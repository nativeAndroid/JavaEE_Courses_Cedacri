package com.example.UI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.classic.Session;

import com.example.Factory;
import com.example.HibernateUtil;
import com.example.PC;

public class JAddPCDialog extends JDialog{
	
	private PC pc = null;
	private JRootPane main_pane = null;
	private JTextField name = null;
	private JTextArea desc = null;
	private JTextField price = null;
	private JButton ok = null;
	private JButton cancel = null;
	private boolean state = false;
	private JDialog me = this;
	
	public JAddPCDialog(final JFrame owner){
		
		super(owner,true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Adaugarea unui calculator nou");

		main_pane = getRootPane();
		main_pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		main_pane.setLayout(new BoxLayout(main_pane, BoxLayout.Y_AXIS));
		name = new JTextField();
		name.setPreferredSize(new Dimension(200,20));
		desc = new JTextArea();
		JScrollPane desc_scroll = new JScrollPane(desc);
		desc_scroll.setPreferredSize(new Dimension(200,200));
		price = new JTextField();
		price.setPreferredSize(new Dimension(200,20));
		JLabel nameLabel = new JLabel("Nume");
		nameLabel.setPreferredSize(new Dimension(100,20));
		JLabel descLabel = new JLabel("Parametri");
		descLabel.setPreferredSize(new Dimension(100,20));
		JLabel priceLabel = new JLabel("Pret");
		priceLabel.setPreferredSize(new Dimension(100,20));
		JPanel line = new JPanel(new FlowLayout());
		line.add(nameLabel);
		line.add(name);
		main_pane.add(line);
		line = new JPanel(new FlowLayout());
		line.add(descLabel);
		line.add(desc_scroll);
		main_pane.add(line);
		line = new JPanel(new FlowLayout());
		line.add(priceLabel);
		line.add(price);
		main_pane.add(line);
		line = new JPanel(new BorderLayout());
		line.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		line.add(ok,BorderLayout.WEST);
		line.add(cancel,BorderLayout.EAST);
		
		main_pane.add(line);
		setResizable(false);
		pack();
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				
			}
		});
		
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				final JProgressDialog jpd = new JProgressDialog(owner, "Please Wait", "Adaugarea unui calculator nou.");

					PC pc = new PC();
					pc.setId_pc(null);
					pc.setName(name.getText());
					pc.setDescr(desc.getText());
					
					double aux = 0;
					try{
						aux = Double.valueOf(price.getText());
					} catch(NumberFormatException e1){
						
					}
					pc.setPrice(aux);
				
					try {
						Factory.getInstance().getPCDAO().add(pc);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				/* Initial data loading from tables */
				/*Runnable r = new Runnable() {
					@Override
					public void run() {

						me.setEnabled(true);
						jpd.setVisible(false);
					}
				};*/
				state = true;
				dispose();				
			}
		});

		setVisible(true);
	}

	public boolean getState(){
		
		return state;
		
	}
	
}
