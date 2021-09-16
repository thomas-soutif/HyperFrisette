package fr.univ_amu.iut.reseauferre.traitement.StructureReseau;

/**
 * Created by Vincent on 30/10/2017.
 */

/**
 * Enumération de toutes les gares prises en charge dans le réseau.
 * Une gare est caractérisée seulement par son nom.
 */
public enum Gare {
    Marseille ("Marseille"),
    Paris ("Paris"),
    Bordeaux ("Bordeaux"),
    Nice ("Nice"),
    Nantes ("Nantes"),
    Lille ("Lille"),
    Strasbourg ("Strasbourg"),
    Toulouse ("Toulouse"),
    Lyon ("Lyon"),
    Rennes ("Rennes"),
    Rouen ("Rouen"),
    Orleans ("Orleans");

    /** Nom de la gare
     */
    private String nom;

    /**
     * Constructeur d'une gare, prend en paramètre le nom de la gare.
     * @param nom
     */
    Gare(String nom){
        this.nom = nom;
    }

    /**
     * Renvoie une string contenant le nom de la ville de la gare.
     * @return
     */
    public String toString() {
        return this.nom;
    }

    /**
     * Renvoie le seul attribut de Gare.
     * @return
     */
    public String getNom() {
        return nom;
    }


}
