package personnages;

import objets.Equipement;

public class Romain {
	private String nom;
	private int force;
	private Equipement[] equipements = new Equipement[2];
	private int nbEquipement = 0;
	
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
	
//	public void recevoirCoup(int forceCoup) {
//		assert forceCoup>0 : "La force du coup reçu doit être positive";
//		int forceAvant = force;
//		
//		force = force - forceCoup;
//		if (force<1) {
//			force = 0;
//			parler("J'abandonne !");
//		} else {
//			parler("Aïe");
//		}
//		
//		assert force <= forceAvant: "La force du romain doit avoir diminué";
//		assert isInvariantVerified() : "La force doit être positive ou égale à 0";
//	}
	
	private boolean isInvariantVerified() {
		if (force>=0) {
			return true;
		}
		return false;
	}
	
	private void ajouterEquipement(Equipement equipement) {
		equipements[nbEquipement] = equipement;
		nbEquipement++;
		System.out.println("Le soldat " + nom + " s'équipe avec un " + equipement + ".");
	}
	
	public void sEquiper(Equipement equipement) {
		switch (nbEquipement) {
		case 2: {
			System.out.println("Le soldat " + nom + " est déjà bien protégé !");
			break;
		}
		case 1: {
			if (equipements[0] == equipement) {
				System.out.println("Le soldat " + nom + " possède déjà un " + equipement + " !");
			} else {
				ajouterEquipement(equipement);
			}
			break;
		}
		default:
			ajouterEquipement(equipement);
			break;
		}
	}
	
	
	
	
	// rajout TD3
	public Equipement[] recevoirCoup(int forceCoup) {
	    Equipement[] equipementEjecte = null;
	    
	    int forceCoupApresResistance = calculResistanceEquipement(forceCoup);  // Utilisez une variable temporaire
	    
	    force -= forceCoupApresResistance;
	    
	    if (force <= 0) {
	        force = 0;
	        equipementEjecte = ejecterEquipement();
	        parler("J'abandonne...");
	    } else {
	        parler("Aïe");
	    }
	    return equipementEjecte;
	}
	
	private int calculResistanceEquipement(int forceCoup) {
	    String texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
	    int resistanceEquipement = 0;
	    if (nbEquipement != 0) {
	        for (int i = 0; i < nbEquipement; i++) {
	            if ((equipements[i] != null && equipements[i].equals(Equipement.BOUCLIER))) {
	                resistanceEquipement += 6;
	            } else {
	                resistanceEquipement += 3;
	            }
	        }
	        
	        int forceCoupApresResistance = forceCoup - resistanceEquipement;
	        
	        if (forceCoupApresResistance <= 0) {
	            texte += "\nMais heureusement, grâce à mon équipement sa force a été complètement absorbée.";
	        } else {
	            texte += "\nMais heureusement, grâce à mon équipement sa force a été diminuée de " + resistanceEquipement + " !";
	        }
	    }
	    parler(texte);
	    forceCoup -= resistanceEquipement;
	    if (forceCoup < 0) {
	        forceCoup = 0;
	    }
	    return forceCoup;
	}
		
	private Equipement[] ejecterEquipement() {
		Equipement[] equipementEjecte = new Equipement[nbEquipement];
		System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipement; i++) {
			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		nbEquipement = 0;
		return equipementEjecte;
	}
	
	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}
	
	public int getNbEquipement() {
	    return nbEquipement;
	}
	
	
	

	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
		
		System.out.println(Equipement.CASQUE);
		System.out.println(Equipement.BOUCLIER);
		
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.BOUCLIER);
	}
}
