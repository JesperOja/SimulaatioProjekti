package main;

import framework.Varasto;
import model.OmaVarasto;


public class Simulaattori {

	public static void main(String[] args) {
		Varasto v = new OmaVarasto();
		
		v.setSimulointiaika(1000);
		v.aja();
		v.tulokset();
	}

}
