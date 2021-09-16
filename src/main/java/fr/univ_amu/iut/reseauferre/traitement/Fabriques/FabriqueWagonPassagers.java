package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonPassager;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de wagon de passagers
 */
public class FabriqueWagonPassagers implements FabriqueWagon {

    /**
     * Renvoie un objet WagonPassager
     * @return
     *
     * @see WagonPassager
     */
    @Override
    public Wagon creer() {
        return new WagonPassager();
    }
}
