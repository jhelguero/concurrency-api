package com.java.night.demo.Model;

import java.util.UUID;

public class FormaTriangulo extends Forma {

    public FormaTriangulo(int lado, UUID formaUuid, String formaName, int delay) {
        super(lado, formaUuid, formaName, FormaType.TRIANGULO, delay);
    }

    @Override
    public void calcularArea() {
        super.calcularArea();
        this.setArea((lado * lado) / 2);
    }
}
