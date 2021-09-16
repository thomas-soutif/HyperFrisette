package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonBetail;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de wagon de bétail
 */
public class FabriqueWagonBetail implements FabriqueWagon {

    /**
     * Renvoie un objet WagonBetail
     * @return
     *
     * @see WagonBetail
     */
    @Override
    public Wagon creer() {
        return new WagonBetail();
    }
}
