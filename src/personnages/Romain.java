package personnages;

public class Romain {
	private String nom;
	private int force;
	
	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		assert isInvariantVerified() : "La force doit être positive ou égale à 0";
	}
	
	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}
	
	public void recevoirCoup(int forceCoup) {
		assert forceCoup>=0 : "La force du coup reçu doit être positive";
		int forceAvant = force;
		
		force = force - forceCoup;
		if (force<1) {
			force = 0;
			parler("J'abandonne !");
		} else {
			parler("Aïe");
		}
		
		assert force <= forceAvant: "La force du romain doit avoir diminué";
		assert isInvariantVerified() : "La force doit être positive ou égale à 0";
	}
	
	private boolean isInvariantVerified() {
		if (force>=0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
	}
}
