package com.example.demo;

public class Helloworld {

	String mes;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Helloworld(String mes) {
		super();
		this.mes = mes;
	}

	@Override
	public String toString() {
		return "Helloworld [mes=" + mes + "]";
	}
	
}
