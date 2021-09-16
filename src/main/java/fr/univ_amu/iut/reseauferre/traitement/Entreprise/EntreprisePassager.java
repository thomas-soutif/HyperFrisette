package fr.univ_amu.iut.reseauferre.traitement.Entreprise;

import fr.univ_amu.iut.reseauferre.traitement.Train.*;

/**
 * Type d'entreprise s'occupant du transport de particulier. Elle ne peut posséder que des trains de passager.
 *
 * @see Entreprise
 * @see TrainMarchandise
 */
public class EntreprisePassager extends Entreprise {
    /**
     * Constructeur de EntreprisePassager. Il appelle le constructeur de la classe mère Entreprise.
     * @param nom
     * @param numSIREN
     * @see Entreprise#Entreprise(String, int)
     */
    public EntreprisePassager(String nom, int numSIREN) {
        super(nom, numSIREN);
    }

    /**
     * Permet d'ajouter un train du bon type.
     * @param train
     * Train à ajouter
     *
     * @return
     */
    @Override
    public boolean addTrain(Train train) {
        if (train.correspond(this)) {
            super.addTrainLocal(train);
            train.setEntreprise(this);
            System.out.println("Le train " + train.getId() + " a été ajouté à l'entreprise " + this.getNom());
            return true;
        } else {
            System.err.println("Le type de train ne correspond pas à l'activité de l'entreprise " + this.getNom() + '.');
            return false;
        }
    }
}
