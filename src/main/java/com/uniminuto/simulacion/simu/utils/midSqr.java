package com.uniminuto.simulacion.simu.utils;

import groovy.lang.*;

public class midSqr {

	private String[] X, U;

	public String[] getX() {
		return X;
	}

	public String[] getU() {
		return U;
	}
	
	public double[] stringToDouble(String[] aux){
		double[] d=new double[aux.length];
		for (int i = 0; i < d.length; i++) {
			d[i]=Double.parseDouble(aux[i]);
		}
		return d;
	}

	public void generarArreglos(String aux,String aux2,String s,String l) throws InstantiationException, IllegalAccessException {
		// Create a String with Groovy code.
		final StringBuilder groovyScript = new StringBuilder();
		groovyScript.append("class Sample {");
		groovyScript.append("  String sayIt(n) { "+aux+" } \n");
		groovyScript.append("  String say(n) { "+aux2+" }");
		groovyScript.append("}");
		int auxs= Integer.parseInt(s);
		int auxl= Integer.parseInt(l);
		int n = 0, auxiliar = 0;
		X = new String[auxl];
		U = new String[auxl];
		X[0] = auxs +"";
		U[0] = "0";
		while (auxiliar < (auxl-1)) {
			n++;
			X[n] = funcionTransicion(X[n - 1],groovyScript);
			U[n] = functionSalida(X[n],groovyScript);
			auxiliar++;
		}

	}

	private String funcionTransicion(String n,StringBuilder groovyScript) throws InstantiationException, IllegalAccessException {
		return stringToCode(n, groovyScript,true);		
	}

	private String functionSalida(String n,StringBuilder groovyScript)throws InstantiationException, IllegalAccessException {
		return stringToCode(n, groovyScript,false);
	}
	
	private String stringToCode(String n,StringBuilder groovyScript,boolean a)throws InstantiationException, IllegalAccessException{
		String aux=(a)?"sayIt":"say";
		@SuppressWarnings("resource")
		final GroovyClassLoader classLoader = new GroovyClassLoader();

		// Load string as Groovy script class.
		Class<?> groovy = classLoader.parseClass(groovyScript.toString());
		GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
		String output = (String) groovyObj.invokeMethod(aux, new Object[] { n });
		return output;	
		
	}
}

