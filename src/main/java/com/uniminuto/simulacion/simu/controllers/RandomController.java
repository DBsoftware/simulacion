package com.uniminuto.simulacion.simu.controllers;



import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uniminuto.simulacion.simu.pojo.Listas;
import com.uniminuto.simulacion.simu.utils.*;

@RestController
@RequestMapping("/random")
public class RandomController {


	@RequestMapping(method = RequestMethod.GET)
    public Listas greeting(@RequestParam(value="x", defaultValue="(n+2)/Math.pow(n, 2)") String x,
							@RequestParam(value="u", defaultValue="n / 33") String u,
							@RequestParam(value="s", defaultValue="2") String s,
							@RequestParam(value="l", defaultValue="20") String l) {
    	seudoCodigo sC = new seudoCodigo();
    	try {
			sC.generarArreglos(x, u,s,l);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Listas(sC.getX(),Arrays.toString(sC.getU())
				,new Probadoresaleatorios().testPromedio(sC.getU())
				,new Probadoresaleatorios().testVarianza(sC.getU())
				,new Probadoresaleatorios().testChiCuadrado(sC.getU())
				, new KolmogorovSmirnov(sC.getU(),
	                    jsc.goodnessfit.KolmogorovCB.exactCriticalValue(sC.getU().length, 0.05/2)
			            ).hacerTest()); 
        
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public Listas greetingP(@RequestParam(value="x", defaultValue="(n+2)/Math.pow(n, 2)") String x,
							@RequestParam(value="u", defaultValue="n / 33") String u,
							@RequestParam(value="s", defaultValue="2") String s,
							@RequestParam(value="l", defaultValue="20") String l) {
    	midSqr sC = new midSqr();
    	try {
			sC.generarArreglos(x, u,s,l);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Listas(sC.stringToDouble(sC.getX()),Arrays.toString(sC.getU())
				,new Probadoresaleatorios().testPromedio(sC.stringToDouble(sC.getU()))
				,new Probadoresaleatorios().testVarianza(sC.stringToDouble(sC.getU()))
				,new Probadoresaleatorios().testChiCuadrado(sC.stringToDouble(sC.getU()))
				, new KolmogorovSmirnov(sC.stringToDouble(sC.getU()),
	                    jsc.goodnessfit.KolmogorovCB.exactCriticalValue(sC.getU().length, 0.05/2)
			            ).hacerTest()); 
        
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/con")
    public Listas greeti(@RequestParam(value="x", defaultValue="(n+2)/Math.pow(n, 2)") String x,
							@RequestParam(value="u", defaultValue="n / 33") String u,
							@RequestParam(value="s", defaultValue="2") String s,
							@RequestParam(value="l", defaultValue="20") String l,
							@RequestParam(value="a", defaultValue="5") String a,
							@RequestParam(value="b", defaultValue="1") String b,
							@RequestParam(value="m", defaultValue="9") String m) {
    	Congruenciales sC = new Congruenciales();
    	try {
			sC.generarArreglos(x, u,s,l,a,b,m);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Listas li = new Listas(sC.getX(),Arrays.toString(sC.getU())
				,new Probadoresaleatorios().testPromedio(sC.getU())
				,new Probadoresaleatorios().testVarianza(sC.getU())
				,new Probadoresaleatorios().testChiCuadrado(sC.getU())
				, new KolmogorovSmirnov(sC.getU(),
	                    jsc.goodnessfit.KolmogorovCB.exactCriticalValue(sC.getU().length, 0.05/2)
			            ).hacerTest()); 
		return li;      
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/c")
    public Listas greet(@RequestParam(value="x", defaultValue="1") String x,
							@RequestParam(value="y", defaultValue="1") String y,
							@RequestParam(value="z", defaultValue="1") String z,
							@RequestParam(value="m", defaultValue="1") String m,
							@RequestParam(value="n1", defaultValue="1") String n1,
							@RequestParam(value="n0", defaultValue="1") String n0,
							@RequestParam(value="r", defaultValue="1") String r,
							@RequestParam(value="s", defaultValue="1") String s,
							@RequestParam(value="p", defaultValue="1") String p,
							@RequestParam(value="op", defaultValue="1") String op,							
							@RequestParam(value="w", defaultValue="1") String w
							) {
    		double [] u= null;
    		switch (w) {
			case "1":
				u =new Fibonacci().generador(Integer.parseInt(p),
						Integer.parseInt(s),
						Integer.parseInt(r), op,
						Integer.parseInt(m), 
						Integer.parseInt(n0),
						Integer.parseInt(n1));
				break;
			case "2":
				u =new Lecuyer().generador(Integer.parseInt(p), new Long(x), new Long(y));
				break;
			case "3":
				u =new Wichmanandhill().generar(Integer.parseInt(p), Integer.parseInt(x),  Integer.parseInt(y),  Integer.parseInt(z) );
				break;
			}
        	
    		Listas l = new Listas(u,Arrays.toString(u)
    				,new Probadoresaleatorios().testPromedio(u)
    				,new Probadoresaleatorios().testVarianza(u)
    				,new Probadoresaleatorios().testChiCuadrado(u)
    				, new KolmogorovSmirnov(u,
    	                    jsc.goodnessfit.KolmogorovCB.exactCriticalValue(u.length, 0.05/2)
    			            ).hacerTest()); 
    		return l;
        
    }

}


