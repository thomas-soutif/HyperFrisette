package fr.univ_amu.iut.reseauferre.traitement.Train;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.*;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.TarifType;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modélisant un train, qui peuvent être de plusieurs type :
 * <ul>
 *     <li>Train de marchandise</li>
 *     <li>Train de passager</li>
 * </ul>
 * Ces trains peuvent avoir des options comme le streaming ou le wifi.
 * Un train est caractérisé par :
 * <ul>
 *     <li>son id (unique pour chaque train</li>
 *     <li>sa taille</li>
 *     <li>le fait qu'il soit en panne ou non</li>
 *     <li>l'entreprise qui le possède</li>
 *     <li>une liste de wagon</li>
 * </ul>
 *
 * @see Entreprise
 * @see Wagon
 */
public abstract class Train implements TarifType, Serializable {

    /**
     * Cet attribut statique indique le nombre de train instancié jusque maintenant.
     */
    protected static int nbTrains = 0;

    /**
     * ID du train, spécifique à ce dernier
     */
    protected int id;

    /**
     * Taille du train
     */
    protected double taille;

    /**
     * Temps restant qu'il reste au train sur son sillon
     */
    protected LocalTime tempsRestant = LocalTime.of(0,0);

    /**
     * Si vrai, alors le train est Hors Service. Il fonctionne normalement si faux
     */
    protected boolean enPanne;

    /**
     * Correspong au dernier sillon d'un train sur son trajet
     */
    protected int prioriteMax;

    /**
     * Liste des wagons du train
     * @see Wagon
     */
    protected List<Wagon> wagons = new ArrayList<>();

    /**
     * Entreprise possédant le train
     * @see Entreprise
     */
    protected Entreprise entreprise;

    /**
     * Renvoie l'ID du train
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * Affecte la priorité max du train
     * @param prioriteMax
     */
    public void setPrioriteMax(int prioriteMax) {
        this.prioriteMax = prioriteMax;
    }

    /**
     * Renvoie la priorité max du train
     * @return
     */
    public int getPrioriteMax() {
        return prioriteMax;
    }

    /**
     * Renvoie le cout selon la politique tarfaire que l'on souhaite appliquer.
     * @param po
     *
     * @see PolitiqueTarifaire
     */
    public abstract void getCout(PolitiqueTarifaire po);

    /**
     * Renvoie l'état de fonctionnement du train
     * @return
     *
     * @see Train#enPanne
     */
    public boolean enPanne() {
        return enPanne;
    }

    /**
     * Permet de déplacer le train, c'est-à-dire diminuer son temps restant de 10 minutes
     * @param min
     */
    public abstract void deplacer(long min);

    /**
     * Permet de fixer l'entreprise qui possède le train
     * @param entreprise
     *
     * @see Entreprise
     */
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    /**
     * Renvoie l'entreprise qui possède le train
     * @return
     *
     * @see Entreprise
     */
    public Entreprise getEntreprise() {
        return entreprise;
    }

    /**
     * Ajoute un wagon dns le train
     * @param wagon
     */
    protected void addWagonLocal(Wagon wagon) {
    	wagons.add(wagon);
	}

    /**
     * Permet d'ajouter un wagon (d'un type correspondant bien au type de train en question)
     * @param wagon
     * @return
     *
     * @see Wagon
     */
    public abstract boolean ajouterWagon(Wagon wagon);

    /**
     * Permet d'indiquer le train est en panne ou qu'il ne l'est plus
     * @param panne
     *
     * @see Train#enPanne
     */
    public void setPanne(boolean panne){
        this.enPanne = panne;
    }

    /**
     * Fixe le temps en heures et minutes qu'il reste au train
     * @param tempsRestant
     */
    public void setTempsRestant(LocalTime tempsRestant) {
        this.tempsRestant = tempsRestant;
    }

    /**
     * Renvoie le temps restant du train sur son sillon
     * @return
     */
    public LocalTime getTempsRestant() {
        return tempsRestant;
    }

    /**
     * Renvoie la taille du train
     * @return
     */
    public double getTaille() {
        return taille;
    }

    /**
     * Méthode permettant de vérifier la correspondance entre l'entreprise à laquelle on tente d'ajouter le train et le train en question.
     * @param entreprise
     * @return
     */
    public abstract boolean correspond(EntrepriseAnimaux entreprise);

    /**
     * Méthode permettant de vérifier la correspondanceentre l'entreprise à laquelle on tente d'ajouter le train et le train en question.
     * @param entreprise
     * @return
     */
	public abstract boolean correspond(EntrepriseCargo entreprise);

    /**
     * Méthode permettant de vérifier la correspondance entre l'entreprise à laquelle on tente d'ajouter le train et le train en question.
     * @param entreprise
     * @return
     */
	public abstract boolean correspond(EntreprisePassager entreprise);

    /**
     * Renvoie une description du train dans une string
     * @return
     */
	public abstract String toString();

}
