package fr.univ_amu.iut.reseauferre.traitement.Wagon;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

import java.io.Serializable;

/**
 * Cette classe représente les wagons appartenant aux trains du réseau. Ils peuvent être de plusieurs types :
 * <ul>
 *     <li>wagon de bétail</li>
 *     <li>wagon de passager</li>
 *     <li>wagon de produits liquides</li>
 * </ul>
 * Un wagon est caractérisé par son ID, spécifique à ce dernier.
 */

public abstract class Wagon implements Serializable {

    /**
     * Contient le nombre de wagon de tout le réseau et sert à attribuer les ID
     */

    public static int nbWagons = 0;

    /**
     * ID du wagon
     */
    private int id;

    /**
     * Constructeur de wagon, incrémente le nombre total de wagon et construit l'ID
     */
    public Wagon() {
        ++nbWagons;
        this.id = nbWagons;
    }

    /**
     * Permet de rajouter le wagon au train passé en paramètre
     * @param train
     * @return
     */
    public abstract boolean connecter(Train train);

    /**
     * Renvoie l'ID du wagon
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant de vérifier la correspondance entre le train auquel on tente d'ajouter le wagon et le wagon en question.
     * @param train
     * @return
     */
    public abstract boolean correspond(TrainMarchandise train);

    /**
     * Méthode permettant de vérifier la correspondance entre le train auquel on tente d'ajouter le wagon et le wagon en question.
     * @param train
     * @return
     */
	public abstract boolean correspond(TrainPassager train);

    /**
     * Renvoie dans une string des informations sur le wagon
     * @return
     */
    public abstract String toString();
}