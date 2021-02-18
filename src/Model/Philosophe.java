package Model;

import java.util.ArrayList;

import Controller.Controller;

public class Philosophe implements Runnable {
	private Controller controller;
	private int numero;
	private static int nombre;
	private ArrayList<Fourchette> fourchettes;
	private ArrayList<Assiette> assiettes;

	// l'etat de philosophe 0 = reflechir, 1 = manger, 2 = dormir
	private int etat;

	public Philosophe(Controller controller, int numero, ArrayList<Fourchette> fourchettes,
			ArrayList<Assiette> assiettes) {
		this.controller = controller;
		this.numero = numero;
		this.fourchettes = fourchettes;
		this.assiettes = assiettes;
		Philosophe.nombre++;
	}

	public Philosophe(int numero, ArrayList<Fourchette> fourchettes, ArrayList<Assiette> assiettes) {
		this.numero = numero;
		this.fourchettes = fourchettes;
		this.assiettes = assiettes;
		Philosophe.nombre++;
	}

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!assiettes.isEmpty()) {
			reflechir();
			manger();
			dormir();
		}
	}

	public void reflechir() {
		try {
			System.out.println("Philosophe" + this.numero + " est en train de réfléchir");
			setEtat(0);
			controller.repaint();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void manger() {

		// prendreAssiete
		synchronized (assiettes) {
			if (assiettes.isEmpty()) {
				System.out.println("Pas d'assietes! ");
				try {
					Thread.sleep(1000*60*60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Philosophe" + this.numero + " prend l'assiette"
						+ assiettes.get(assiettes.size() - 1).getNumero());
				assiettes.remove(assiettes.size() - 1);
				controller.removeAssiette();
			}
		}

		// prendreFourchette
		synchronized (fourchettes) {
			while (this.fourchettes.get(this.numero).getOccupe() == true
					|| this.fourchettes.get((this.numero + 1) % Philosophe.nombre).getOccupe() == true) {
				try {
					fourchettes.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fourchettes.notify();
			System.out.println("Philosophe" + this.numero + " prend la fourchette gauche"
					+ this.fourchettes.get(this.numero).getNumero() + " et la fourchette droit "
					+ this.fourchettes.get((this.numero + 1) % Philosophe.nombre).getNumero());
			this.fourchettes.get(this.numero).setOccupe(true);
			this.fourchettes.get((this.numero + 1) % Philosophe.nombre).setOccupe(true);

		}

		// manger
		try {
			System.out.println("Philosophe" + this.numero + " est en train de manger");
			setEtat(1);
			controller.repaint();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// deposer Fourchette
		synchronized (fourchettes) {
			fourchettes.notify();

			System.out.println("Philosophe" + this.numero + " depose la fourchette gauche"
					+ this.fourchettes.get(this.numero).getNumero() + " et la fourchette droit "
					+ this.fourchettes.get((this.numero + 1) % Philosophe.nombre).getNumero());
			this.fourchettes.get(this.numero).setOccupe(false);
			this.fourchettes.get((this.numero + 1) % Philosophe.nombre).setOccupe(false);
		}

	}

	public void dormir() {
		try {
			System.out.println("Philosophe" + this.numero + " est en train de dormir");
			setEtat(2);
			controller.repaint();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getEtat() {
		return this.etat;
	}

	public int getNumero() {
		return numero;
	}

	public static int getNombre() {
		return nombre;
	}
	
	public Fourchette getFourchette(int numero) {
		return fourchettes.get(numero);
	}

	public int getNombreAssiettes() {
		return this.assiettes.size();
	}
}
