package fr.univ_amu.iut.reseauferre.affichage.menugestioncompagnies;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrain;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainMarchandises;
import fr.univ_amu.iut.reseauferre.traitement.Fabriques.FabriqueTrainPassagers;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.util.Scanner;

/**
 * Created by Vincent on 19/11/2017.
 */

/**
 * Cette classe gère la création de trains pour une entreprise donnée
 */
public class MenuGestionCreationTrains {

    /**
     * Le système demande quel type de train l'utilisateur souhaite ajouter à l'entreprise choisie
     * @param entreprise
     */
    public static void selectionnerTypeTrain(Entreprise entreprise) {
        System.out.println("\nMenu Principal -> Gestion de compagnies -> Gestion de création de trains");
        System.out.println("Quel type de train souhaitez-vous ajouter à l'entreprise " + entreprise.getNom() + " ?");
        System.out.println("1 - Passagers");
        System.out.println("2 - Marchandises");
        System.out.println("0 - Quitter");
        ajouterTrainAEntreprise(entreprise);
    }

    /**
     * Permet d'ajouter un train à une entreprise après reçu sa taille
     * @param entreprise
     */
    private static void ajouterTrainAEntreprise(Entreprise entreprise) {
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix == 0) return;
            if (choix >= 1 && choix <= 2) {
                double tailleTrain = demanderTailleTrain(entreprise);
                switch (choix) {
                    case 1:
                        FabriqueTrain maFabriqueTrainPassagers = new FabriqueTrainPassagers();
                        Train nouveauTrainPassagers = maFabriqueTrainPassagers.creer(tailleTrain);
                        if(entreprise.addTrain(nouveauTrainPassagers)) selectionnerTypeTrain(entreprise);
                        else selectionnerTypeTrain(entreprise);
                        break;
                    case 2:
                        FabriqueTrain maFabriqueTrainMarchandises = new FabriqueTrainMarchandises();
                        Train nouveauTrainMarchandises = maFabriqueTrainMarchandises.creer(tailleTrain);
                        if(entreprise.addTrain(nouveauTrainMarchandises)) selectionnerTypeTrain(entreprise);
                        else selectionnerTypeTrain(entreprise);
                        break;
                }
            }
            else {
                System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
                ajouterTrainAEntreprise(entreprise);
            }
        }
        else {
            System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
            ajouterTrainAEntreprise(entreprise);
        }
    }

    /**
     * L'utilisateur insère la taille du nouveau train
     * @param entreprise
     * @return
     */
    private static double demanderTailleTrain(Entreprise entreprise) {
        System.out.println("\n Veuillez saisir la taille du train à ajouter (entre 50 et 1500). Appuyez sur 0 pour quitter");
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix != 0) {
                if (choix >= 50) {
                    if (choix <= 1500)
                        return choix;
                    else {
                        System.err.println("La taille du train est trop élevée.");
                        demanderTailleTrain(entreprise);
                    }
                } else {
                    System.err.println("La taille du train est trop basse. Veuillez réessayer.");
                    demanderTailleTrain(entreprise);
                }
            }
            else
                selectionnerTypeTrain(entreprise);
        }
        else {
            System.err.println("Saisie incorrecte, veuillez réessayer.");
            demanderTailleTrain(entreprise);
        }
        return 0;
    }
}
