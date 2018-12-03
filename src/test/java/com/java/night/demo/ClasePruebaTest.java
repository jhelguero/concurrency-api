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
    public void proccessTest() {
        List<Forma> formaList = FormaFactory.buildFormaListMismoDelay();

        List<Forma> formaListResult = executorServiceConcurrency.proccess(formaList);

        assertThat(formaListResult.size(), is(6));
    }

    @Test
    public void proccessResponseTest() {
        List<Forma> formaList = FormaFactory.buildFormaListTipoDesconocido();

        executorServiceConcurrency.proccessResponses(formaList);
    }
}
