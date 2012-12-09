package com.example;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.jar.JarEntry;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.example.UI.JAddPCDialog;
import com.example.UI.MainFrame;

public class Main {

	public static void main(String a[]) {

		AnnotationConfiguration ac = new AnnotationConfiguration();
		ac.addAnnotatedClass(PC.class);
		ac.addAnnotatedClass(COM.class);
		ac.configure();
		
		
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
					main.setTitle("Exemplu Hibernate cu annotation-mapping");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		EventQueue.invokeLater(ui);
		
	}

}
