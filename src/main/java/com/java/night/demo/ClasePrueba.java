package com.java.night.demo;

import com.java.night.demo.Model.Either;
import com.java.night.demo.Model.Forma;
import com.java.night.demo.Model.FormaType;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ClasePrueba {

    public List<Forma> proccess(List<Forma> formas) {
        long start = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(formas.size(), 9));
        List<Forma> futures = formas.stream()
                .map(forma -> {
                    try {
                        //Esto se podría correr con runAsync sin devolver respuesta (sin return forma;)
                        return CompletableFuture.supplyAsync(() -> {
                            FormaType formaType = forma.getFormaType();
                            String formaName = forma.getFormaName();
                            System.out.println("Calculando área para formaType=" + formaType + " formaName=" + formaName);
                            forma.calcularArea();
                            return forma;
                        }, executorService)
                        .get(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } finally {
                        return null;
                    }
                })
                .filter(forma -> forma != null)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", formas.size(), duration);

        return futures;
    }

    public void proccessResponses(List<Forma> formas) {
        List<Either<Forma, Exception>> result = formas.stream()
                .map(forma -> calculateArea(forma))
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
    }

    private CompletableFuture<Either<Forma, Exception>> calculateArea(Forma forma) {
        ExecutorService executorService = Executors.newFixedThreadPool(9);

        return CompletableFuture.supplyAsync(() -> {
             FormaType formaType = forma.getFormaType();
             String formaName = forma.getFormaName();
             String errorMesagge = "";
             try {
                 switch (formaType) {
                     case RECTANGULO:
                     case CIRCULO:
                     case TRIANGULO:
                         System.out.println("Calculando área para formaType=" + formaType + " formaName=" + formaName);
                         forma.calcularArea();
                         return Either.left(forma);
                     default:
                         errorMesagge = "Tipo de forma no válido, chequee el tipo de la forma";
                         forma.setErrorDeCalculo(errorMesagge);
                         return Either.right(new InvalidKeyException(errorMesagge));
                 }
             } catch (RuntimeException re) {
                 errorMesagge = "Error encontrado al calcular el área para formaType=" + formaType
                         + " formaName=" + formaName
                         + " exception=" + re.getMessage();
                 System.out.println(errorMesagge);
                 forma.setErrorDeCalculo(errorMesagge);
                 return Either.right(re);
             }
         }, executorService);
    }
}
