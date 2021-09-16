package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrain;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainMarchandises;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainPassagers;
import fr.univ_amu.iut.reseauferre.traitement.Train.Streaming;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.Wifi;
import org.junit.Test;

public class TestTrain {

	@Test
	public void test() {
        FabriqueTrain fabriqueTrain = new FabriqueTrainMarchandises();
		Train train = fabriqueTrain.creer(1115);
		train = new Wifi(train);
		train = new Streaming(train);
		train.deplacer(10);
	}

	@Test
	public void entrepriseTrain(){

		Entreprise monEntrepriseCargo = new EntrepriseCargo("SNCF",4587);
		Entreprise monEntreprisePassagers = new EntreprisePassager("VIVA",8741);

		FabriqueTrain maFabriqueMarchandises = new FabriqueTrainMarchandises();
		FabriqueTrain maFabriquePassagers = new FabriqueTrainPassagers();
		Train train = maFabriqueMarchandises.creer( 100);
		Train autreTrain = maFabriquePassagers.creer(80);
		monEntrepriseCargo.addTrain(train);
		monEntreprisePassagers.addTrain(autreTrain);
		System.out.println(monEntrepriseCargo.toString());
		System.out.println(monEntreprisePassagers.toString());

	}
}
