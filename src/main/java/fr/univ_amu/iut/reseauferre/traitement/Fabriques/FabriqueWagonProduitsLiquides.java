package fr.univ_amu.iut.reseauferre.traitement.Fabriques;

import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonProduitsLiquides;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Fabrique de wagon de produits liquides
 */
public class FabriqueWagonProduitsLiquides implements FabriqueWagon {

    /**
     * Renvoie un objet WagonProduitsLiquides
     * @return
     *
     * @see FabriqueWagonProduitsLiquides
     */
    @Override
    public Wagon creer() {
        return new WagonProduitsLiquides();
    }
}
