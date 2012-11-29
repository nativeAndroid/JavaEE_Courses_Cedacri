package com.example;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;

public class PCImplDAO implements PCDAO{

	public boolean add(PC pc) throws SQLException {

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(pc);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}

		return false;
	}

	public boolean update(int id, PC pc) throws SQLException {

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(pc);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}

		return false;
	}

	public PC removeByPCId(int id) {

		Session session = null;
		PC pc = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			pc = (PC) session.get(PC.class, id);
			session.delete(pc);
			session.getTransaction().commit();
			return pc;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

	public PC getByPCId(int id) throws SQLException {

		Session session = null;
		PC pc = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			pc = (PC) session.get(PC.class, id);
			return pc;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

	public ArrayList<PC> getAll() {

		Session session = null;
		ArrayList<PC> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			list = (ArrayList<PC>) session.createCriteria(PC.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

}
