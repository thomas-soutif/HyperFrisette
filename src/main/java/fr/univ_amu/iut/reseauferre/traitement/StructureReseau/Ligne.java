package fr.univ_amu.iut.reseauferre.traitement.StructureReseau;

import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.TarifType;

import java.io.Serializable;

/**
 * Created by Vincent on 30/10/2017.
 */


/**
 * Ligne est la classe représentant les lignes du réseau. Elle ne s'emprunte que dans un sens. Elle est caractérisée par :
 * <ul>
 *     <li>Deux gares</li>
 *     <li>Un nom pour un sens de circulation</li>
 *     <li>Un nom pour le sens de circulation inverse</li>
 * </ul>
 *
 * @see Gare
 */
public class Ligne implements Serializable {

    /**
     * Gare de "départ" de la ligne
     *
     * @see Gare
     */
    private Gare gareDepart;

    /**
     * Gare "d'arrivée" de la ligne
     *
     * @see Gare
     */
    private Gare gareArrivee;

    /**
     * Nom de la ligne
     */
    private String libelle;

    /**
     * Constructeur de la classe Ligne. Prend en paramètre deux gares et contruit les deux noms de la ligne a partir de ces dernières.
     * @param gareDepart
     * @param gareArrivee
     *
     * @see Gare
     */
    public Ligne(Gare gareDepart, Gare gareArrivee) {
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.libelle = gareDepart + "_" + gareArrivee;
    }

    /**
     * Renvoie le nom "de base" de la ligne dans une string
     * @return
     */
    public String toString() {
        return this.libelle;
    }

    /**
     * Renvoie la gare de départ de la ligne.
     * @return
     *
     * @see Gare
     */
    public Gare getGareDepart() {
        return this.gareDepart;
    }

    /**
     * Renvoie la gare d'arrivée de la ligne.
     * @return
     *
     * @see Gare
     */
    public Gare getGareArrivee() {
        return this.gareArrivee;
    }

    /**
     * Renvoie le nom de la ligne
     * @return
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Renvoie le prix de la ligne, calculé à partir du prix asscoié à chaque gare prise en charge par le réseau.
     * @return
     */
    public double getPrixLigne() {

        return getPrixGare(this.gareDepart) + getPrixGare(this.gareArrivee);
    }


    /**
     * Fixe le prix de base d'une gare.
     * @param gare
     * @return
     */
    private double getPrixGare(Gare gare) {
        switch (gare) {
            case Paris:
                return 50;
            case Lille:
                return 20;
            case Rennes:
                return 20;
            case Bordeaux:
                return 30;
            case Lyon:
                return 70;
            case Marseille:
                return 15;
            case Nice:
                return 100;
            case Nantes:
                return 20;
            case Strasbourg:
                return 20;
            case Toulouse:
                return 20;
            case Rouen:
                return 40;
            case Orleans:
                return 20;
            default:
                return 0;
        }


    }

}
