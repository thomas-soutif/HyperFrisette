package fr.univ_amu.iut.reseauferre.affichage;

import fr.univ_amu.iut.reseauferre.affichage.menugestioncompagnies.MenuGestionCompagnies;
import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionGlouton;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseAnimaux;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainMarchandise;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vincent on 15/11/2017.
 */

/**
 * Représente le menu principal de l'application
 */
public class Systeme {

    /**
     * La liste d'entreprises prédéfinies à défaut de les récupérer depuis un fichier serializable
     */
    private static List<Entreprise> lesEntreprises = new ArrayList<>();

    /**
     * On remplit la liste d'entreprises avec des trains prédéfinis
     */
    static {
        Entreprise sncf = new EntreprisePassager("SNCF", 1);
        Entreprise trainimauxLand = new EntrepriseAnimaux("TrainimauxLand", 2);
        Entreprise esCargo = new EntrepriseCargo("EsCargo", 3);
        trainimauxLand.addTrain(new TrainPassager(300));
        trainimauxLand.addTrain(new TrainPassager(150));
        esCargo.addTrain(new TrainMarchandise(140));
        sncf.addTrain(new TrainPassager(100));
        sncf.addTrain(new TrainPassager(200));
        lesEntreprises.add(sncf);
        lesEntreprises.add(trainimauxLand);
        lesEntreprises.add(esCargo);
    }

    /**
     * Menu principal de l'application qui s'affiche lorsqu'on lance l'application
     */
    public static void affichageBienvenue() {
        System.out.println("\n*****Bienvenue sur HyperFrisette*****");
        System.out.println("***********Menu Principal*********** [Heure du systeme :" + ApplicationHyperFrisette.recupererHeure().toString() + "]\n");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1 - Gestion des compagnies");
        System.out.println("2 - Simuler le déplacement des trains");
        System.out.println("3 - Visualiser le système");
        System.out.println("4 - Requête de création d'une nouvelle ligne");
        System.out.println("5 - Changer la méthode d'attribution des sillons (Actuellement : " + attributionToString() + ")");
        System.out.println("6 - Gestion de la tarification");
        System.out.println("7 - Reinitialiser les sillons à zéro");
        System.out.println("0 - Quitter");
        bouclePrincipale();
    }

    /**
     * L'utilisateur saisit sa demande et est redirigé vers le menu correspondant
     */
    private static void bouclePrincipale() {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 7)
                switch (choix) {
                    case 1:
                        MenuGestionCompagnies.afficherMenuPrincipal(lesEntreprises);
                        affichageBienvenue();
                        break;
                    case 2:
                        Controleur.actionnerSyst(10);
                        affichageBienvenue();
                        break;
                    case 3:
                        MenuGestionVues.afficherMenuPrincipal();
                        affichageBienvenue();
                        break;
                    case 4:
                        MenuCreationLigne.afficherMenuPrincipal();
                        affichageBienvenue();
                        break;
                    case 5:
                        MenuGestionAttributionSillon.changerMethodeAttributionSillons();
                        affichageBienvenue();
                        break;
                    case 6:
                        MenuGestionTarif.afficherMenuPrincipal();
                        affichageBienvenue();
                        break;
                    case 7:
                        System.out.println("Desactivé , disponible dans la prochaine mise à jour");
                        affichageBienvenue();
                    case 0:
                        //quitter();
                        break;
                }
            else {
                System.err.println("Saisie incorrecte");
                System.out.println("Que souhaitez vous faire ?");
                bouclePrincipale();

            }
        } else {
            System.err.println("Saisie incorrecte");
            System.out.println("Que souhaitez vous faire ?");
            bouclePrincipale();
        }
    }

    /**
     * Renvoie le nom de la méthode d'attribution des sillons actuelle
     * @return
     */
    private static String attributionToString() {

        if (Controleur.getAttributionSillon() instanceof AttributionGlouton)
            return "Glouton";
        else
            return "Coût";
    }
}
