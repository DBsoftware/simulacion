package com.uniminuto.simulacion.simu.utils;

import groovy.lang.*;

public class seudoCodigo {

	private double[] X, U;

	public double[] getX() {
		return X;
	}

	public double[] getU() {
		return U;
	}

	public void generarArreglos(String aux,String aux2,String s,String l) throws InstantiationException, IllegalAccessException {
		// Create a String with Groovy code.
		final StringBuilder groovyScript = new StringBuilder();
		groovyScript.append("class Sample {");
		groovyScript.append("  double sayIt(n) { "+aux+" } \n");
		groovyScript.append("  double say(n) { "+aux2+" }");
		groovyScript.append("}");
		int auxs= Integer.parseInt(s);
		int auxl= Integer.parseInt(l);
		int n = 0, auxiliar = 0;
		X = new double[auxl];
		U = new double[auxl];
		X[0] = auxs;
		while (auxiliar < (auxl-1)) {
			n++;
			X[n] = funcionTransicion(X[n - 1],groovyScript);
			U[n] = functionSalida(X[n],groovyScript);
			auxiliar++;
		}

	}

	private double funcionTransicion(double n,StringBuilder groovyScript) throws InstantiationException, IllegalAccessException {
		return stringToCode(n, groovyScript,true);		
	}

	private double functionSalida(double n,StringBuilder groovyScript)throws InstantiationException, IllegalAccessException {
		return stringToCode(n, groovyScript,false);
	}
	
	private double stringToCode(double n,StringBuilder groovyScript,boolean a)throws InstantiationException, IllegalAccessException{
		String aux=(a)?"sayIt":"say";
		@SuppressWarnings("resource")
		final GroovyClassLoader classLoader = new GroovyClassLoader();

		// Load string as Groovy script class.
		Class<?> groovy = classLoader.parseClass(groovyScript.toString());
		GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
		double output = (double) groovyObj.invokeMethod(aux, new Object[] { Double.parseDouble(String.valueOf(n)) });
		return output;	
		
	}
}
