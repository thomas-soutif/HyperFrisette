package fr.univ_amu.iut.reseauferre.traitement.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Exportation;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Importation;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Lignes;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.time.LocalTime;
import java.util.*;

/**
 * Cette classe a pour responsabilité de faire fonctionner les trains et d'attribuer les sillons au train selon la méthode demandée.
 */
public class Controleur {

    /**
     * Attribut représentant la méthode d'attribution des futurs sillons
     */
    private static AttributionSillon attributionSillon;

    /**
     * Collection de tous les sillons existants même ceux qui sont vides. On les range par ordre croissant d'heure pour chaque ligne
     *
     * @see Sillon
     */
    private static Collection<Sillon> sillons = new TreeSet<>(new Comparator<Sillon>() {
        @Override
        public int compare(Sillon o1, Sillon o2) {
            int heure = o1.gethDepart().compareTo(o2.gethDepart());
            int libelle = o1.getLigne().getLibelle().compareTo(o2.getLigne().getLibelle());
            if (libelle == 0)
                return heure;
            return libelle;
        }
    });


    /**
     * Initialisation de tous les sillons et importation d'un fichier si il existe
     *
     * @see Ligne
     * @see Sillon
     */
    static {
        for (Ligne ligne : Lignes.getMesLignes()) {
            LocalTime tempTmp = LocalTime.of(0, 0, 0);
            for (int i = 0; i <= 23; i++) {
                sillons.add(new Sillon(tempTmp.plusHours(i), tempTmp.plusHours(i + 1), null, ligne, 0));
            }
        }
        sillons.addAll(Importation.importerSillons());
    }

    /**
     * Renvoie la collection de sillons
     * @return
     *
     * @see Sillon
     */
    public static Collection<Sillon> getSillons() {
        return sillons;
    }

    /**
     * Incrémente le temps de 10 minutes. Actualise la position des trains dans le réseau et gère les pannes
     * @param min
     */

    public static void actionnerSyst(long min) {
        for (Sillon s : sillons) {
            LocalTime heureActuel = ApplicationHyperFrisette.recupererHeure();
            if (s.gethDepart().isBefore(heureActuel) && s.gethArrive().isAfter(heureActuel) || s.gethDepart().equals(heureActuel) || s.gethArrive().equals(heureActuel)) {
                if (s.getTrain() != null && !s.getTrain().enPanne() && s.getTrain().getTempsRestant().isAfter(LocalTime.of(0, 0))) {
                    s.getTrain().deplacer(min);
                    if (s.getTrain().getTempsRestant().getMinute() == 0) {
                        System.out.println("Le train " + s.getTrain().getId() + " appartenant à l'entreprise " + s.getTrain().getEntreprise() + " rentre à la gare " + s.getLigne().getGareArrivee());
                        if (s.getPriorite() == s.getTrain().getPrioriteMax())
                            retirerTrainReseau(s.getTrain());
                    } else {
                        System.out.println("Il lui reste " + s.getTrain().getTempsRestant().getMinute() + " minutes");
                    }
                }
            }
        }
        ApplicationHyperFrisette.incrementerHeure(min);
    }


    /**
     * Permet de changer la méthode d'attribution des sillons
     * @param attributionSillon
     */

    public static void setAttribuerSillons(AttributionSillon attributionSillon) {
        Controleur.attributionSillon = attributionSillon;
    }

    /**
     * Renvoie la méthode d'attribution des sillons courante
     * @return
     */
    public static AttributionSillon getAttributionSillon() {
        return attributionSillon;
    }

    /**
     * Appelle l'algorithme d'attribution des sillons
     * @param lignesDuTrajet
     * @param train
     */
    public static void attribuerSillons(List<Ligne> lignesDuTrajet, Train train) {
        //déterminer le trajet en fonction de l'algorithme
        attributionSillon.attribuer(lignesDuTrajet, train);
        Long longOfSize = new Long(lignesDuTrajet.size()); // on recupere le nombre d'heure
        LocalTime nbMin = LocalTime.of(0, 0).plusMinutes(longOfSize * 60); // convertie en minutes
        train.setTempsRestant(train.getTempsRestant().plusHours(nbMin.getHour()));// on l'ajoute au temps du train restant (qui est de 0)
        Exportation.exporterSillons(Controleur.getSillons());
    }

    /**
     * Recupère le trajet saisi par l'utilisateur
     * @param train
     */
    public static void recupererTrajet(Train train) {
        for (Sillon sillon : sillons) {
            if (sillon.getTrain() != null && sillon.getTrain().equals(train)) {
                System.err.println("Le train " + train.getId() + " a déjà un trajet de programmé !\n");
                return;
            }
        }

        List<Ligne> lignesDuTrajet = new ArrayList<>();
        System.out.println("Les lignes sont : " + '\n' + Lignes.getMesLignes());
        Scanner sc = new Scanner(System.in);
        System.out.println("Quelles lignes voulez vous emprunter ?");
        System.out.println("Saisissez ok lorsque vous avez saisi toutes vos lignes");
        for (int i = 0; ; ++i) {
            String libelleLigne = sc.nextLine();
            if (libelleLigne.equals("ok")) break;
            Ligne nouvelleLigneDuTrajet = Lignes.getLigneFromString(libelleLigne);
            if (nouvelleLigneDuTrajet != null)
                if (i > 0)
                    if (!Lignes.duplicatLigneDansTrajet(lignesDuTrajet, nouvelleLigneDuTrajet) && Lignes.lignesConsecutives(libelleLigne, lignesDuTrajet.get(i - 1).getLibelle()))
                        lignesDuTrajet.add(nouvelleLigneDuTrajet);
                    else
                        --i;
                else
                    lignesDuTrajet.add(nouvelleLigneDuTrajet);
            else
                --i;
            System.out.println(lignesDuTrajet);
        }
        if (lignesDuTrajet.size() != 0)
            try {
                attribuerSillons(lignesDuTrajet, train);
                System.out.println("\n* Le trajet " + lignesDuTrajet + " a été enregistré pour le train " + train.getId() + " appartenant à l'entreprise " + train.getEntreprise().getNom() + " *\n");
            } catch (NullPointerException e) {
                System.err.println("Veuillez sélectionner une méthode d'attribution de sillons à partir du menu principal");
            }
    }

    /**
     * Enleve le train d'un sillon lorsqu'il entre en gare
     * @param t
     */
    private static void retirerTrainReseau(Train t) {

        for (Sillon s : Controleur.getSillons()) {
            if (s.getTrain() == t) {
                s.setPriorite(0);
                s.setTrain(null);
            }
        }

    }
}

