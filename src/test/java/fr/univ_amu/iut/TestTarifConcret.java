package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionAuCout;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

import java.util.Map;

public class TestTarifConcret {


    public static void main(String[] args){

        Controleur.setAttribuerSillons(new AttributionAuCout());

        Entreprise ent = new EntrepriseCargo( "SNCF", 808332670);
        Entreprise ent2 = new EntreprisePassager("SouSou",785218552);
        Train train = new TrainMarchandise(100);
        Train trainVide = new TrainPassager(10);
        Train t = new TrainPassager(500);
        ent.addTrain(train);
        ent2.addTrain(t);
        ent2.addTrain(trainVide);

        System.out.println("L'entreprise SNCF demande un trajet pour son train de marchandises :");
        ent.demanderTrajet(train);

        System.out.println("");
        System.out.println("L'entreprise SouSou demande un trajet pour son train de passager qui est vide :");
        ent2.demanderTrajet(trainVide);
        System.out.println("");
        System.out.println("Voici la liste des entreprises qui doivent de l'argent à HyperFrisette : ");
        for(Map.Entry entry : ApplicationHyperFrisette.getTarifEntreprise().entrySet())
        {
            System.out.println(entry.getKey() + ", " + entry.getValue() + "€" );
        }

        System.out.println("L'entreprise Sousou veut à présent demander un trajet pour un de ses nouveaux trains :");
        ent2.demanderTrajet(t);

        System.out.println("La liste s'est bien mise à jour");

        for(Map.Entry entry : ApplicationHyperFrisette.getTarifEntreprise().entrySet())
        {
            System.out.println(entry.getKey() + ", " + entry.getValue() + "€" );
        }

    }

}
