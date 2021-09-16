package fr.univ_amu.iut.reseauferre.traitement.Wagon;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

/**
 * Wagon transportant des produits liquides
 */
public class WagonProduitsLiquides extends Wagon {

	/**
	 * @see Wagon#Wagon()
	 */
    public WagonProduitsLiquides() {
        super();
    }

	/**
	 * Ajoute le wagon à la liste de wagon du train passé en paramètre après avoir vérifier si leurs types coincident
	 * @param train
	 * @return
	 */
    public boolean connecter(Train train) {
    	if (train.ajouterWagon(this)) {
    		return true;
    	}
    	else {
            System.err.println("Ce train ne peut pas posséder des wagons de liquides");
            return false;
    	}
    }

	/**
	 * Renvoie dans une string des informations sur le wagon
	 * @return
	 */
    @Override
    public String toString() {
        return "WagonProduitsLiquides{id=" + super.getId() + "}";
    }

	/**
	 * Permet de vérifier qu'un wagon de produit liquide peut être ajouté à un train de marchandise
	 * @param train
	 * @return
	 */
	@Override
	public boolean correspond(TrainMarchandise train) {
		return true;
	}

	/**
	 * Permet de vérifier qu'un wagon de produit liquide ne peut pas être ajouté à un train de passager
	 * @param train
	 * @return
	 */
	@Override
	public boolean correspond(TrainPassager train) {
		return false;
	}
}