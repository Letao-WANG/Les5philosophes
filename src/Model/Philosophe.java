package Model;

import java.util.ArrayList;

import Controller.Controller;

/**
 * Class Philosophe, which is Thread
 * @author letao et Jingyao
 *
 */
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

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3 etat
		while (!assiettes.isEmpty()) {
//		while (true) {
			reflechir();
			manger();
			dormir();
		}
	}

	public void reflechir() {
		try {
			// set state reflechir
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
				try {
					// stop the current thread
					Thread.sleep(1000*60*60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
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
			this.fourchettes.get(this.numero).setOccupe(true);
			this.fourchettes.get((this.numero + 1) % Philosophe.nombre).setOccupe(true);

		}

		// manger
		try {
			// set state manger
			setEtat(1);
			controller.repaint();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// deposer Fourchette
		synchronized (fourchettes) {
			fourchettes.notifyAll();

			this.fourchettes.get(this.numero).setOccupe(false);
			this.fourchettes.get((this.numero + 1) % Philosophe.nombre).setOccupe(false);
		}

	}

	public void dormir() {
		try {
			// set state dormir
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
