package fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Serialization.Lecteur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.util.Collection;

public class Importation {
    /**
     * importe une collection d'entreprises du fichier correspondant
     * @return
     */
    public static Collection<Entreprise> importerEntreprises() {
        return Lecteur.recupererEntreprises("Entreprises.ser");

    }

    /**
     * importe une collection de trains du fichier correspondant
     * @return
     */
    public static Collection<Train> importerTrains(){
        return Lecteur.recupererTrains("Trains.ser");

    }

    /**
     * importe une collection de sillons du fichier correspondant
     * @return
     */
    public static Collection<Sillon> importerSillons() {
        return Lecteur.recupererSillons("Sillons.ser");
    }
}
