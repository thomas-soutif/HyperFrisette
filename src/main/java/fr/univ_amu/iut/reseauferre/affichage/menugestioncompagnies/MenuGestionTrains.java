package fr.univ_amu.iut.reseauferre.affichage.menugestioncompagnies;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Exportation;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.Streaming;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.Wifi;

import java.util.List;
import java.util.Scanner;

/**
 * Created by w16002657 on 17/11/17.
 */

/**
 * Cette classe gère les actions générales sur les trains d'une entreprise choisie par l'utilisateur
 */
public class MenuGestionTrains {

    /**
     * L'entreprise à laquelle appartiennent les trains sur lesquelles on travaille
     */
    private static Entreprise entrepriseGeree;


    /**
     * Le système demande sur quel train de l'entreprise gére travailler
     * @param entreprise
     */
    public static void afficherMenuPrincipal(Entreprise entreprise) {
        if (entrepriseGeree == null || !entreprise.equals(entrepriseGeree))
            entrepriseGeree = entreprise;
        System.out.println("\nMenu Principal -> Gestion de compagnies -> Gestion de trains");
        System.out.println("Sur quel train voulez vous travailler ?");
        int numeroAffichage = 1;
        for (Train train : entrepriseGeree.getTrains()) {
            System.out.println(numeroAffichage + " - " + entrepriseGeree.getTrains().get(numeroAffichage - 1));
            ++numeroAffichage;
        }
        System.out.println("0 - quitter");
        demanderQuelTrain(entreprise.getTrains(), numeroAffichage);
    }

    /**
     * le système récupère les trains de l'entreprise et l'utilisateur saisit le train. Il est ensuite redirigé vers la sélection de l'action à effectuer sur ce train
     * @param trainsDeLEntreprise
     * @param numeroAffichage
     */
    private static void demanderQuelTrain(List<Train> trainsDeLEntreprise, int numeroAffichage) {
        Train train;
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 1 && choix < numeroAffichage) {
                train = trainsDeLEntreprise.get(choix - 1);
                selectionnerOptionTrain(train);
            } else if (choix == 0)
                return;
            else {
                System.err.println("Saisie incorrecte, veuillez réessayer.");
                demanderQuelTrain(trainsDeLEntreprise, numeroAffichage);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez réessayer.");
            demanderQuelTrain(trainsDeLEntreprise, numeroAffichage);
        }
    }

    /**
     * Demande à l'utilisateur quoi faire sur le train choisi
     * @param train
     */
    private static void selectionnerOptionTrain(Train train) {
        if (train != null) {
            System.out.println("\nVous avez sélectionné le train " + train.getId() + " .");
            System.out.println("1 - Demande de trajet");
            System.out.println("2 - Demande de wagons");
            System.out.println("3 - Ajouter des options");
            System.out.println("4 - Demande de panne");
            System.out.println("5 - Annulation de panne");
            System.out.println("0 - Quitter");
            bouclePrincipale(train);
        } else
            System.out.println("Retour à la sélection des trains...");
    }


    /**
     * L'utilisateur dit ce qu'il veut faire sur ce train
     * @param train
     */
    private static void bouclePrincipale(Train train) {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 5)
                switch (choix) {
                    case 1:
                        entrepriseGeree.demanderTrajet(train);
                        afficherMenuPrincipal(entrepriseGeree);
                        break;
                    case 2:
                        MenuGestionWagons.selectionnerTypeWagon(train);
                        afficherMenuPrincipal(entrepriseGeree);
                        break;
                    case 3:
                        System.out.println("Quelle(s) option(s) voulez-vous ajouter à ce train ?");
                        System.out.println("1 - Streaming");
                        System.out.println("2 - Wifi");
                        System.out.println("0 - Quitter");
                        ajouterOptions(train);
                        break;
                    case 4:
                        if (!train.enPanne()) {
                            entrepriseGeree.declarerPanne(train, true);
                            Exportation.exporterSillons(Controleur.getSillons());
                            System.out.println("\n* Le train " + train.getId() + " de l'entreprise " + train.getEntreprise() + " est en panne ! *\n");
                        } else
                            System.err.println("\nLe train " + train.getId() + " de l'entreprise " + train.getEntreprise() + " est déjà en panne !\n");
                        afficherMenuPrincipal(entrepriseGeree);
                        break;
                    case 5:
                        if (train.enPanne()) {
                            entrepriseGeree.declarerPanne(train, false);
                            System.out.println("\n* Le train " + train.getId() + " de l'entreprise " + train.getEntreprise() + " n'est plus en panne !* \n");
                        } else
                            System.err.println("\nLe train " + train.getId() + " de l'entreprise " + train.getEntreprise() + " n'est pas en panne !\n");
                        afficherMenuPrincipal(entrepriseGeree);
                        break;
                    case 0:
                        afficherMenuPrincipal(entrepriseGeree);
                        break;
                }
            else {
                System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
                bouclePrincipale(train);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
            bouclePrincipale(train);
        }
    }

    /**
     * Si l'utilisateur a choisi l'option 3, il est redirigé ici et choisit l'option à ajouter sur le train choisi
     * @param train
     */
    private static void ajouterOptions(Train train) {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 2)
                switch (choix) {
                    case 1:
                        if (!estDansTrajet(train)) {
                            Train trainPersonnalise = train;
                            new Streaming(trainPersonnalise).personnaliser();
                            entrepriseGeree.addTrain(new Streaming(trainPersonnalise));
                            train.getEntreprise().getTrains().remove(train);
                        }
                        System.out.println("\n* Le train " + train.getId() + " est désormais équipé de l'option streaming ! *");
                        selectionnerOptionTrain(train);
                        break;
                    case 2:
                        if (!estDansTrajet(train)) {
                            Train trainPersonnalise = train;
                            entrepriseGeree.addTrain(new Wifi(trainPersonnalise));
                            train.getEntreprise().getTrains().remove(train);
                        }
                        System.out.println("\n* Le train " + train.getId() + " est désormais équipé de l'option Wi-Fi ! *");
                        selectionnerOptionTrain(train);
                        break;
                    case 0:
                        selectionnerOptionTrain(train);
                        break;
                }
            else {
                System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
                bouclePrincipale(train);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
            bouclePrincipale(train);
        }
    }

    private static boolean estDansTrajet(Train train) {
        for (Sillon sillon : Controleur.getSillons()) {
            if (sillon.getTrain() != null && sillon.getTrain().equals(train))
                return true;
        }
        return false;
    }
}
