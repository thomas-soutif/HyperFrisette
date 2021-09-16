package fr.univ_amu.iut.reseauferre.traitement.Train;

import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;

/**
 * Cette classe permet d'ajouter l'option "Streaming" à un train.
 */
public class Streaming extends TrainPersonnalise {

    /**
     * @see TrainPersonnalise#TrainPersonnalise(Train)
     * @param t
     */
    public Streaming(Train t) {
        super(t);
    }

    /**
     * Permet de rajouter le prix de l'option streaming selon la politique tarifaire passée en paramètre
     * @param po
     */
    @Override
    public void getCout(PolitiqueTarifaire po) {
        po.getPrix(this);
    }

    /**
     * Ajoute l'option Streaming au train
     */
    @Override
    public void personnaliser() {
        System.out.println("Streaming disponible");
    }

    /**
     * Déplcer le train délégué
     * @param min
     */
    @Override
    public void deplacer(long min){
        personnaliser();
        train.deplacer(min);
    }
}
