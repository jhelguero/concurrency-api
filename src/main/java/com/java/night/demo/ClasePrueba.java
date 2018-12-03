package com.java.night.demo;

import com.java.night.demo.Model.Either;
import com.java.night.demo.Model.Forma;
import com.java.night.demo.Model.FormaType;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ClasePrueba {

    public void proccessSequentially(List<Forma> formas) {
        long start = System.nanoTime();

        formas.stream().forEach(forma -> {
            logFormaCalculada(forma);
            forma.calcularArea();
        });

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);
    }

    public List<Void> proccessExecutorService(List<Forma> formas, int maxThreadsPool) {
        long start = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(formas.size(), maxThreadsPool));
        List<CompletableFuture<Void>> formasAreasCalculadas = formas.stream()
                .map(forma -> CompletableFuture.runAsync(() -> {
                    logFormaCalculada(forma);
                            forma.calcularArea();
                        }, executorService)
                )
                .collect(Collectors.toList());

        List<Void> result = formasAreasCalculadas.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);

        return result;
    }

    public void proccessParallelStream(List<Forma> formas) {
        long start = System.nanoTime();

        formas.parallelStream().forEach(forma -> {
            logFormaCalculada(forma);
            forma.calcularArea();
        });

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);
    }

    public List<Forma> proccessExecutorServiceTimedOut(List<Forma> formas, int maxThreadsPool) {
        long start = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(formas.size(), maxThreadsPool));
        List<Forma> futures = formas.stream()
                .map(forma -> {
                    try {
                        return CompletableFuture.supplyAsync(() -> {
                            forma.calcularArea();
                            return forma;
                        }, executorService)
                                .get(1500, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        forma.setErrorDeCalculo(e.getMessage());
                        return forma;
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(formaCalculada -> formaCalculada != null)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);

        return futures;
    }

    public void proccessResponses(List<Forma> formas, int maxThreadsPool) {
        long start = System.nanoTime();

        List<Either<Forma, Exception>> result = formas.stream()
                .map(forma -> calcularAreaPorTipo(forma, maxThreadsPool))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        for (Either<Forma, Exception> forma : result) {
            if (forma.isRight()) {
                System.out.println("Informar al sistema de errores esta ocurrencia " + forma.getRight().getMessage());
            } else {
                Forma formaFinal = forma.getLeft();
                System.out.println("El área de la forma= " + formaFinal.getFormaType() + " es " + formaFinal.getArea());
            }
        }

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);
    }

    private CompletableFuture<Either<Forma, Exception>> calcularAreaPorTipo(Forma forma, int maxThreadsPool) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadsPool);

        // Esto se podría correr con runAsync sin devolver respuesta como en el método proccessExecutorService
        // Lo corremos con supplyAsync a los fines de mostrar qué podemos hacer en el postprocesamiento
        return CompletableFuture.supplyAsync(() -> {
             FormaType formaType = forma.getFormaType();
             String errorMesagge;
             switch (formaType) {
                 case RECTANGULO:
                 case CIRCULO:
                 case TRIANGULO:
                     logFormaCalculada(forma);
                     forma.calcularArea();
                     return Either.left(forma);
                 default:
                     errorMesagge = "Tipo de forma no válido, chequee el tipo de la forma";
                     forma.setErrorDeCalculo(errorMesagge);
                     return Either.right(new InvalidKeyException(errorMesagge));
             }
         }, executorService);
    }

    private void logFormaCalculada(Forma forma) {
        FormaType formaType = forma.getFormaType();
        String formaName = forma.getFormaName();
        System.out.println("Calculando área para formaType=" + formaType + " formaName=" + formaName);
    }
}
