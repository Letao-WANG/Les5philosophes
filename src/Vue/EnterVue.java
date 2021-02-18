package Vue;

import javax.swing.JFrame;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EnterVue extends JFrame implements ActionListener{
	
	private Controller controller;
	private MainVue mainVue;
	private JTextField textFieldPhi;
	private JTextField textFieldPlate;
	
	public EnterVue(Controller controller) {
		this.controller = controller;
		getContentPane().setLayout(null);
		
		JLabel lblP = new JLabel("Please enter the number of philosophers :");
		lblP.setBounds(30, 30, 350, 20);
		getContentPane().add(lblP);
		
		textFieldPhi = new JTextField();
		textFieldPhi.setBounds(30, 60, 100, 20);
		getContentPane().add(textFieldPhi);
		textFieldPhi.setColumns(10);
		
		JLabel labelNumberP = new JLabel("( 2 < N < 11)");
		labelNumberP.setBounds(140, 60, 200, 20);
		getContentPane().add(labelNumberP);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the number of plates ( assiettes ) :");
		lblPleaseEnterThe.setBounds(30, 110, 350, 20);
		getContentPane().add(lblPleaseEnterThe);
		
		textFieldPlate = new JTextField();
		textFieldPlate.setBounds(30, 140, 100, 20);
		getContentPane().add(textFieldPlate);
		textFieldPlate.setColumns(10);
		
		JLabel lblNumberPlate = new JLabel("( N >= numbers of philosophers )");
		lblNumberPlate.setBounds(140, 140, 250, 20);
		getContentPane().add(lblNumberPlate);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(30, 200, 100, 20);
		btnStart.addActionListener(this);
		getContentPane().add(btnStart);
		
		this.setTitle("Les philosophes");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	}

	public int getNumberPhi() {
		return Integer.parseInt(textFieldPhi.getText());
	}

	public int getNumberPlate() {
		return Integer.parseInt(textFieldPlate.getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
		controller.initPhilosophe();
		this.mainVue = new MainVue(controller);
	}
	
	public void repaintMain() {
		this.mainVue.repaint();
	}
}
