package fr.univ_amu.iut.reseauferre.affichage.ihm;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;

import java.time.LocalTime;
import java.util.Collection;


public interface Observateur {
    // recupere les sillons dans le fichier Sillons.ser
    // et met à jour
    public void actualiser(LocalTime date);
}
