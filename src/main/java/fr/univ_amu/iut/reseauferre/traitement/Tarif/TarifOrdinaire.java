package fr.univ_amu.iut.reseauferre.traitement.Tarif;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.*;

/**
 * Type de tarif ordinaire. Cette classe implémente l'interface PolitiqueTarifaire et va définir comment calculer le cout selon différents paramètres.
 *
 * @see PolitiqueTarifaire
 */
public class TarifOrdinaire implements PolitiqueTarifaire {

    /**
     * Le cout total du trajet est stocké dans cet attribut
     */
    private double coutTotal;

    /**
     * Prend en paramètre un train de passager et selon sa taille, ajoute un certain prix dans le coutTolal
     * @param trainPassager
     */
    @Override
    public void getPrix(TrainPassager trainPassager) {

        coutTotal += 500;
        coutTotal += trainPassager.getTaille()* 10 * 5; // Pour chaque taille on estime a 10 personnes le nombre, et l'entreprise doit payer 5 euros d'assurance par passager.
    }

    /**
     * Prend en paramètre un train avec l'option Streaming et  ajoute un certain prix dans le coutTolal
     * @param trainStream
     */
    @Override
    public void getPrix(Streaming trainStream) {

        trainStream.getTrain().getCout(this); // On ajoute le cout du type de train (c'est un train personnalisé
        coutTotal += 10; // On fait payer 10 euros parce que le poid des télévisions alourdie le train..

    }

    /**
     * Prend en paramètre un train avec l'option Wi-Fi et  ajoute un certain prix dans le coutTolal
     * @param trainWifi
     */
    @Override
    public void getPrix(Wifi trainWifi) {

    }

    /**
     * Prend en paramètre un train de marchandise et  ajoute un certain prix dans le coutTolal
     * @param trainMarchandise
     */
    @Override
    public void getPrix(TrainMarchandise trainMarchandise) {

        coutTotal += 50;

    }

    /**
     * Prend en paramètre un sillon et ajoute à coutTotal le cout de l'utilisation du sillon selon son horaire et selon la ligne sur laquel il est.
     * @param sillon
     *
     * @see Sillon
     */
    @Override
    public void getPrix(Sillon sillon) {

        coutTotal += sillon.getPrixHoraire();
        coutTotal += sillon.getLigne().getPrixLigne();
        sillon.getTrain().getCout(this);
    }

    /**
     * Renvoie l'attribut coutTotal
     * @return
     */
    @Override
    public double getCoutTotal() {
        return coutTotal;
    }
}
