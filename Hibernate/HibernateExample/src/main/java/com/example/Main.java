package com.example;

import java.util.Collection;
import java.util.Iterator;

public class Main {
	@SuppressWarnings("rawtypes")
	public static void main(String a[]) {

		Collection comenzi = (Collection) Factory.getInstance().getCOMDAO()
				.getAllCOM();

		Iterator iterator = comenzi.iterator();

		while (iterator.hasNext()) {
			COM com = (COM) iterator.next();
			System.out.println(com.getId_com()
					+ ", " + com.getId_pc() + ", " + com.getnr_unit());
		}

	}

}
