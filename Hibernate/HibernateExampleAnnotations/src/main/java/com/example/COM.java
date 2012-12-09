package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "COM")
public class COM {

	private Integer id_com = null;
	private Integer id_pc = null;
	private Integer nr_unit = null;

	@Id
	@GeneratedValue
	@Column(name = "id_com", unique = true, nullable = false)
	public Integer getId_com() {
		return id_com;
	}

	public void setId_com(Integer id_com) {
		this.id_com = id_com;
	}


	@Column(name = "id_pc", nullable = false)
	public Integer getId_pc() {
		return id_pc;
	}

	public void setId_pc(Integer id_pc) {
		this.id_pc = id_pc;
	}

	@Column(name = "nr_unit", nullable = false)
	public Integer getNr_unit() {
		return nr_unit;
	}

	public void setNr_unit(Integer nr_unit) {
		this.nr_unit = nr_unit;
	}

}
