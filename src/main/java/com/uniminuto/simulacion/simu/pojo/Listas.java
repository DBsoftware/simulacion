package com.uniminuto.simulacion.simu.pojo;

public class Listas {
	private String X,U,pr,vr,chi,ks;

	


	public Listas(String x, String u, String pr, String vr, String chi, String ks) {
		super();
		X = x;
		U = u;
		this.pr = pr;
		this.vr = vr;
		this.chi = chi;
		this.ks = ks;
	}

	public String getPr() {
		return pr;
	}

	public String getVr() {
		return vr;
	}

	public String getChi() {
		return chi;
	}

	public String getRs() {
		return ks;
	}

	public String getX() {
		return X;
	}

	public void setX(String x) {
		X = x;
	}

	public String getU() {
		return U;
	}

	public void setU(String u) {
		U = u;
	}
	
	
}
