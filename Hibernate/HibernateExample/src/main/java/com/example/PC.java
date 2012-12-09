package com.example;

import java.util.HashSet;
import java.util.Set;

public class PC {

	private Integer id_pc = null;
	private String name = null;
	private String descr = null;
	private Double price = null;

	public PC(){
		
	}
	
	public PC(PC pc){
		setId_pc(pc.getId_pc());
		setName(pc.getName());
		setDescr(pc.getDescr());
		setPrice(pc.getPrice());
	}

	public Integer getId_pc() {
		return id_pc;
	}

	public void setId_pc(Integer id_pc) {
		this.id_pc = id_pc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
