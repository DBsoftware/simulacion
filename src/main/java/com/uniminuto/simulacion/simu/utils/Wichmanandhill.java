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
public class Wichmanandhill {
	String[]t =null;
	
        public String[] getT() {
		return t;
	}

		public double[] generar(int parada,int valx,int valy,int valz) {  
        double aux=0;	
        int n=0;
        double [] x =new double[parada];
        double [] y =new double[parada];
        double [] z =new double[parada];
        double [] u =new double[parada];
        t =new String[parada];
        x[0]=valx;
        y[0]=valy;
        z[0]=valz;
        t[0]=x[0]+" - "+y[0]+" - "+z[0];
        while(n<parada-1){
            x[n+1]=(171*x[n])%30269;
            y[n+1]=(172*y[n])%30307;
            z[n+1]=(170*z[n])%30323;
            t[n+1]=x[n+1]+" - "+y[n+1]+" - "+z[n+1];
            aux=(x[n+1]/30269)+(y[n+1]/30307)+(z[n+1]/30323);
            u[n+1]=aux-(int)aux;
            n++;
        }
        return u;
    } 
}
