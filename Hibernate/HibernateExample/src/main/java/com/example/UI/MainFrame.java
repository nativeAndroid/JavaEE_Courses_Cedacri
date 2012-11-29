package com.example.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.hibernate.classic.Session;

import com.example.COM;
import com.example.Factory;
import com.example.HibernateUtil;
import com.example.PC;

public class MainFrame extends JFrame {

	private JRootPane main_panel = null;
	private JTabbedPane tabbed_pane = null;
	private JTable pc_table = null;
	private JTable com_table = null;
	private ArrayList<PC> pcs = new ArrayList<>();
	private int[] pcs_nr_unit = null;
	private ArrayList<COM> coms = new ArrayList<>();
	private Map<Integer, PC> relat = new TreeMap<>();
	private JButton buy = null;

	public MainFrame() throws SQLException {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		main_panel = getRootPane();
		main_panel.setLayout(new BorderLayout());
		main_panel.setBackground(Color.white);
		readPcs();
		tabbed_pane = new JTabbedPane();
		tabbed_pane.addTab("Calculatoare", makePCPanel());
		tabbed_pane.setMnemonicAt(0, KeyEvent.VK_1);

		readComs();
		tabbed_pane.addTab("Comenzi", makeCOMPanel());
		tabbed_pane.setMnemonicAt(0, KeyEvent.VK_2);
		tabbed_pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbed_pane.setVisible(true);

		main_panel.add(tabbed_pane, BorderLayout.CENTER);
		main_panel.setPreferredSize(new Dimension(500, 500));

		buy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Session session = null;

				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				for(int i = 0; i < pcs_nr_unit.length; i++)
					if(pcs_nr_unit[i] > 0) {
						
						COM com = new COM();
						com.setId_com(null);
						com.setId_pc(pcs.get(i).getId_pc());
						com.setNr_unit(pcs_nr_unit[i]);
						try {
							Factory.getInstance().getCOMDAO().add(com);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pcs_nr_unit[i] = 0;
						
					}

				session.getTransaction().commit();
				try {
					readComs();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				((AbstractTableModel)com_table.getModel()).fireTableDataChanged();
				com_table.repaint();
				
				
			}
		});
		
		setResizable(false);
		pack();
		setVisible(true);

	}

	public JPanel makePCPanel() {

		JPanel aux = new JPanel(new BorderLayout());
		pc_table = new JTable();

		TableModel tm = new AbstractTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4786424828923525420L;

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				case 3:
					return true;
				default:
					return false;
				}
			}

			@Override
			public String getColumnName(int column) {

				switch (column) {

				case 0:
					return "Name";
				case 1:
					return "Description";
				case 2:
					return "Price";
				case 3:
					return "Nr Unitati";

				}
				return null;

			}

			@Override
			public Object getValueAt(int arg0, int arg1) {

				switch (arg1) {

				case 0:
					return pcs.get(arg0).getName();
				case 1:
					return pcs.get(arg0).getDesc();
				case 2:
					return pcs.get(arg0).getPrice();
				default:
					return pcs_nr_unit[arg0];

				}

			}

			@Override
			public void setValueAt(Object arg0, int arg1, int arg2) {

				int newValue = 0;
				try {
					newValue = Integer.valueOf((String) arg0);
				} catch (Exception e) {
				}

				if (arg2 == 3)
					pcs_nr_unit[arg1] = newValue;
			}

			@Override
			public int getRowCount() {

				return pcs.size();

			}

			@Override
			public int getColumnCount() {

				return 4;

			}
		};

		pc_table.setModel(tm);
		JScrollPane sp = new JScrollPane(pc_table);
		sp.setPreferredSize(new Dimension(450, 300));
		aux.add(sp, BorderLayout.CENTER);
		buy = new JButton("Buy");
		aux.add(buy, BorderLayout.SOUTH);
		aux.setPreferredSize(new Dimension(450, 300));
		return aux;

	}

	public JPanel makeCOMPanel() {

		JPanel aux = new JPanel(new BorderLayout());
		com_table = new JTable();

		TableModel tm = new AbstractTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4786424828923525420L;

			@Override
			public String getColumnName(int column) {

				switch (column) {

				case 0:
					return "ID Command";
				case 1:
					return "PC Name";
				case 2:
					return "Unit Price";
				case 3:
					return "Nr Units";
				case 4:
					return "Total Price";

				}
				return null;

			}

			@Override
			public Object getValueAt(int arg0, int arg1) {

				switch (arg1) {

				case 0:
					return coms.get(arg0).getId_com();
				case 1:
					return relat.get(coms.get(arg0).getId_com()).getName();
				case 2:
					return relat.get(coms.get(arg0).getId_com()).getPrice();
				case 3:
					return coms.get(arg0).getNr_unit();
				default:
					return coms.get(arg0).getNr_unit()
							* relat.get(coms.get(arg0).getId_com()).getPrice();

				}

			}

			@Override
			public int getRowCount() {

				return coms.size();

			}

			@Override
			public int getColumnCount() {

				return 5;

			}
		};

		com_table.setModel(tm);
		JScrollPane sp = new JScrollPane(com_table);
		sp.setPreferredSize(new Dimension(450, 300));
		aux.add(sp, BorderLayout.CENTER);
		aux.setPreferredSize(new Dimension(450, 300));
		return aux;

	}
	
	public void readPcs(){
		
		pcs = Factory.getInstance().getPCDAO().getAll();
		pcs_nr_unit = new int[pcs.size()];
		
	}
	
	public void readComs() throws SQLException{
		
		coms.clear();
		relat.clear();
		coms = Factory.getInstance().getCOMDAO().getAll();

		relat = new TreeMap<>();
		for (COM i : coms)
			relat.put(i.getId_com(), Factory.getInstance().getPCDAO()
					.getByPCId(i.getId_pc()));

		
	}
}
