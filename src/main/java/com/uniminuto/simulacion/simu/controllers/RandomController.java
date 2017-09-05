package com.uniminuto.simulacion.simu.controllers;

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
        return new Listas(sC.getX(),sC.getU());
        
    }
}


