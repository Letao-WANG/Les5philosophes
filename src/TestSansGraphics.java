
import java.util.ArrayList;
import java.util.Scanner;

import Model.Assiette;
import Model.Fourchette;

/**
 * Test the program without a graphical interface
 * @author letao
 *
 */
public class TestSansGraphics {

	public static void main(String[] args) {
		int nbPhilosophes = 5;
		int nbAssiettes = 5;
		ArrayList<Fourchette> fourchettes = new ArrayList<Fourchette>();
		ArrayList<Assiette> assiettes = new ArrayList<Assiette>();

		// Interaction de l'utilisateur
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre de philosophes (entre 2 et 11)= : ");

		System.out.println("Nombre de philosophes (entre 2 et 11)= ");
		nbPhilosophes = sc.nextInt();
		while (nbPhilosophes < 2 || nbPhilosophes > 11) {
			System.out.print("Entre 2 et 11 !\nNombre de philosophes (entre 2 et 11)= ");
			nbPhilosophes = sc.nextInt();
		}

		System.out.println("Nombre d'assiettes (>=nbPhilosophe)= ");
		nbAssiettes = sc.nextInt();
		while (nbAssiettes < nbPhilosophes) {
			System.out.print(">= nbPhilosophe !\nNombre d'assiettes (>=nbPhilosophie)= ");
			nbAssiettes = sc.nextInt();
		}

		/*
		 * initialization
		 */
		for (int i = 0; i < nbPhilosophes; i++)
			fourchettes.add(new Fourchette(i));

		for (int i = 0; i < nbAssiettes; i++)
			assiettes.add(new Assiette(i));

		Philosophe[] philosophes = new Philosophe[nbPhilosophes];
		for (int i = 0; i < nbPhilosophes; i++)
			philosophes[i] = new Philosophe(i, fourchettes, assiettes);

		for (int i = 0; i < nbPhilosophes; i++)
			new Thread(philosophes[i]).start();
	}
}

/**
 * Class Philosophe without a graphical interface
 * @author letao
 *
 */
class Philosophe implements Runnable {
	private int numero;
	private static int nombre;
	private ArrayList<Fourchette> fourchettes;
	private ArrayList<Assiette> assiettes;

	// l'etat de philosophe 0 = reflechir, 1 = manger, 2 = dormir
	private int etat;

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
		// 3 etat
		while (!assiettes.isEmpty()) {
			reflechir();
			manger();
			dormir();
		}
	}

	public void reflechir() {
		try {
			System.out.println("Philosophe" + this.numero + " est en train de réfléchir");
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
				// stop the current thread
				System.exit(0);
			} else {
				System.out.println("Philosophe" + this.numero + " prend l'assiette"
						+ assiettes.get(assiettes.size() - 1).getNumero());
				assiettes.remove(assiettes.size() - 1);
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}