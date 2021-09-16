package fr.univ_amu.iut.reseauferre.affichage;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionAuCout;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionGlouton;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;

import java.util.Scanner;

/**
 * Created by Vincent on 18/11/2017.
 */

/**
 * Gère le changement de méthode d'attribution des sillons
 */
public class MenuGestionAttributionSillon {

    /**
     * Informe l'utilisateur de toutes les méthodes d'attribution existantes
     */
    public static void changerMethodeAttributionSillons() {
        System.out.println("\nMenu Principal -> Gestion d'attribution des futurs sillons");
        System.out.println("Quelle méthode d'attribution de sillons souhaitez-vous appliquer pour les futurs trajets ?\n");
        System.out.println("1 - Attribution glouton");
        System.out.println("2 - Attribution au coût");
        System.out.println("0 - Quitter");
        bouclePrincipale();
    }

    /**
     * L'utilisateur sélectionne la nouvelle méthode à appliquer
     */
    private static void bouclePrincipale() {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 3)
                switch (choix) {
                    case 1:
                        Controleur.setAttribuerSillons(new AttributionGlouton());
                        System.out.println("\n* Les nouveaux trajets seront désormais attribués en glouton ! *\n");
                        break;
                    case 2:
                        Controleur.setAttribuerSillons(new AttributionAuCout());
                        System.out.println("\n* Les nouveaux trajets seront désormais attribués selon l'horaire le moins cher ! *\n");
                        break;
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


}
