package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class TestPhilosophe {
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
		while ( nbPhilosophes < 2 || nbPhilosophes > 11 )
		{
			System.out.print("Entre 2 et 11 !\nNombre de philosophes (entre 2 et 11)= ");
			nbPhilosophes = sc.nextInt();
		}
		
		System.out.println("Nombre d'assiettes (>=nbPhilosophe)= ");
		nbAssiettes = sc.nextInt();
		while ( nbAssiettes < nbPhilosophes )
		{
			System.out.print(">= nbPhilosophe !\nNombre d'assiettes (>=nbPhilosophie)= ");
			nbAssiettes = sc.nextInt();
		}
		
		//

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