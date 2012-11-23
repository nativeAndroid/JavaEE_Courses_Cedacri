package com.example;

import java.util.HashSet;
import java.util.Set;

public class COM {

	private Integer id_com = null;
	private Set id_pc = new HashSet<COM>();
	private Integer nr_unit = null;

	public Integer getId_com() {
		return id_com;
	}

	public void setId_com(Integer id_com) {
		this.id_com = id_com;
	}

	public Set getId_pc() {
		return id_pc;
	}

	public void setId_pc(Set id_pc) {
		this.id_pc = id_pc;
	}

	public Integer getnr_unit() {
		return nr_unit;
	}

	public void setnr_unit(Integer nr_unit) {
		this.nr_unit = nr_unit;
	}

}
