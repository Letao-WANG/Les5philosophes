package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Assiette;
import Model.Fourchette;
import Model.Philosophe;
import Vue.EnterVue;
import Vue.MainVue;

public class Controller {

	private EnterVue enterVue;
	private Philosophe[] philosophes;
	
	private int nombreAssiettes;

	public Controller() {
		this.enterVue = new EnterVue(this);
	}

	public void initPhilosophe() {
		int nbPhilosophes = getNumberPhi();
		int nbAssiettes = getNumberPlate();
		ArrayList<Fourchette> fourchettes = new ArrayList<Fourchette>();
		ArrayList<Assiette> assiettes = new ArrayList<Assiette>();

		for (int i = 0; i < nbPhilosophes; i++)
			fourchettes.add(new Fourchette(i));

		for (int i = 0; i < nbAssiettes; i++)
			assiettes.add(new Assiette(i));

		Philosophe[] philosophes = new Philosophe[nbPhilosophes];
		for (int i = 0; i < nbPhilosophes; i++)
			philosophes[i] = new Philosophe(this, i, fourchettes, assiettes);

		this.philosophes = philosophes;
		this.nombreAssiettes = assiettes.size();
		
		for (int i = 0; i < nbPhilosophes; i++)
			new Thread(philosophes[i]).start();
	}

	public void start() {
		for (int i = 0; i < getNumberPhi(); i++)
			new Thread(philosophes[i]).start();

	}

	public int getNumberPhi() {
		return enterVue.getNumberPhi();
	}

	/**
	 * get the value from user
	 * @return
	 */
	public int getNumberPlate() {
		return enterVue.getNumberPlate();
	}
	
	/**
	 * get the number from Thread Philosophe
	 * @return0
	 */
	public int getNombreAssiettes() {
		return nombreAssiettes;
	}
	
	public void removeAssiette() {
		nombreAssiettes--;
	}

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
