package fr.univ_amu.iut.reseauferre.traitement.Wagon;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

/**
 * Wagon transportant des passagers
 */
public class WagonPassager extends Wagon {

	/**
	 * @see Wagon#Wagon()
	 */
    public WagonPassager() {
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
            System.err.println("Ce train ne peut pas posséder des wagons de passagers");
            return false;
    	}
    }

	/**
	 * Renvoie dans une string des informations sur le wagon
	 * @return
	 */
    @Override
    public String toString() {
        return "WagonPassager{id=" + super.getId() + "}";
    }

	/**
	 * Permet de vérfifer qu'un wagon de passager ne peut pas être ajouté à un train de marchandise
	 * @param train
	 * @return
	 */
	@Override
	public boolean correspond(TrainMarchandise train) {
		return false;
	}

	/**
	 * Permet de vérifier qu'un wagon de passager peut être ajouté à un train de marchandise
	 * @param train
	 * @return
	 */
	@Override
	public boolean correspond(TrainPassager train) {
		return true;
	}
}