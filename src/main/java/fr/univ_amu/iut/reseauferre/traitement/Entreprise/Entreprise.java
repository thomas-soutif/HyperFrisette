package fr.univ_amu.iut.reseauferre.traitement.Entreprise;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Train.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entreprise est la classe représentant les entreprises. Ces dernières peuvent être de plusieurs types :
 * <ul>
 *     <li> Entreprise Animaux : elle s'occupe principalement du transport d'animaux</li>
 *     <li> Entreprise Cargo : elle s'occupe du transport de marchandise</li>
 *     <li> Entreprise Passager : la plus classique, celle qui s'occupe du trajet des particuliers</li>
 * </ul>
 * Une entreprise est caractérisée par les éléments suivants :
 * <ul>
 *     <li>Son nom</li>
 *     <li>Son numéro SIREN</li>
 *     <li>La liste de trains qu'elle possède</li>
 * </ul>
 *
 * @see Train
 */
public abstract class Entreprise implements Serializable {

    /**
     * Le nom de l'entreprise.
     * @see Entreprise#getNom()
     */
    private String nom;

    /**
     * ID de l'entreprise, il est unique.
     * @see Entreprise#getNumSIREN()
     */
    private int numSIREN;

    /**
     * La liste des trains de l'entreprise
     * @see Entreprise#getTrains()
     * @see Entreprise#getTrainbyId(int)
     * @see Train
     */
    private List<Train> trains;

    /**
     * Constructeur de Entreprise. Il initialise la liste de trains.
     * @param nom
     * Nom de l'entreprise
     *
     * @param numSIREN
     * Numéro SIREN de l'entreprise
     */
    public Entreprise(String nom, int numSIREN) {
        trains = new ArrayList<Train>();
        this.nom = nom;
        this.numSIREN = numSIREN;
    }

    /**
     * Permet l'ajout d'un train à la liste de trains possédés par l'entreprise.
     *
     * @param train
     * Train à ajouter
     *
     * @return
     *
     * @see Train
     */
    abstract public boolean addTrain(Train train);

    /**
     * Permet de déclarer le fait qu'un train est en panne (ou non). Prend en paramètre le train en question.
     *
     * @param train
     * Le train en question
     * @param isEnPanne
     * = true si le train est en panne, false sinon
     *
     * @see Train
     */
    public void declarerPanne(Train train, boolean isEnPanne) {
        for (Train t : trains)
            if (t.equals(train))
                t.setPanne(isEnPanne);
    }

    /**
     * Renvoie le nom de l'entreprise.
     * @return
     */
    public String getNom() {
        return nom;
    }


    /**
     * Renvoie la liste des trains possédés par l'entreprise.
     * @return
     */
    public List<Train> getTrains() {
        return trains;
    }

    /**
     * Renvoie le numéro SIREN de l'entreprise.
     * @return
     */
    public int getNumSIREN() {
        return numSIREN;
    }

    /**
     * Permet de retrouver un train par son ID dans la liste des trains possédés par l'entreprise.
     * @param id
     * @return
     */
    public Train getTrainbyId(int id) {
        for (Train train : trains) {
            if (train.getId() == id)
                return train;
        }
        return null;
    }

    /**
     * Permet d'ajouter un train à la liste des trains de l'entreprise
     * @param train
     */
    protected void addTrainLocal(Train train) {
        trains.add(train);
    }

    /**
     * Renvoie une string décrivant l'entreprise (avec son nom et son numéro SIREN).
     * @return
     */
    @Override
    public String toString() {
        return "Entreprise [nom=" + nom + ", numSIREN=" + numSIREN + "]";
    }

    /**
     * Demande l'attribution de sillons pour un trajet spécifié avec la méthode d'attribution spécifiée pour le train passé en paramètre.
     * @param train
     * @see Controleur#recupererTrajet(Train)
     * @see Train
     */
    public void demanderTrajet(Train train) {
        try {
            Controleur.recupererTrajet(train);
        } catch (NullPointerException e) {
            System.out.println("Un train doit être spécifié");
        }
    }
}
