package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de train de passager
 */
public class FabriqueTrainPassagers implements FabriqueTrain {

    /**
     * Prend en paramètre la taille du train et renvoie un objet TrainPassager
     * @param taille
     * @return
     *
     * @see TrainPassager
     */
    @Override
    public Train creer(double taille) {
        return new TrainPassager(taille);
    }
}
