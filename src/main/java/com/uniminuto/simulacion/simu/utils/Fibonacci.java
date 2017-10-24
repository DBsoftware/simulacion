/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.simulacion.simu.utils;



/**
 *
 * @author blueashym
 */
public class Fibonacci {

    public double[] generador(int parada,int s,int r,String ope,int valmodulo,int n0,int n1) {
        int n = 2;
        double[] x = new double[parada];
        x[0] = n0;
        x[1] = n1;
        while (n < parada) {
            if (ope.equals("+")) {
                x[n] = (x[n - s] + x[n - r]) % valmodulo;
            } else if (ope.equals("-")) {
                x[n] = (x[n - s] - x[n - r]) % valmodulo;
            } else if (ope.equals("*")) {
                x[n] = (x[n - s] * x[n - r]) % valmodulo;
            }
            n++;
        }
        return x;
    }
}
