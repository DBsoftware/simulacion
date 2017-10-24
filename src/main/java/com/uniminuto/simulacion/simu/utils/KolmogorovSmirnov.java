/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.simulacion.simu.utils;

import java.util.Arrays;

/**
 *
 * @author blueashym
 */
public class KolmogorovSmirnov {

    double[] d1, d2, num;
    double alpha;

    public KolmogorovSmirnov(double num[], double alpha) {
        this.d1 = new double[num.length];
        this.d2 = new double[num.length];
        this.num = num;
        this.alpha = alpha;
    }

    public String hacerTest() {
        Arrays.sort(num);
        double n = (double) num.length;

        for (int i = 0; i < num.length; i++) {
            d1[i] = (i / n) - num[i];
        }

        for (int i = 0; i < num.length; i++) {
            d2[i] = num[i] - ((i - 1) / n);
        }

        double maxD1 = Arrays.stream(d1).max().getAsDouble();
        double maxD2 = Arrays.stream(d2).max().getAsDouble();
        double max = ((maxD1 > maxD2) ? maxD1 : maxD2);

        return (alpha >  max) ? "p,"+max+","+alpha: "f,"+max+","+alpha;

    }

}
