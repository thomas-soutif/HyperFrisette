package fr.univ_amu.iut.reseauferre.affichage;

import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.time.LocalTime;
import java.util.*;

/**
 * Created by Vincent on 18/11/2017.
 */

/**
 * Gère l'affichage des différentes vues
 */
public class MenuGestionVues {

    /**
     * Le menu qui affiche les différentes vues existantes
     */
    public static void afficherMenuPrincipal() {
        System.out.println("\nMenu Principal -> Menu de gestion des affichages");
        System.out.println("Choisissez un panneau à afficher : \n");
        System.out.println("1 - Vue sur l'ensemble du réseau");
        System.out.println("2 - Vue sur les temps de départ estimés des trains");
        System.out.println("3 - Vue sur les temps d'arrivée estimés des trains");
        System.out.println("4 - Vue sur les perturbations au sein du réseau");
        System.out.println("0 - Quitter");
        bouclePrincipale();
    }

    /**
     * L'utilisateur saisit la vue qu'il souhaite afficher
     */
    private static void bouclePrincipale() {
        Scanner sc = new Scanner(System.in);
        int choix;
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix >= 0 && choix <= 4)
                switch (choix) {
                    case 1:
                        vueEnsemble();
                        afficherMenuPrincipal();
                        break;
                    case 2:
                        vueDepartsEstimes();
                        afficherMenuPrincipal();
                        break;
                    case 3:
                        vueArriveesEstimes();
                        afficherMenuPrincipal();
                        break;
                    case 4:
                        vuePerturbationsReseau();
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

    /**
     * Affiche les trains en déplacement
     */
    private static void vueEnsemble() {
        System.out.println("\nSituation du réseau à : ( " + ApplicationHyperFrisette.recupererHeure().toString() + " )\n");

        Set<Sillon> sillonAvecTrain = new HashSet<>();
        for (Sillon s : Controleur.getSillons()) {
            if (s.getTrain() != null)
                sillonAvecTrain.add(s);
        }


        LocalTime heureActuel = ApplicationHyperFrisette.recupererHeure();
        boolean isTrain = false;
        for (Sillon sillon : sillonAvecTrain) {
            if (sillon.gethDepart().isBefore(heureActuel) && sillon.gethArrive().isAfter(heureActuel) || sillon.gethDepart().equals(heureActuel) || sillon.gethArrive().equals(heureActuel)) {
                String id = String.valueOf(sillon.getTrain().getId());
                String entreprise = sillon.getTrain().getEntreprise().getNom();
                String heureDep = sillon.gethDepart().toString();
                String heureArr = sillon.gethArrive().toString();
                String etat = "";
                if (sillon.getTrain().enPanne()) etat = "Panne déclarée";
                else etat = "En déplacement";
                System.out.println("[Train " + id + " | " + entreprise + " | Départ " + sillon.getLigne().getGareDepart() + " " + heureDep + " | Arrivée " + sillon.getLigne().getGareArrivee() + " " + heureArr + " | " + etat + "]");
                isTrain = true;
            }
        }
        if (!isTrain)
            System.out.println("Aucun train ne circule en ce moment sur les réseaux de HyperFrisette.");
    }

    /**
     * Affiche les départs estimés des trains qui ont un trajet programmé
     */
    private static void vueDepartsEstimes() {
        System.out.println("\nDéparts estimés dans le réseau à : ( " + ApplicationHyperFrisette.recupererHeure().toString() + " )\n");
        System.out.println("");

        Set<Sillon> touslesSillonsParOrdrePriorite = new TreeSet<Sillon>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon sillon, Sillon t1) {
                if (sillon.getPriorite() == t1.getPriorite())
                    return -1;
                else return (sillon.getPriorite() - t1.getPriorite());
            }
        });
        for (Sillon s : Controleur.getSillons()) {
            if (s.getTrain() != null)
                touslesSillonsParOrdrePriorite.add(s);
        }

        Set<Train> tousLesTrains = new LinkedHashSet<>(); //Un set pour eviter les doublons !
        for (Sillon sillon : Controleur.getSillons()) { //On récupère tous les trains qui ont un trajet prévu
            if (sillon.getTrain() != null) {
                tousLesTrains.add(sillon.getTrain());
            }
        }
        ;
        for (Train train : tousLesTrains) {
            Ligne ligneDAvant = null;
            for (Sillon sillon : touslesSillonsParOrdrePriorite) {
                if (sillon.getTrain().equals(train)) {
                    if (sillon.getPriorite() == 0)
                        System.out.println("Le départ du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareDepart() + " à destination de " + sillon.getLigne().getGareArrivee() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévu pour " + sillon.gethDepart());
                    else {
                        if ((ligneDAvant.getGareDepart().equals(sillon.getLigne().getGareArrivee()) || ligneDAvant.getGareArrivee().equals(sillon.getLigne().getGareArrivee())))
                            System.out.println("Le départ du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareArrivee() + " à destination de " + sillon.getLigne().getGareDepart() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévu pour " + sillon.gethDepart());
                        else
                            System.out.println("Le départ du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareDepart() + " à destination de " + sillon.getLigne().getGareArrivee() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévu pour " + sillon.gethDepart());

                    }
                    ligneDAvant = sillon.getLigne();
                }
            }
        }


    }

    /**
     * Affiche les arrivées estimés des trains qui ont un trajet programmé
     */
    private static void vueArriveesEstimes() {

        System.out.println("\nArrivées estimées dans le réseau à : ( " + ApplicationHyperFrisette.recupererHeure().toString() + " )\n");
        System.out.println("");

        Set<Sillon> touslesSillonsParOrdrePriorite = new TreeSet<Sillon>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon sillon, Sillon t1) {
                if (sillon.getPriorite() == t1.getPriorite())
                    return -1;
                else return (sillon.getPriorite() - t1.getPriorite());
            }
        });
        for (Sillon s : Controleur.getSillons()) {
            if (s.getTrain() != null)
                touslesSillonsParOrdrePriorite.add(s);
        }

        Set<Train> tousLesTrains = new LinkedHashSet<>(); //Un set pour eviter les doublons !
        for (Sillon sillon : Controleur.getSillons()) { //On récupère tous les trains qui ont un trajet prévu
            if (sillon.getTrain() != null) {
                tousLesTrains.add(sillon.getTrain());
            }
        }
        ;
        for (Train train : tousLesTrains) {
            Ligne ligneDAvant = null;
            for (Sillon sillon : touslesSillonsParOrdrePriorite) {
                if (sillon.getTrain().equals(train)) {
                    if (sillon.getPriorite() == 0)
                        System.out.println("L'arrivée du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareDepart() + " à destination de " + sillon.getLigne().getGareArrivee() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévue pour " + sillon.gethArrive());
                    else {
                        if ((ligneDAvant.getGareDepart().equals(sillon.getLigne().getGareArrivee()) || ligneDAvant.getGareArrivee().equals(sillon.getLigne().getGareArrivee())))
                            System.out.println("L'arrivée du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareArrivee() + " à destination de " + sillon.getLigne().getGareDepart() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévue pour " + sillon.gethArrive());
                        else
                            System.out.println("L'arrivée du train " + train.getId() + " en provenance de " + sillon.getLigne().getGareDepart() + " à destination de " + sillon.getLigne().getGareArrivee() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " est prévue pour " + sillon.gethArrive());

                    }
                    ligneDAvant = sillon.getLigne();
                }
            }
        }
    }

    /**
     * Affiche les sillons qui contiennent des trains en panne
     */
    private static void vuePerturbationsReseau() {
        System.out.println("\nPerturbations dans le réseau à l'heure actuelle :");
        for (Sillon sillon : Controleur.getSillons()) {
            if (sillon.getTrain() != null && sillon.getTrain().enPanne())
                System.out.println(sillon);
        }
    }
}


