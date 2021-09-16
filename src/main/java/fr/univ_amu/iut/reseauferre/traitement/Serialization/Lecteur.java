package fr.univ_amu.iut.reseauferre.traitement.Serialization;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Lecteur {

    /**
     * Lit dans le fichier passé en paramètre et retourne une collection d'entreprises
     * @param fic
     * @return
     */
    public static Collection<Entreprise> recupererEntreprises(String fic) {
        Collection<Entreprise> entreprises = new ArrayList<Entreprise>();
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichier = new FileInputStream(fic);
            ois = new ObjectInputStream(fichier);
            try {

                while(fichier.available() > 0) {
                    final Entreprise ent = (Entreprise) ois.readObject();
                    entreprises.add(ent);
                }

            } catch (EOFException e) {
                System.out.println("Erreur : Fin de lecture");
            }
            if (ois != null)
                ois.close();

        } catch (FileNotFoundException e) {
            System.out.println("Mauvais nom de fichier");
        } catch (ClassNotFoundException e) {
            System.out.println("Le fichier" + fic + "ne contient pas des objets connus");
        } catch (IOException e) {
            System.out.println("Erreur entree/sortie");
        }
        return entreprises;
    }


    /**
     * Lit dans le fichier passé en paramètre et retourne une collection de sillons
     * @param fic
     * @return
     */
        public static Collection<Sillon> recupererSillons(String fic) {
            Collection<Sillon> sillons = new ArrayList<Sillon>();
            ObjectInputStream ois = null;
            try {
                final FileInputStream fichier = new FileInputStream(fic);
                ois = new ObjectInputStream(fichier);
                try {

                    while(fichier.available() > 0) {
                        final Sillon si = (Sillon) ois.readObject();
                        sillons.add(si);
                    }

                } catch (EOFException e) {
                    System.out.println("Erreur : Fin de lecture");
                }
                if (ois != null)
                    ois.close();

            } catch (FileNotFoundException e) {
                System.out.println("Mauvais nom de fichier");
            } catch (ClassNotFoundException e) {
                System.out.println("Le fichier" + fic + "ne contient pas des objets connus");
            } catch (IOException e) {
                System.out.println("Erreur entree/sortie");
            }
            return sillons;

    }

    /**
     * Lit dans le fichier passé en paramètre et retourne une collection de trains
     * @param fic
     * @return
     */
    public static Collection<Train> recupererTrains(String fic) {
        Collection<Train> trains = new ArrayList<Train>();
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichier = new FileInputStream(fic);
            ois = new ObjectInputStream(fichier);
            try {

                while(fichier.available() > 0) {
                    final Train tr = (Train) ois.readObject();
                    trains.add(tr);
                }

            } catch (EOFException e) {
                System.out.println("Erreur : Fin de lecture");
            }
            if (ois != null)
                ois.close();

        } catch (FileNotFoundException e) {
            System.out.println("Mauvais nom de fichier");
        } catch (ClassNotFoundException e) {
            System.out.println("Le fichier" + fic + "ne contient pas des objets connus");
        } catch (IOException e) {
            System.out.println("Erreur entree/sortie");
        }
        return trains;
    }










}
