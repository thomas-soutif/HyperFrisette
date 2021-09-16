package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.*;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseAnimaux;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.*;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;
import org.junit.Test;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;

public class TestFabriques {
    private static Entreprise entrepriseTestAnimaux = new EntrepriseAnimaux("animauxTest", 1);
    private static Entreprise entrepriseTestPassagers = new EntreprisePassager("passagerTest", 1);
    private static Entreprise entrepriseTestCargo = new EntrepriseCargo("cargoTest", 1);

	@Test
	public void testFabriqueTrains() {
		FabriqueTrain fa = new FabriqueTrainPassagers();
		Train train = fa.creer(450);
		entrepriseTestPassagers.addTrain(train);
		System.out.println(train);
	}

	@Test
	public void testFabriqueWagons() {
		FabriqueWagon maFabrique = new FabriqueWagonBetail();
		Wagon wagon = maFabrique.creer();
		System.out.println(wagon);
	}

	@Test
	public void testBonWagonBonTrain() {
		FabriqueTrain maFabriqueTrain = new FabriqueTrainMarchandises();
		FabriqueWagon maFabriqueWagon = new FabriqueWagonProduitsLiquides();

		Train trainMarchandises = maFabriqueTrain.creer(123);
        Wagon wagonProduitsLiquides = maFabriqueWagon.creer();

        wagonProduitsLiquides.connecter(trainMarchandises);
        entrepriseTestCargo.addTrain(trainMarchandises);
		System.out.println(trainMarchandises);
	}

	@Test
	public void testMauvaisWagonBonTrain() {
		FabriqueTrain fabriqueTrain = new FabriqueTrainPassagers();
		FabriqueWagon fabriqueWagon = new FabriqueWagonProduitsLiquides();

		Train trainPassagers = fabriqueTrain.creer( 123);
		Wagon wagonProduitsLiquides = fabriqueWagon.creer();

		wagonProduitsLiquides.connecter(trainPassagers);
		entrepriseTestPassagers.addTrain(trainPassagers);
		System.out.println(entrepriseTestPassagers);
	}

	@Test
    public void testBonTrainBonneEntreprise() {
        FabriqueTrain fabriqueTrain = new FabriqueTrainPassagers();
        Train trainMarchandises = fabriqueTrain.creer(789);
        entrepriseTestAnimaux.addTrain(trainMarchandises);
        System.out.println(entrepriseTestAnimaux);
    }

	@Test
	public void testMauvaisTrainBonneEntreprise() {
		FabriqueTrain fabriqueTrain = new FabriqueTrainMarchandises();
		Train trainMarchandises = fabriqueTrain.creer(789);
		entrepriseTestAnimaux.addTrain(trainMarchandises);
        System.out.println(entrepriseTestAnimaux);
	}
}
