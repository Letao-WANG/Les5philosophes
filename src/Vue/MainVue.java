package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;

/**
 * Main graphical interface
 * 
 * @author letao
 *
 */
public class MainVue extends JFrame {

	private Controller controller;
	private int numberPhi;
	private int originX = 150;
	private int originY = 150;
	private int radius = 100;

	public MainVue(Controller controller) {

		this.controller = controller;
		this.numberPhi = controller.getNombrePhi();

		this.setLayout(null);
		this.setTitle("Les philosophes");
		this.setSize(400, 400);
		this.setLocation(50, 50);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Mainly realize the functions of graphics, including the graphics of plates,
	 * forks and philosophers
	 */
	public void paint(Graphics g) {

		// 3 Colors we use
		Color blue = new Color(153, 204, 255);
		Color cyan = new Color(51, 153, 153);
		Color red = new Color(255, 102, 102);

		// Draw Assiettes
		g.setColor(cyan);
		g.fillOval(originX, originY, 100, 100);
		g.setColor(Color.black);
		g.drawString("Assiettes " + String.valueOf(controller.getNombreAssiettes()), 165, 200);
		g.setColor(blue);
		
		/*
		 * r : small radius used to construct fork graphics
		 * R : big radius used to construct fork graphics
		 * bias : graphic offset adjustment
		 */
		int r = 75, R = 125, bias = 25;
		
		for (int i = 0; i < numberPhi; i++) {
			String etat = "";
			switch (controller.getEtat(controller.getPhilosophe(i))) {
			case 0:
				etat = "R";
				g.setColor(blue);
				break;
			case 1:
				etat = "M";
				g.setColor(red);
				break;
			case 2:
				etat = "D";
				g.setColor(blue);
				break;
			}

			// Draw Philosophes
			int pointXPhi = (int) (originX + bias + radius * (Math.cos(2 * i * Math.PI / numberPhi)));
			int pointYPhi = (int) (originY + bias + radius * (Math.sin(2 * i * Math.PI / numberPhi)));
			g.fillOval(pointXPhi, pointYPhi, 50, 50);
			g.setColor(Color.black);
			g.drawString("P" + String.valueOf(i) + ":" + etat, pointXPhi + 5, pointYPhi + bias);

			// Draw Fourchettes
			if (controller.getPhilosophe(i).getFourchette(i).getOccupe())
				g.setColor(red);
			else
				g.setColor(blue);

			g.drawLine((int) (originX + bias * 2 + r * (Math.cos(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + r * (Math.sin(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + R * (Math.cos(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + R * (Math.sin(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))));
		}
	}
}