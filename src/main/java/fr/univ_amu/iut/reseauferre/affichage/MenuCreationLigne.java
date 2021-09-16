package fr.univ_amu.iut.reseauferre.affichage;

import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Lignes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Vincent on 18/11/2017.
 */

/**
 * Cette classe gère la création de nouvelles lignes sur le réseau sur demande de l'utilisateur
 */
public class MenuCreationLigne {

    /**
     * Demande à l'utilisateur ce qu'il veut faire
     */
    public static void afficherMenuPrincipal() {
        System.out.println("\nMenu Principal -> Menu de création de lignes");
        System.out.println("Voici la liste des lignes déjà existantes :\n");
        System.out.println(Lignes.getMesLignes());
        System.out.println("\nQue voulez-vous faire ?");
        System.out.println("1 - Demander la création d'une ligne");
        System.out.println("0 - Quitter");
        bouclePrincipale();
    }

    /**
     * L'utilisateur saisit les deux gares correspondant à la nouvelle ligne et le système l'ajoute si possible
     */
    private static void bouclePrincipale() {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 1)
                switch (choix) {
                    case 1:
                        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Saisissez la gare de départ correspondant à la nouvelle ligne :");
                        String gareDepart = null;
                        try {
                            gareDepart = buf.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Saisissez la gare d'arrivée correspondant à la nouvelle ligne :");
                        String gareArrivee = null;
                        try {
                            gareArrivee = buf.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Lignes.ajouterLigneSiGaresExistent(gareDepart, gareArrivee);
                        afficherMenuPrincipal();
                        break;
                    case 0:
                        //quitter
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
