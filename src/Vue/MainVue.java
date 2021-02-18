package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;

public class MainVue extends JFrame {

	private Controller controller;
	private int numberPlates;
	private int numberPhi;
	private int originX = 150;
	private int originY = 150;
	private int radius = 100;

	public MainVue(Controller controller) {

		this.controller = controller;
		this.numberPhi = controller.getNumberPhi();
		this.numberPlates = controller.getNumberPlate();

		this.setLayout(null);
		this.setTitle("Les philosophes");
		this.setSize(400, 400);
		this.setLocation(50, 50);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	}
//
//	public MainVue() {
//		this.numberPhi = 5;
//		this.numberPlates = 7;
//		this.setLayout(null);
//		this.setTitle("Les philosophes");
//		this.setSize(400, 400);
//		this.setLocation(50, 50);
//		this.setBackground(Color.white);
//		this.setVisible(true);
//	}

	public void paint(Graphics g) {

		Color blue = new Color(153, 204, 255);
		Color cyan = new Color(51, 153, 153);
		Color red = new Color(255, 102, 102);
		
		// Draw Assiettes
		g.setColor(new Color(51, 153, 153));// cyan
		g.fillOval(originX, originY, 100, 100);
		g.setColor(Color.black);
		g.drawString("Assiettes " + String.valueOf(controller.getNombreAssiettes()), 165, 200);
		g.setColor(new Color(153, 204, 255));// blue
		int r = 75, R = 125, bias = 25;
		for (int i = 0; i < numberPhi; i++) {
			
			String etat = "";
			switch(controller.getEtat(controller.getPhilosophe(i))){
				case 0:etat = "R"; g.setColor(blue); break;
				case 1:etat = "M"; g.setColor(red);  break;
				case 2:etat = "D"; g.setColor(blue); break;
			}

			
			// Draw Philosophes
			int pointXPhi = (int) (originX + bias + radius * (Math.cos(2 * i * Math.PI / numberPhi)));
			int pointYPhi = (int) (originY + bias + radius * (Math.sin(2 * i * Math.PI / numberPhi)));
			g.fillOval(pointXPhi,pointYPhi, 50, 50);
			g.setColor(Color.black);
			g.drawString("P" + String.valueOf(i)+":"+etat, pointXPhi+5, pointYPhi+bias);

			// Draw Fourchettes
			if(controller.getPhilosophe(i).getFourchette(i).getOccupe())
				g.setColor(red);
			else g.setColor(blue);

			g.drawLine((int) (originX + bias * 2 + r * (Math.cos(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + r * (Math.sin(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + R * (Math.cos(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))),
					(int) (originX + bias * 2 + R * (Math.sin(2 * i * Math.PI / numberPhi - Math.PI / numberPhi))));
		}
	}

//	public static void main(String[] args) {
//		MainVue mv = new MainVue();
////		while (mv.numberPlates > 0) {
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			mv.repaint();
////			mv.numberPlates--;
////		}
//
//	}
}