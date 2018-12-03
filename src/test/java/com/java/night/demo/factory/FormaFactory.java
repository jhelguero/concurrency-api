package com.java.night.demo.factory;

import com.java.night.demo.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FormaFactory {

    public static List<Forma> buildFormaListMismoDelay() {

        List<Forma> formaList = new ArrayList<>();

        Forma forma1 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo1", 1000);
        Forma forma2 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo2", 1000);
        Forma forma3 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo3", 1000);
        Forma forma4 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo1", 1000);
        Forma forma5 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo2", 1000);
        Forma forma6 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo3", 1000);
        Forma forma7 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo1", 1000);
        Forma forma8 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo2", 1000);
        Forma forma9 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo3", 1000);

        formaList.add(forma1);
        formaList.add(forma2);
        formaList.add(forma3);
        formaList.add(forma4);
        formaList.add(forma5);
        formaList.add(forma6);
        formaList.add(forma7);
        formaList.add(forma8);
        formaList.add(forma9);

        return formaList;
    }

    public static List<Forma> buildFormaListDiferenteDelay() {

        List<Forma> formaList = new ArrayList<>();

        Forma forma1 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo1", 2000);
        Forma forma2 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo2", 2000);
        Forma forma3 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo3", 2000);
        Forma forma4 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo1", 1000);
        Forma forma5 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo2", 1000);
        Forma forma6 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo3", 1000);
        Forma forma7 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo1", 1000);
        Forma forma8 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo2", 1000);
        Forma forma9 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo3", 1000);

        formaList.add(forma1);
        formaList.add(forma2);
        formaList.add(forma3);
        formaList.add(forma4);
        formaList.add(forma5);
        formaList.add(forma6);
        formaList.add(forma7);
        formaList.add(forma8);
        formaList.add(forma9);

        return formaList;
    }

    public static List<Forma> buildFormaListTipoDesconocido() {

        List<Forma> formaList = new ArrayList<>();

        Forma forma1 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo1", 1000);
        Forma forma2 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo2", 1000);
        Forma forma3 = new FormaCirculo(new Random().nextInt(10), UUID.randomUUID(),"Circulo3", 1000);
        Forma forma4 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo1", 1000);
        Forma forma5 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo2", 1000);
        Forma forma6 = new FormaRectangulo(new Random().nextInt(10), UUID.randomUUID(),"Rectangulo3", 1000);
        Forma forma7 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo1", 1000);
        Forma forma8 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo2", 1000);
        Forma forma9 = new FormaTriangulo(new Random().nextInt(10), UUID.randomUUID(),"Triangulo3", 1000);

        formaList.add(forma1);
        formaList.add(forma2);
        formaList.add(forma3);
        formaList.add(forma4);
        formaList.add(forma5);
        formaList.add(forma6);
        formaList.add(forma7);
        formaList.add(forma8);
        formaList.add(forma9);
        formaList.add(new Forma(new Random().nextInt(10), UUID.randomUUID(),"Desconocido1", FormaType.UNKNOWN, 1000));

        return formaList;
    }
}
