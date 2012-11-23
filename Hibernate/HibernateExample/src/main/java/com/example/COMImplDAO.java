package com.example;

import java.util.ArrayList;

import org.hibernate.Session;

public class COMImplDAO implements COMDAO {

	public ArrayList<COM> getAllCOM() {

		ArrayList<COM> list = new ArrayList<COM>();

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			list = (ArrayList<COM>) session.createCriteria(COM.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;

	}
}
