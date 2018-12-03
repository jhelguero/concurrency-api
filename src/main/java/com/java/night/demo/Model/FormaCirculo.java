package com.java.night.demo.Model;

import java.util.UUID;

public class FormaCirculo extends Forma {

    public FormaCirculo(int lado, UUID formaUuid, String formaName, int delay) {
        super(lado, formaUuid, formaName, FormaType.CIRCULO, delay);
    }

    @Override
    public void calcularArea(){
        super.calcularArea();
        float radio = lado / 2;
        this.setArea(Math.PI * radio * radio);
    }
}
