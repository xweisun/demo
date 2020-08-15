package com.sun.controller;

public class Pair <T,R> {
    private String T;
    private Double R;

    public Pair() {
    }

    public Pair(String t, Double r) {
        T = t;
        R = r;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "T='" + T + '\'' +
                ", R=" + R +
                '}';
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public Double getR() {
        return R;
    }

    public void setR(Double r) {
        R = r;
    }
}
