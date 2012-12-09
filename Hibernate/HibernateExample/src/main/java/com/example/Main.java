package com.example;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.jar.JarEntry;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.example.UI.JAddPCDialog;
import com.example.UI.MainFrame;

public class Main {

	public static void main(String a[]) {

		/*ArrayList<COM> com = null;
		try {
			com = Factory.getInstance().getCOMDAO().findByNrUnit(325);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println(com.size());
		
		for(COM i : com)
			System.out.println(i.getId_com() + ", " + i.getId_pc() + ", " + i.getNr_unit());
*/
		
		
		Runnable ui = new Runnable() {
			
			public void run() {

					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				MainFrame main;
				try {
					main = new MainFrame();
					main.setTitle("Exemplu Hibernate cu xml-mapping");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		EventQueue.invokeLater(ui);
		
	}

}
