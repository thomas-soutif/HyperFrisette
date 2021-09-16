package fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Serialization.Serializeur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.util.Collection;

public class Exportation {

    /**
     * exporte une collection d'entreprises dans un fichier
     * @param ent
     */
    public static void exporterEntreprises(Collection<Entreprise> ent)  {
        Serializeur.serializerEntreprises(ent);

    }

    /**
     * exporter une collection de trains dans un fichier
     * @param tr
     */

    public static void exporterTrains(Collection<Train> tr){
        Serializeur.serializerTrains(tr);

    }

    /**
     * exporte une collection de Sillons dans un fichier
     * @param si
     */

    public static void exporterSillons(Collection<Sillon> si) {
        Serializeur.serializerSillons(si);
    }






}
