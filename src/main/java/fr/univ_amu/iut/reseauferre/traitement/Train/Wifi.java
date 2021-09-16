package fr.univ_amu.iut.reseauferre.traitement.Train;

import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;

/**
 * Cette classe permet d'ajouter l'option "Wifi" à un train.
 */
public class Wifi extends TrainPersonnalise {

    /**
     * @see TrainPersonnalise#TrainPersonnalise(Train)
     * @param t
     */
    public Wifi(Train t) {
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
     * Ajoute l'option Wifi au train
     */
    @Override
    public void personnaliser() {
        System.out.println("Activation Wifi");
    }

    /**
     * Permet de déplacer le train, c'est-à-dire diminuer son temps restant de 10 minutes
     * @param min
     */
    @Override
    public void deplacer(long min){
        personnaliser();
        train.deplacer(min);
    }
}
