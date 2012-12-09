package com.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public interface COMDAO {

	/**
	 * Adds a new command to commands table
	 * @return true on success
	 */
	public boolean add(COM com) throws SQLException;
	
	/**
	 * Updates data of command with specified id
	 * @param id id of command to update
	 * @param com object with new command data
	 * @return
	 */
	public boolean update(int id, COM com) throws SQLException;
	
	/**
	 * Removes a command from the table using command
	 * id.
	 * @param id identificator of the removed command
	 * @return The object of currently removed command
	 */
	public COM removeByCommandId(int id);
	
	/**
	 * Removes a command from the table using pc
	 * id.
	 * @param id PC identificator
	 * @return number of removed commands
	 */
	public int removeByPCId(int id) throws SQLException;
	
	/**
	 * Removes a command from the table based on 
	 * quantity of pc's that command contains.
	 * @param nrUnit nr of pc units
	 * @return nr of removed commands
	 */
	public int removeByNrUnit(int nrUnit) throws SQLException;
	
	/**
	 * Returns an object containing command with
	 * specified id.
	 * @param id command id
	 * @return object containing command with specified id
	 */
	public COM getByCommandId(int id) throws SQLException;
	
	/**
	 * Returns List of commands that are asociated with
	 * specific pc id.
	 * @param id pc id
	 * @return list of commands
	 */
	public ArrayList<COM> findByPCId(int id) throws SQLException;
	
	/**
	 * Returns List of commands with specific
	 * nr of units sold
	 * @param nrUnit nr of units sold
	 * @return list of commands
	 */
	public ArrayList<COM> findByNrUnit(int nrUnit) throws SQLException;
	
	/**
	 * Returns a list of all commands from the
	 * commands table
	 * @return a list of all commands
	 */
	public ArrayList<COM> getAll();
	
}
