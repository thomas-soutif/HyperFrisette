package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de train
 */
public interface FabriqueTrain {
    public Train creer(double taille);
}
