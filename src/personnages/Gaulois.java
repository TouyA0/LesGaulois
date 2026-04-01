package personnages;

import village_gaulois.Musee;
import village_gaulois.Village;
import objets.Equipement;

public class Gaulois {
	private String nom;
	private int force;
	private int effetPotion = 1;
	private Village village;
	private int nbTrophees;
	private Equipement[] trophees = new Equipement[100];

	
	public Gaulois(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}

//	private String prendreParole() {
//		return "Le gaulois " + nom + " : ";
//	}
	
	public static void main(String[] args) {
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(asterix);
	}

//	@Override
//	public String toString() {
//		return nom;
//	}
	
//	public void frapper(Romain romain) {
//		String nomRomain = romain.getNom();
//		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + nomRomain);
//		int forceCoup = force*effetPotion / 3;
//		
//		if (effetPotion>1) {
//			effetPotion-=1;
//		}
//		
//		romain.recevoirCoup(forceCoup);
//	}
	
	public void boirePotion(int forcePotion) {
		effetPotion = forcePotion;
	}
	
	public void setVillage(Village village) {
		this.village = village;
	}
	
	public void sePresenter() {
		if (village != null && village.getChef() == this) {
			System.out.println("Le Gaulois " + nom + " : \"Bonjour, je m'appelle " + nom + ". Je suis le chef du " + "village : " + village.getNom() + ".\"");
		} else if (village != null) {
			System.out.println("Le Gaulois " + nom + " : \"Bonjour, je m'appelle " + nom + ". J'habite le village : " + village.getNom() + ".\"");
		} else {
			System.out.println("Le Gaulois " + nom + " : \"Bonjour, je m'appelle " + nom + ". Je voyage de villages en villages.");
		}
	}
	
	// rajout TD3
	private String prendreParole() {
		return "Le gaulois " + nom + " : ";
	}
	
	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		Equipement[] equipementsGagnes = romain.recevoirCoup((force / 2) * effetPotion);
		effetPotion--;
		if (effetPotion < 1) {
			effetPotion = 1;
		}
		for (int i = 0; equipementsGagnes != null && i < equipementsGagnes.length; i++, nbTrophees++) {
			this.trophees[nbTrophees] = equipementsGagnes[i];
		}
	}
	
	public void faireUneDonnation(Musee musee) {
	    if (nbTrophees > 0) {
	        System.out.print(prendreParole() + "« Je donne au musee tous mes trophees :\n");
	        for (int i = 0; i < nbTrophees; i++) {
	            if (i == nbTrophees - 1) {
	                System.out.println("- " + trophees[i] + "»");
	            } else {
	                System.out.println("- " + trophees[i]);
	            }
	            musee.donnerTrophees(this, trophees[i]);
	        }
	    }
	}
}
