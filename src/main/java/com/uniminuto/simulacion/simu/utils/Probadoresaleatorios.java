/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.simulacion.simu.utils;


import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Arrays;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class Probadoresaleatorios {
    final double alfa = 0.05;

	public String testPromedio(double [] datos){
		 int n = datos.length;

	        //funciones de la libreria commons.math3 para sacar los valores de las tablas
	        NormalDistribution nd = new NormalDistribution();
	        DescriptiveStatistics estadistica = new DescriptiveStatistics();
	        
	        //primera prueba

	        double media, li, ls;
	        double z;
	        for (int i=0; i<n; i++){
	            estadistica.addValue(datos[i]);
	        }
	        //con la libreria se saca la media

	        media = estadistica.getMean();
	        //Para obtener el valor z (sin consultar tablas) se utiliza la siguiente fórmula.
	        z= nd.inverseCumulativeProbability(1 - alfa/2 );
	        //Aplicamos las fórmulas para obtener los límites
	        li = 0.5 - z * (1/Math.sqrt(12*estadistica.getN()));
	        ls = 0.5 + z * (1/Math.sqrt(12*estadistica.getN()));
		
		return (media>li&&media<ls)?"p,"+li+","+media+","+ls:"f,"+li+","+media+","+ls;
	}
	public String testVarianza(double [] datos){
		 int n = datos.length;
	        int gradoslibertad = n-1;
        ChiSquaredDistribution chi = new ChiSquaredDistribution(gradoslibertad);
        DescriptiveStatistics estadistica = new DescriptiveStatistics();
        //segunda prueba
		double varianza,liv, lsv;
		for (int i=0; i<n; i++){
			estadistica.addValue(datos[i]);
		}
        varianza = estadistica.getVariance();

        //Obtenemos los valores de la tabla de Chi cuadrada:
        liv = chi.inverseCumulativeProbability(alfa/2)/(12*gradoslibertad);
        lsv = chi.inverseCumulativeProbability(1-alfa/2)/(12*gradoslibertad);
		
		return (varianza>liv&&varianza<lsv)?"p,"+liv+","+varianza+","+lsv:"f,"+liv+","+varianza+","+lsv;
	}

    public String testChiCuadrado(double [] datos){        
        double k =  Math.sqrt(datos.length),t = 0, nsk = datos.length / k;
        double[] arrayFrecuencias = frecuencias(1 / k, datos,(int) k);
        ChiSquaredDistribution chi = new ChiSquaredDistribution(datos.length-1);;
        for (int j = 0; j < (int)k; j++) {
            t += (Math.pow((arrayFrecuencias[j] - nsk), 2) / nsk);
        }
        double x = chi.inverseCumulativeProbability(1 - alfa);
        return (t<x)?"p,"+t+","+x  :"f,"+t+","+ x;
    }
    
    private  double[] frecuencias(double dif, double d[], int k) {
        double auxi = 0, auxs = dif;
        double auxF[] = new double[k];
        int counter = 0;
        Arrays.sort(d);
        for (int i = 0; i < k; i++) {
            for (double e : d) {
                counter += ((e >= auxi) && e < auxs) ? 1 : 0;
            }    
            auxF[i] = counter;
            counter=0;
            auxi = auxs;
            auxs += dif;
        }
        return auxF;
    }
}
