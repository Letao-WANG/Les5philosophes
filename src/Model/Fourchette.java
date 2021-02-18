package Model;

/**
 * Class Fork
 * @author letao
 *
 */
public class Fourchette {
	private int numero;
	private boolean occupe;

	public Fourchette(int numero) {
		this.numero = numero;
		this.occupe = false;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	public int getNumero() {
		return numero;
	}
	
	public boolean getOccupe() {
		return occupe;
	}


}