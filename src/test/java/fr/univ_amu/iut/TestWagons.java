package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseAnimaux;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainMarchandises;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainPassagers;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.WagonPassager;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrain;
import org.junit.Test;

/**
 * Created by w16002657 on 07/11/17.
 */
public class TestWagons {
    private static Entreprise entrepriseTestPassagers = new EntreprisePassager("passagerTest", 1);
    private static Entreprise entrepriseTestCargo = new EntrepriseCargo("cargoTest", 1);

    @Test
    public void testAjouterWagonPassagerATrainPassager() {
        FabriqueTrain fa = new FabriqueTrainPassagers();

        Train trainPassager = fa.creer(12);

        Wagon wagonPassager = new WagonPassager();
        wagonPassager.connecter(trainPassager);
        entrepriseTestPassagers.addTrain(trainPassager);
        System.out.println(trainPassager);
    }

    @Test
    public void testAjouterWagonPassagerATrainMarchandises() {
        FabriqueTrain fa = new FabriqueTrainMarchandises();
        Train trainMarchandises = fa.creer(152);
        Wagon wagonPassager = new WagonPassager();
        wagonPassager.connecter(trainMarchandises);
        entrepriseTestCargo.addTrain(trainMarchandises);
        System.out.println(trainMarchandises);
    }
}
