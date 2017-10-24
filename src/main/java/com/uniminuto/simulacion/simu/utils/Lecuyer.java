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
public class Lecuyer {
	double [] z =null;
	
    public double[] getZ() {
		return z;
	}

	public double[] generador(int parada,Long valx,Long valy){
        int n=0;
        double [] x =new double[parada];
        double [] y =new double[parada];
        z =new double[parada];
        double [] u =new double[parada];
        x[0]=valx;
        y[0]=valy;
        double aux=0;
        while(n<parada-1){
            x[n+1]=(40014*x[n])%2147483563;
            y[n+1]=(40692*y[n])%2147483399;
            z[n+1]=(x[n] - y[n])%2147483562;
            if(z[n+1]!=0){
            	aux = z[n+1]/2147483563;
                u[n+1]=aux-(int)aux;
            }else{
                u[n+1]=0.9999999995343387;
            }
            u[n+1]*=(u[n+1]<0)?-1:1;
            n++;
        }
        return u;
    }
    
}
