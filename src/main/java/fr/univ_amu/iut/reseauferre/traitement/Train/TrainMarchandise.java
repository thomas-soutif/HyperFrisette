package fr.univ_amu.iut.reseauferre.traitement.Train;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.*;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;

import java.time.LocalTime;

/**
 * Type de train transportant des marchandises. Il ne peut contenir que des wagons de produits liquides et des wagons de bétail.
 *
 * @see fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonBetail
 * @see fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonProduitsLiquides
 */
public class TrainMarchandise extends Train {

	/**
	 * Constructeur du train de marchandise. Il incrémente le nombre de train total et fixe sa taille grace à la taille passée en paramètre.
	 * @param taille
	 */
    public TrainMarchandise(double taille) {
		++nbTrains;
    	super.id = nbTrains;
    	super.taille = taille;
    }

	/**
	 * Renvoie le prix pour un train de marchandise en fonction de la politique tarifaire passée en paramètre.
	 * @param po
	 *
	 * @see PolitiqueTarifaire
	 */
	@Override
	public void getCout(PolitiqueTarifaire po) {
    	po.getPrix(this);

	}

	/**
	 * Permet de déplacer le train, c'est-à-dire diminuer son temps restant de 10 minutes
	 * @param min
	 */
	@Override
	public void deplacer(long min) {
        LocalTime dixMin = LocalTime.of(0,0).plusMinutes(min);
        super.tempsRestant = super.tempsRestant.minusMinutes(dixMin.getMinute());

        System.out.println("\n* Le train de Marchandises " + id + " se déplace dans le réseau *");
	}

	/**
	 * Permet d'indiquer que le train de marchandise ne peut pas appartenir à une entreprise d'animaux.
	 * @param entreprise
	 * @return
	 *
	 * @see EntrepriseAnimaux
	 */
	@Override
	public boolean correspond(EntrepriseAnimaux entreprise) {
		return false;
	}

	/**
	 * Permet d'indiquer que le train de marchandise peut appartenir à une entreprise cargo.
	 * @param entreprise
	 * @return
	 *
	 * @see EntrepriseCargo
	 */
	@Override
	public boolean correspond(EntrepriseCargo entreprise) {
		return true;
	}

	/**
	 * Permet d'indiquer que le train de marchandise ne peut pas appartenir à une entreprise de passager.
	 * @param entreprise
	 * @return
	 *
	 * @see EntreprisePassager
	 */
	@Override
	public boolean correspond(EntreprisePassager entreprise) {
		return false;
	}

	/**
	 * Renvoie dans une string des informations sur le train.
	 * @return
	 */
	@Override
	public String toString() {
        String stringPanne;
        if (this.enPanne) stringPanne = "(en panne)";
        else stringPanne = "(en marche)";
        String mesWagons = "Le train marchandise" + this.getId() + stringPanne + " qui appartient à l'entreprise "+ this.entreprise.getNom() + " possède les wagons : ";
        for (Wagon wagon : wagons) {
            mesWagons += wagon.toString();
            if (wagon != wagons.get(wagons.size()-1))
                mesWagons += ", ";
        }
        return mesWagons;
    }

	/**
	 * Permet d'ajouter des wagons d'un type correspondant à celui du train.
	 * @param wagon
	 * @return
	 */
	@Override
	public boolean ajouterWagon(Wagon wagon) {
		if (wagon.correspond(this)) {
			this.addWagonLocal(wagon);
			System.out.println("\n* Le wagon " + wagon.getId() + " a été ajouté au train " + this.id + " *\n");
			return true;
		}
		return false;
	}

	

} // TrainPassager

