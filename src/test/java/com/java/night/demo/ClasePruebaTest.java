package com.java.night.demo;

import com.java.night.demo.Model.Forma;
import com.java.night.demo.Model.FormaType;
import com.java.night.demo.factory.FormaFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ClasePruebaTest {

    private ClasePrueba executorServiceConcurrency;

    @Before
    public void setup() {
        this.executorServiceConcurrency = new ClasePrueba();
    }

    @Test
    public void proccessSequentiallyTest() {
        List<Forma> formaList = FormaFactory.buildFormaListMismoDelay();

        executorServiceConcurrency.proccessSequentially(formaList);
    }

    @Test
    public void proccessParallelStreamTest() {
        List<Forma> formaList = FormaFactory.buildFormaListMismoDelay();

        executorServiceConcurrency.proccessParallelStream(formaList);
    }

    // Este test sirve para apreciar las diferencia en los tiempos de respuesta en comparación con parallelStream
    // Probar seteando diferentes cantidades de threads
    @Test
    public void proccessExecutorServiceSinDelayTest() {
        List<Forma> formaList = FormaFactory.buildFormaListMismoDelay();

        List<Void> formaListResult = executorServiceConcurrency.proccessExecutorService(formaList, formaList.size());

        assertThat(formaListResult.size(), is(formaList.size()));
    }

    // Este es el ejemplo que no funcionó en la charla, donde a la forma de tipo círculo le seteabamos un tiempo de cálculo mayor
    // El error del anterior código es que siempre devolvíamos una forma nula por el finally del catch
    // Sigue funcionando en forma secuencial pero el filtrado de los resultados se hace correctamente por time out
    // Acá se explica como realizar un mejor manejo de time outs para tareas en paralelo
    // http://iteratrlearning.com/java9/2016/09/13/java9-timeouts-completablefutures.html
    @Test
    public void proccessExecutorServiceConDelayTest() {
        List<Forma> formaList = FormaFactory.buildFormaListDiferenteDelay();

        List<Forma> formaListResult = executorServiceConcurrency.proccessExecutorServiceTimedOut(formaList, formaList.size());

        assertThat(formaListResult.size(), is(6));
    }

    // El test para este método sigue funcionando en forma secuencial pero sirve para dar una idea de cómo sería el manejo de respuestas
    @Test
    public void proccessResponsesTest() {
        List<Forma> formaList = FormaFactory.buildFormaListTipoDesconocido();

        executorServiceConcurrency.proccessResponses(formaList);
    }
}
