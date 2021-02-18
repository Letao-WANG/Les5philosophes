package Controller;

import java.util.ArrayList;
import Model.Assiette;
import Model.Fourchette;
import Model.Philosophe;
import Vue.EnterVue;

/**
 * The controller of MVC
 * 
 * @author letao
 *
 */

public class Controller {

	private EnterVue enterVue;
	private Philosophe[] philosophes;
	private int nombreAssiettes;

	public Controller() {
		this.enterVue = new EnterVue(this);
	}

	/**
	 * Initialize fourchette(fork), assiette(plate) and thread Philosophe
	 */
	public void initPhilosophe() {
		int nbPhilosophes = getNombrePhi();
		ArrayList<Fourchette> fourchettes = new ArrayList<Fourchette>();
		ArrayList<Assiette> assiettes = new ArrayList<Assiette>();

		for (int i = 0; i < nbPhilosophes; i++)
			fourchettes.add(new Fourchette(i));

		for (int i = 0; i < nombreAssiettes; i++)
			assiettes.add(new Assiette(i));

		Philosophe[] philosophes = new Philosophe[nbPhilosophes];
		for (int i = 0; i < nbPhilosophes; i++)
			philosophes[i] = new Philosophe(this, i, fourchettes, assiettes);

		this.philosophes = philosophes;

		/*
		 * start the thread
		 */
		for (int i = 0; i < nbPhilosophes; i++)
			new Thread(philosophes[i]).start();
	}

	public int getNombrePhi() {
		return enterVue.getNombrePhi();
	}

	public int getNombreAssiettes() {
		return nombreAssiettes;
	}

	public void removeAssiette() {
		nombreAssiettes--;
	}

	public void setNombreAssiettes(int nombre) {
		this.nombreAssiettes = nombre;
	}

	/**
	 * get the state of philosophe
	 * 
	 * @param philosophe
	 * @return 0=reflechir, 1=manger, 2=dormir
	 */
	public int getEtat(Philosophe philosophe) {
		return philosophe.getEtat();
	}

	public Philosophe getPhilosophe(int numero) {
		return philosophes[numero];
	}

	public void repaint() {
		enterVue.repaintMain();
	}

	public static void main(String[] args) {
		new Controller();
	}
}
