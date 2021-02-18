package Vue;

import javax.swing.JFrame;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Graphical interface for obtaining information from users
 * 
 * @author letao
 *
 */
public class EnterVue extends JFrame implements ActionListener {

	private Controller controller;
	private MainVue mainVue;
	private JTextField textFieldPhi;
	private JTextField textFieldPlate;
	private JLabel lblErrorP;
	private JLabel lblErrorPlate;

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

		lblErrorP = new JLabel("Illegal value ！");
		lblErrorP.setBounds(30, 85, 200, 20);
		getContentPane().add(lblErrorP);
		lblErrorP.setForeground(Color.RED);
		lblErrorP.setVisible(false);

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

		lblErrorPlate = new JLabel("Illegal value ！");
		lblErrorPlate.setBounds(30, 165, 200, 20);
		getContentPane().add(lblErrorPlate);
		lblErrorPlate.setForeground(Color.RED);
		lblErrorPlate.setVisible(false);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(30, 200, 100, 20);
		btnStart.addActionListener(this);
		getContentPane().add(btnStart);

		this.setTitle("Les philosophes");
		this.setSize(400, 400);
		this.setLocation(50, 50);;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public int getNombrePhi() {
		return Integer.parseInt(textFieldPhi.getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Integer.parseInt(textFieldPhi.getText()) > 11 || Integer.parseInt(textFieldPhi.getText()) < 2) {
			lblErrorP.setVisible(true);
		} else if (Integer.parseInt(textFieldPlate.getText()) < Integer.parseInt(textFieldPhi.getText())) {
			lblErrorPlate.setVisible(true);
		} else {
			dispose();
			controller.setNombreAssiettes(Integer.parseInt(textFieldPlate.getText()));
			controller.initPhilosophe();
			this.mainVue = new MainVue(controller);
		}
	}

	public void repaintMain() {
		this.mainVue.repaint();
	}
}
