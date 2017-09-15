package com.uniminuto.simulacion.simu.utils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class Congruenciales {

	private double[] X, U;

	public double[] getX() {
		return X;
	}

	public double[] getU() {
		return U;
	}

	public void generarArreglos(String aux,String aux2,String s,String l,String a,String b,String m) throws InstantiationException, IllegalAccessException {
		// Create a String with Groovy code.
		final StringBuilder groovyScript = new StringBuilder();
		groovyScript.append("class Sample {");
		groovyScript.append("  double sayIt(n,a,b,m) { "+aux+" } \n");
		groovyScript.append("  double say(n,a,b,m) { "+aux2+" }");
		groovyScript.append("}");
		int auxs= Integer.parseInt(s);
		int auxl= Integer.parseInt(l);
		double auxa= Double.parseDouble(a);
		double auxb= Double.parseDouble(b);
		double auxm= Double.parseDouble(m);		
		int n = 0, auxiliar = 0;
		X = new double[auxl];
		U = new double[auxl];
		X[0] = auxs;
		while (auxiliar < (auxl-1)) {
			n++;
			X[n] = funcionTransicion(X[n - 1],auxa,auxb,auxm,groovyScript);
			U[n] = functionSalida(X[n],auxa,auxb,auxm,groovyScript);
			auxiliar++;
		}

	}

	private double funcionTransicion(double n,double a,double b,double m,StringBuilder groovyScript) throws InstantiationException, IllegalAccessException {
		return stringToCode(n,a,b,m,groovyScript,true);		
	}

	private double functionSalida(double n,double a,double b,double m,StringBuilder groovyScript)throws InstantiationException, IllegalAccessException {
		return stringToCode(n,a,b,m,groovyScript,false);
	}
	
	private double stringToCode(double n,double a,double b,double m,StringBuilder groovyScript,boolean t)throws InstantiationException, IllegalAccessException{
		String aux=(t)?"sayIt":"say";
		@SuppressWarnings("resource")
		final GroovyClassLoader classLoader = new GroovyClassLoader();

		// Load string as Groovy script class.
		Class<?> groovy = classLoader.parseClass(groovyScript.toString());
		GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
		double output = (double) groovyObj.invokeMethod(aux, new Object[] { n,a,b,m });
		return output;	
		
	}
}
