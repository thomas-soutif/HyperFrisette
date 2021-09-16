package fr.univ_amu.iut.reseauferre.affichage.menugestioncompagnies;

import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueWagon;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueWagonBetail;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueWagonPassagers;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueWagonProduitsLiquides;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;

import java.util.Scanner;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Cette classe gère l'ajout de wagons à un train
 */
public class MenuGestionWagons {

    /**
     * Donne à l'utilisateur tous les types de wagons préexistants
     * @param train
     */
    public static void selectionnerTypeWagon(Train train) {
        System.out.println("\nMenu Principal -> Gestion de compagnies -> Gestion de trains -> Gestion de wagons");
        System.out.println("Quel type de wagon souhaitez-vous connecter à ce train ?");
        System.out.println("1 - Passager");
        System.out.println("2 - Betail");
        System.out.println("3 - Produits Liquides");
        System.out.println("0 - Quitter");
        ajouterWagonATrain(train);
    }

    /**
     * L'utilisateur saisit le type du wagon à ajouter et le système l'ajoute si il est compatible avec le type de train
     * @param train
     */
    private static void ajouterWagonATrain(Train train) {
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 3) {
                switch (choix) {
                    case 1:
                        FabriqueWagon fabriqueWagonPassager = new FabriqueWagonPassagers();
                        Wagon nouveauWagonPassager = fabriqueWagonPassager.creer();
                        if (nouveauWagonPassager.connecter(train)) selectionnerTypeWagon(train);
                        else ajouterWagonATrain(train);
                        break;
                    case 2:
                        FabriqueWagon fabriqueWagonBetail = new FabriqueWagonBetail();
                        Wagon nouveauWagonBetail = fabriqueWagonBetail.creer();
                        if (nouveauWagonBetail.connecter(train)) selectionnerTypeWagon(train);
                        else ajouterWagonATrain(train);
                        break;
                    case 3:
                        FabriqueWagon fabriqueWagonProduitsLiquides = new FabriqueWagonProduitsLiquides();
                        Wagon nouveauWagonProduitsLiquides = fabriqueWagonProduitsLiquides.creer();
                        if (nouveauWagonProduitsLiquides.connecter(train)) selectionnerTypeWagon(train);
                        else ajouterWagonATrain(train);
                        break;
                    case 0:
                        break;
                }
            } else {
                System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
                ajouterWagonATrain(train);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
            ajouterWagonATrain(train);
        }
    }
}
