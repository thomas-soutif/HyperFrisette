package fr.univ_amu.iut.reseauferre.traitement.Tarif;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.*;

/**
 * Interface permettant de regrouper tous les différents types de tarifs proposés.
 */
public interface PolitiqueTarifaire {

    /**
     * Calcul du prix pour un train de passager
     * @param trainPassager
     */
    public void getPrix(TrainPassager trainPassager);

    /**
     * Calcul du prix pour un train avec l'option streaming
     * @param trainStream
     */
    public void getPrix(Streaming trainStream);

    /**
     * Calcul du prix pour un train avec l'option Wifi
     * @param trainWifi
     */
    public void getPrix(Wifi trainWifi);

    /**
     * Calcul du prix pour un train de marchandise
     * @param trainMarchandise
     */
    public void getPrix(TrainMarchandise trainMarchandise);

    /**
     * Calcul du prix en fonction du sillon horraire utilisé.
     * @param sillon
     */
    public void getPrix(Sillon sillon);


    /**
     * Renvoie le cout total.
     * @return
     */
    public double getCoutTotal();
}
