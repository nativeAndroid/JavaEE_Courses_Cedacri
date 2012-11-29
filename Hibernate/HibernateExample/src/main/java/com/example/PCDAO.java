package com.example;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PCDAO {

	/**
	 * Adds a new pc to pcs table
	 * @return true on success
	 */
	public boolean add(PC pc) throws SQLException;
	
	/**
	 * Updates data of pc with specified id
	 * @param id id of pc to update
	 * @param pc object with new pc data
	 * @return
	 */
	public boolean update(int id, PC pc) throws SQLException;
	
	/**
	 * Removes a pc from the table using pc
	 * id.
	 * @param id identificator of the removed pc
	 * @return The object of currently removed pc
	 */
	public PC removeByPCId(int id);
	
	/**
	 * Returns an object containing pc with
	 * specified id.
	 * @param id pc id
	 * @return object containing pc with specified id
	 */
	public PC getByPCId(int id) throws SQLException;
	
	/**
	 * Returns a list of all pc from the
	 * pc table
	 * @return a list of all pc
	 */
	public ArrayList<PC> getAll();
}
