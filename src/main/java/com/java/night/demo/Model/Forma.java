package com.java.night.demo.Model;

import java.util.UUID;

public class Forma {

    UUID formaUuid;

    String formaName;

    FormaType formaType;

    int lado;

    double area;

    String errorDeCalculo;

    int delayCalculo;

    public Forma(int lado, UUID formaUuid, String formaName, FormaType formaType, int delayCalculo) {
        this.lado = lado;
        this.formaUuid = formaUuid;
        this.formaName = formaName;
        this.formaType = formaType;
        this.delayCalculo = delayCalculo;
    }

    public void calcularArea() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(this.delayCalculo);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UUID getFormaUuid() {
        return formaUuid;
    }

    public void setFormaUuid(UUID formaUuid) {
        this.formaUuid = formaUuid;
    }

    public String getFormaName() {
        return formaName;
    }

    public void setFormaName(String formaName) {
        this.formaName = formaName;
    }

    public FormaType getFormaType() {
        return formaType;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public void setFormaType(FormaType formaType) {
        this.formaType = formaType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getErrorDeCalculo() {
        return errorDeCalculo;
    }

    public void setErrorDeCalculo(String errorDeCalculo) {
        this.errorDeCalculo = errorDeCalculo;
    }

    public int getDelayCalculo() { return delayCalculo; }

    public void setDelayCalculo(int delayCalculo) { this.delayCalculo = delayCalculo; }
}