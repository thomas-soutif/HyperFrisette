package fr.univ_amu.iut.reseauferre.affichage;

import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;

import java.util.Map;
import java.util.Scanner;

/**
 * Gère la facturation des entreprises
 */
public class MenuGestionTarif {

    /**
     * Demande à l'utilisateur si il veut afficher la facture des entreprises ou bien les faire payer
     */
    public static void afficherMenuPrincipal() {
        System.out.println("\nMenu Principal -> Gestion de la Tarification");
        System.out.println("1 - Afficher le montant d'argent que doit chaque entreprise");
        System.out.println("2 - Faire payer toutes les entreprises qui doivent de l'argent");
        System.out.println("0 - Retour");
        bouclePrincipale();
    }

    /**
     * L'utilisateur saisit sa demande
     */
    private static void bouclePrincipale() {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 2)
                switch (choix) {
                    case 1:
                        afficherEntreprise();
                        afficherMenuPrincipal();
                        break;
                    case 2:
                        appliquerTarifEntreprises();
                        afficherMenuPrincipal();
                    case 0:
                        break;
                }
            else {
                System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
                bouclePrincipale();
            }
        } else {
            System.err.println("Saisie incorrecte, veuillez re-saisir une option.");
            bouclePrincipale();
        }
    }

    /**
     * Affiche l'argent que doit chaque entreprise à HyperFrisette
     */
    private static void afficherEntreprise() {
        if (ApplicationHyperFrisette.getTarifEntreprise().isEmpty())
            System.out.println("Aucune entreprise ne vous doit de l'argent pour aujourd'hui.");
        else {
            System.out.println("\n * Les entreprises qui doivent de l'argent sont : *\n");
            for (Map.Entry<Entreprise, Double> entry : ApplicationHyperFrisette.getTarifEntreprise().entrySet()) {
                System.out.println(entry.getKey().getNom() + " ==> " + entry.getValue() + " €");
            }
        }
    }

    /**
     * Fait payer toutes les entreprises
     */
    private static void appliquerTarifEntreprises() {
        Double benefice = new Double(0);
        for (Map.Entry<Entreprise, Double> entry : ApplicationHyperFrisette.getTarifEntreprise().entrySet()) {
            benefice += entry.getValue();
        }
        ApplicationHyperFrisette.reinitialiserTarif();
        if (benefice != 0) {
            System.out.println("\nToutes les entreprises ont été facturées !");

            System.out.println("HyperFrisette a dégagé un bénéfice de " + benefice + " € aujourd'hui !\n");
        } else
            System.out.println("\nAucune entreprise ne vous doit de l'argent !\n");
    }
}
