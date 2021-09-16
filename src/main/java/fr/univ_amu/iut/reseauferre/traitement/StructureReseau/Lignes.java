package fr.univ_amu.iut.reseauferre.traitement.StructureReseau;

import java.util.*;

/**
 * Classe stockant les lignes existantes dans une collection statique.
 *
 *
 * @see Ligne
 */

public class Lignes {

    /**
     * Collection regroupant toutes les lignes déjà existantes.
     *
     * @see Ligne
     */
    private static Set<Ligne> mesLignes = new TreeSet<>(new Comparator<Ligne>() {
        @Override
        public int compare(Ligne ligne1, Ligne ligne2) {
            return ligne1.getLibelle().compareTo(ligne2.getLibelle());
        }
    });

    static {
        mesLignes.add(new Ligne(Gare.Paris,     Gare.Lille));
        mesLignes.add(new Ligne(Gare.Paris,     Gare.Strasbourg));
        mesLignes.add(new Ligne(Gare.Paris,     Gare.Lyon));
        mesLignes.add(new Ligne(Gare.Paris,     Gare.Orleans));
        mesLignes.add(new Ligne(Gare.Paris,     Gare.Rennes));
        mesLignes.add(new Ligne(Gare.Lille,     Gare.Strasbourg));
        mesLignes.add(new Ligne(Gare.Lille,     Gare.Rouen));
        mesLignes.add(new Ligne(Gare.Rennes,    Gare.Rouen));
        mesLignes.add(new Ligne(Gare.Rennes,    Gare.Nantes));
        mesLignes.add(new Ligne(Gare.Bordeaux,  Gare.Nantes));
        mesLignes.add(new Ligne(Gare.Bordeaux,  Gare.Orleans));
        mesLignes.add(new Ligne(Gare.Bordeaux,  Gare.Toulouse));
        mesLignes.add(new Ligne(Gare.Lyon,      Gare.Strasbourg));
        mesLignes.add(new Ligne(Gare.Marseille, Gare.Lyon));
        mesLignes.add(new Ligne(Gare.Marseille, Gare.Nice));
        mesLignes.add(new Ligne(Gare.Marseille, Gare.Toulouse));

        mesLignes.add(new Ligne(Gare.Lille,     Gare.Paris));
        mesLignes.add(new Ligne(Gare.Strasbourg,     Gare.Paris));
        mesLignes.add(new Ligne(Gare.Lyon,     Gare.Paris));
        mesLignes.add(new Ligne(Gare.Orleans,     Gare.Paris));
        mesLignes.add(new Ligne(Gare.Rennes,     Gare.Paris));
        mesLignes.add(new Ligne(Gare.Strasbourg,     Gare.Lille));
        mesLignes.add(new Ligne(Gare.Rouen,     Gare.Lille));
        mesLignes.add(new Ligne(Gare.Rouen,    Gare.Rennes));
        mesLignes.add(new Ligne(Gare.Nantes,    Gare.Rennes));
        mesLignes.add(new Ligne(Gare.Nantes,  Gare.Bordeaux));
        mesLignes.add(new Ligne(Gare.Orleans,  Gare.Bordeaux));
        mesLignes.add(new Ligne(Gare.Toulouse,  Gare.Bordeaux));
        mesLignes.add(new Ligne(Gare.Strasbourg,      Gare.Lyon));
        mesLignes.add(new Ligne(Gare.Lyon, Gare.Marseille));
        mesLignes.add(new Ligne(Gare.Nice, Gare.Marseille));
        mesLignes.add(new Ligne(Gare.Toulouse, Gare.Marseille));
    }

    /**
     * Renvoie la collection de lignes existantes
     * @return
     *
     * @see Ligne
     */
    public static Set<Ligne> getMesLignes() {
        return mesLignes;
    }

    /**
     * Renvoie une ligne exisante si le nom passé en paramètre correspond au nom de cette dernière.
     * @param libelleLigne
     * @return
     *
     * @see Ligne
     */
    public static Ligne getLigneFromString(String libelleLigne) {
        for (Ligne ligne : mesLignes) {
            if (ligne.getLibelle().equals(libelleLigne))
                return ligne;
        }
        System.err.println("La ligne entrée n'existe pas. Réessayez.");
        return null;
    }

    /**
     * Renvoie vrai si les deux lignes passées en paramètre sont consécutives. Renvoie faux sinon.
     * @param ligne
     * @param ancienneLigne
     * @return
     */
    public static boolean lignesConsecutives(String ligne, String ancienneLigne) {
        if (Lignes.getLigneFromString(ligne).getGareDepart().getNom().equals(Lignes.getLigneFromString(ancienneLigne).getGareArrivee().getNom()))
            return true;
        System.err.println("La ligne saisie précédemment et la dernière ligne saisie ne sont pas consécutives");
        return false;
    }

    /**
     * Renvoie vrai si la ligne passé en paramètre existe dans la collection de ligne passée en paramètre. Renvoie faux sinon.
     * @param lignesDuTrajet
     * @param nouvelleLigne
     * @return
     */
    public static boolean duplicatLigneDansTrajet(List<Ligne> lignesDuTrajet, Ligne nouvelleLigne) {
        for (Ligne ligne : lignesDuTrajet) {
            if(ligne.equals(nouvelleLigne)) {
                System.err.println("Impossible d'emprunter deux fois la même ligne dans un trajet");
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode permettant d'ajouter une ligne à la collection de ligne de la classe. Elle vérfie que les gares passées en paramètre existe, puis invoque lé méthode ajouterLigne.
     * @param gareDepartSaisie
     * @param gareArriveeSaisie
     *
     * @see Lignes#ajouterLigne(Ligne)
     */
    public static void ajouterLigneSiGaresExistent(String gareDepartSaisie, String gareArriveeSaisie) {
        for (Gare gareDepart : Gare.values())
            if(gareDepart.getNom().equals(gareDepartSaisie)) {
                Gare gareDepartExistante = gareDepart;
                for (Gare gareArrivee : Gare.values())
                    if (gareArrivee.getNom().equals(gareArriveeSaisie)) {
                        Gare gareArriveeExistante = gareArrivee;
                        Ligne nouvelleLigne = new Ligne(gareDepartExistante, gareArriveeExistante);
                        ajouterLigne(nouvelleLigne);
                        return;
                    }
            }
        System.err.println("L'une des deux gares entrées n'existe pas.");
    }

    /**
     * Méthode ajoutant la ligne passée en paramètre à la collection de ligne de la classe. Elle vérifie d'abord que la ligne n'existe pas.
     * @param nouvelleLigne
     *
     * @see Lignes#ligneExisteDeja(Ligne)
     */
    public static void ajouterLigne (Ligne nouvelleLigne) {
        if (!ligneExisteDeja(nouvelleLigne)) {
            mesLignes.add(nouvelleLigne);
            System.out.println("\n* La ligne " + nouvelleLigne.getLibelle() + " a été créée avec succès *");
        }
        else
            System.err.println("La ligne " + nouvelleLigne.getLibelle() + " existe déjà");

    }

    /**
     * Renvoie vrai si la ligne passée en paramètre existe déjà dans la collection de ligne de la classe.
     * @param ligne
     * @return
     */
    public static boolean ligneExisteDeja(Ligne ligne) {
        for (Ligne ligne1 : mesLignes) {
            if ((ligne.getGareDepart()  == ligne1.getGareDepart()  && ligne.getGareArrivee()  == ligne1.getGareArrivee()))
                return true;
        }
        return false;
    }
}
