package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de train de marchandise
 */
public class FabriqueTrainMarchandises implements FabriqueTrain {

    /**
     * Prend en paramètre la taille du train et renvoie un objet TrainMarchandise
     * @param taille
     * @return
     *
     * @see TrainMarchandise
     */
    @Override
    public Train creer(double taille) {
        return new TrainMarchandise(taille);
    }
}
