package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionGlouton;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrain;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainMarchandises;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueWagon;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.TarifOrdinaire;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.*;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;
import org.junit.Test;

import java.util.*;

public class TestTarif {

    @Test
    public void TestAttrbutionTarifParRapportAuTrain(){

        Entreprise ent = new EntrepriseCargo( "SNCF", 808332670);

        FabriqueTrain fabriqueTrain = new FabriqueTrainMarchandises();
        Train train = fabriqueTrain.creer( 100);

        ent.addTrain(train);

        PolitiqueTarifaire politiqueSNCF = new TarifOrdinaire();

        train.getCout(politiqueSNCF);
        assert(politiqueSNCF.getCoutTotal() == 50);

    }

    @Test
    public void testerTarifsurPlusieursEntrepriseQuiAffecteUnTrainChacune()
    {

        Entreprise ent = new EntrepriseCargo( "SNCF", 808332670);
        Entreprise ent2 = new EntreprisePassager("SouSou",785218552);

        Train train = new TrainMarchandise(100);
        Train trainVide = new TrainPassager(10);
        ent.addTrain(train);
        ent2.addTrain(trainVide);
        Ligne ligne = new Ligne(Gare.Paris,Gare.Lille);
        Ligne ligne2 = new Ligne(Gare.Marseille,Gare.Paris);
        List<Ligne> liste = new ArrayList<>();
        liste.add(ligne);

        List<Ligne> liste2 = new ArrayList<>();
        liste2.add(ligne2);

        Controleur.setAttribuerSillons(new AttributionGlouton());
        Controleur.attribuerSillons(liste,train);
        Controleur.attribuerSillons(liste2,trainVide);
        for(Map.Entry entry : ApplicationHyperFrisette.getTarifEntreprise().entrySet())
        {
            System.out.println(entry.getKey() + ", " + entry.getValue() + "€" );
        }
        //On affiche toute les Entreprises qui ont quelque chose à payer.

    }

    @Test
    public void TestPrioriteCout()
    {

        PriorityQueue<Sillon> queue = new PriorityQueue<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon s1, Sillon s2) {
                if(s1.getPrixHoraire() < s2.getPrixHoraire())
                    return -1;
                else
                    return 1;
            }
        });

        for(Sillon sillon  : Controleur.getSillons())
        {
            if(sillon.getTrain() == null)
                queue.add(sillon);
        }



    }

}
