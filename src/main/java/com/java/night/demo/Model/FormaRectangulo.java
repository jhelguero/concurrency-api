package com.java.night.demo.Model;

import java.util.UUID;

public class FormaRectangulo extends Forma {

    public FormaRectangulo(int lado, UUID formaUuid, String formaName, int delay) {
        super(lado, formaUuid, formaName, FormaType.RECTANGULO, delay);
    }

    @Override
    public void calcularArea() {
        super.calcularArea();
        this.setArea(lado * lado);
    }
}
