package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PC")
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

	@Id
	@GeneratedValue
	@Column(name = "id_pc", unique = true, nullable = false)
	public Integer getId_pc() {
		return id_pc;
	}

	public void setId_pc(Integer id_pc) {
		this.id_pc = id_pc;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "descr", nullable = false)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
