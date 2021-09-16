package fr.univ_amu.iut.reseauferre.traitement.Serialization;


import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.io.*;
import java.util.Collection;

public class Serializeur {

    /**
     * Serialize une collection d'entreprises
     * @param entreprises
     */

    public static void serializerEntreprises(Collection<Entreprise> entreprises){

        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("Entreprises.ser");
            oos = new ObjectOutputStream(fichier);

            for(Entreprise e : entreprises)
                oos.writeObject(e);

            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Serialize une collection de Sillons
     * @param sillons
     */
    public static void serializerSillons(Collection<Sillon> sillons) {

        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("Sillons.ser");
            oos = new ObjectOutputStream(fichier);

            for(Sillon s : sillons)
                oos.writeObject(s);

            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    /**
     * Serialize une collection de trains
     * @param trains
     */
    public static void serializerTrains(Collection<Train> trains) {



        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("Trains.ser");
            oos = new ObjectOutputStream(fichier);

            for(Train t : trains)
                oos.writeObject(t);

            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
