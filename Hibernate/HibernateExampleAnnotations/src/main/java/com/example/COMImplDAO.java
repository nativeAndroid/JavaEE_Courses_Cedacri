package com.example;

import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Query;
import org.hibernate.Session;

public class COMImplDAO implements COMDAO {

	public boolean add(COM com) throws SQLException {

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(com);
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

	public boolean update(int id, COM com) throws SQLException {

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			if(session.get(COM.class, id) != null)
				session.merge(com);
			else
				session.save(com);
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

	public COM removeByCommandId(int id) {

		Session session = null;
		COM com = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			com = (COM) session.get(COM.class, id);
			session.delete(com);
			session.getTransaction().commit();
			return com;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

	public int removeByPCId(int id) throws SQLException {
		Session session = null;
		ArrayList<COM> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"SELECT c FROM COM c WHERE c.id_pc = :id_pc ").setInteger(
					"id_pc", id);
			list = (ArrayList<COM>) query.list();
			for(COM i : list)	{
				session.delete(i);
			}
			session.getTransaction().commit();
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return 0;
	}

	public int removeByNrUnit(int nrUnit) throws SQLException {

		Session session = null;
		ArrayList<COM> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"SELECT c FROM COM c WHERE c.nr_unit = :nrUnit ")
					.setInteger("nrUnit", nrUnit);
			list = (ArrayList<COM>) query.list();
			for(COM i : list)	{
				session.delete(i);
			}
			session.getTransaction().commit();
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return 0;
	}

	public COM getByCommandId(int id) throws SQLException {

		Session session = null;
		COM com = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			com = (COM) session.get(COM.class, id);
			return com;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<COM> findByPCId(int id) throws SQLException {
		Session session = null;
		ArrayList<COM> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"SELECT c FROM COM c WHERE c.id_pc = :id_pc ").setInteger(
					"id_pc", id);
			list = (ArrayList<COM>) query.list();
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

	public ArrayList<COM> findByNrUnit(int nrUnit) throws SQLException {

		Session session = null;
		ArrayList<COM> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"SELECT c FROM COM c WHERE c.nr_unit = :nrUnit ")
					.setInteger("nrUnit", nrUnit);
			list = (ArrayList<COM>) query.list();
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

	public ArrayList<COM> getAll() {

		Session session = null;
		ArrayList<COM> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			list = (ArrayList<COM>) session.createCriteria(COM.class).list();
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
