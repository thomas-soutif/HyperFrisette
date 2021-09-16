package fr.univ_amu.iut.reseauferre.affichage.menugestioncompagnies;

import fr.univ_amu.iut.reseauferre.affichage.MenuCreationLigne;
import fr.univ_amu.iut.reseauferre.affichage.Systeme;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.util.List;
import java.util.Scanner;

/**
 * Created by w16002657 on 17/11/17.
 */

/**
 * Cette classe gère les actions générales sur les compagnies
 */
public class MenuGestionCompagnies {

    /**
     * Affiche le menu principal de gestion des entreprises : le système demande à l'utilisateur sur quelle entreprises il veut travailler
     * @param lesEntreprises
     */
    public static void afficherMenuPrincipal(List<Entreprise> lesEntreprises) {
        System.out.println("\nMenu Principal -> Gestion de compagnies");
        System.out.println("Sur quelle entreprise souhaitez-vous travailler ?");
        int numeroAffichage = 1;
        for (Entreprise entreprise : lesEntreprises) {
            System.out.println(numeroAffichage + " - " + lesEntreprises.get(numeroAffichage - 1));
            ++numeroAffichage;
        }
        System.out.println("0 - quitter");
        demanderQuelleEntreprise(lesEntreprises, numeroAffichage);
    }

    /**
     * Le système demande quelle action effectuer sur cette entreprise
     * @param lesEntreprises
     * @param numeroAffichage
     */
    private static void demanderQuelleEntreprise(List<Entreprise> lesEntreprises, int numeroAffichage) {
        Entreprise entreprise;
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 1 && choix < numeroAffichage) {
                entreprise = lesEntreprises.get(choix - 1);
                System.out.println("\nVous avez sélectionné l'entreprise " + entreprise.getNom());
                System.out.println("\nQuelle action voulez-vous effectuer sur cette entreprise ?");
                System.out.println("1 - Gérer ses trains");
                System.out.println("2 - Ajouter un train");
                System.out.println("0 - Quitter");
                selectionnerActionSurEntreprise(entreprise);
                afficherMenuPrincipal(lesEntreprises);
            } else if (choix == 0)
                return;
            else {
                System.err.println("Saisie incorrecte, veuillez réessayer.");
                demanderQuelleEntreprise(lesEntreprises, numeroAffichage);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez réessayer.");
            demanderQuelleEntreprise(lesEntreprises, numeroAffichage);
        }
    }

    /**
     * Le système redirige l'utilisateur vers le menu qu'il a choisi
     * @param entreprise
     */
    private static void selectionnerActionSurEntreprise(Entreprise entreprise) {
        int choix;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 2) {
                switch (choix) {
                    case 1:
                        MenuGestionTrains.afficherMenuPrincipal(entreprise);
                        break;
                    case 2:
                        MenuGestionCreationTrains.selectionnerTypeTrain(entreprise);
                        break;
                    case 0:
                        //quitter
                        break;
                }
            } else {
                System.err.println("Saisie incorrecte, veuillez réessayer.");
                selectionnerActionSurEntreprise(entreprise);
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez réessayer.");
            selectionnerActionSurEntreprise(entreprise);
        }
    }
}
