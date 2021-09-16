package fr.univ_amu.iut;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseAnimaux;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Exportation;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Importation;
import fr.univ_amu.iut.reseauferre.traitement.Serialization.Lecteur;
import fr.univ_amu.iut.reseauferre.traitement.Serialization.Serializeur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Gare;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;
import fr.univ_amu.iut.reseauferre.traitement.Train.Wifi;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonPassager;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class TestSerializeur {
   /* @Test
    public void testGlobal() {
        Entreprise e1 = new EntreprisePassager("SNCF", 1 );
        Entreprise e2 = new EntrepriseAnimaux("THALYS", 2);
        Entreprise e3 = new EntrepriseCargo("EUROSTAR", 3);
        Train t1 = new TrainPassager(5);
        e1.addTrain(t1);
        t1.ajouterWagon(new WagonPassager());
        t1.ajouterWagon(new WagonPassager());
        t1.ajouterWagon(new WagonPassager());
        Train t8 = new Wifi(new TrainPassager(7));
        e1.addTrain(t8);

        Train e1t2 = new TrainPassager(45);
        e1t2.ajouterWagon(new WagonPassager());


        Train t2 = new TrainPassager(89);
        e1.addTrain(t2);
        t2.ajouterWagon(new WagonPassager());

        Train t3 = new TrainMarchandise(67);
        e2.addTrain(t3);

        Train t5 = new TrainMarchandise(4);

        Collection<Entreprise> collectionEntreprise = new ArrayList<Entreprise>();
        collectionEntreprise.add(e1);
        collectionEntreprise.add(e2);
        collectionEntreprise.add(e3);


        Serializeur.serializerEntreprises(collectionEntreprise);

        Collection<Entreprise> nouvelleCollectionEntreprise = new ArrayList<>();
        nouvelleCollectionEntreprise = Lecteur.recupererEntreprises("Entreprises.ser");



        String strTest = collectionEntreprise.toString();
        String strTest2 = nouvelleCollectionEntreprise.toString();

        System.out.println("Collection qui a été sauvergardée : " + strTest);
        System.out.println("Collection qui a été récupérée : " + strTest2);
        assertTrue(strTest == strTest2);
    }

    @Test
    public void TestRecuperationDansLeFichier()
    {
        for(Entreprise e : Importation.importerEntreprises())
            System.out.println(e);

        for(Train t : Importation.importerTrains())
            System.out.println(t);

        for(Sillon s : Importation.importerSillons())
            System.out.println(s);
    }

    @Test
    public void TestExport()
    {
        Entreprise e = new EntrepriseCargo("dedede",45);
        Train t = new TrainMarchandise(25);
        Collection<Entreprise> liste = new ArrayList<>();
        liste.add(e);
        Exportation.exporterEntreprises(liste);

    }
    @Test
    public void TestSerializerExportationImportation()
    {
        // Sur les entreprises
        Entreprise ent = new EntrepriseCargo("dede",444545);
        Entreprise ent2 = new EntrepriseAnimaux("CMOI",421);
        Collection<Entreprise> liste = new ArrayList<>();
        liste.add(ent);
        liste.add(ent2);
        Exportation.exporterEntreprises(liste);

        for(Entreprise e : Importation.importerEntreprises())
            System.out.println(e);


        //Sur les trains
        Train t = new TrainMarchandise(20);
        Collection<Train> listeTrain = new ArrayList<>();
        listeTrain.add(t);
        Exportation.exporterTrains(listeTrain);

        Collection<Train> trains = Importation.importerTrains();
        for(Train f : trains)
            System.out.println(f);

        //Sur les sillons

        Exportation.exporterSillons(Controleur.getSillons());

        for(Sillon s : Importation.importerSillons())
            System.out.println(s);

    }

    @Test
    public void exporterToutlesSillons()
    {
        Exportation.exporterSillons(Controleur.getSillons());

    }

    @Test
    public void ImportExportSillon(){

        Train t = new TrainPassager(500);
        Entreprise e = new EntreprisePassager("sncf",452465);
        e.addTrain(t);
        Ligne l = new Ligne(Gare.Paris,Gare.Lille);
        LocalTime tempTmp = LocalTime.of(0, 0, 0);
        Sillon s = new Sillon(tempTmp.plusHours(0), tempTmp.plusHours(1), t, l, 0);

        Collection<Sillon> sillons = new HashSet<>();
        sillons.add(s);
        Exportation.exporterSillons(sillons);

        Collection<Sillon> liste = Importation.importerSillons();
        for(Sillon se : liste)
            System.out.println(se);

    }*/
}
