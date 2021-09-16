package fr.univ_amu.iut.reseauferre.affichage.ihm;


import java.time.LocalDate;
import java.time.LocalTime;

public interface Observable {

    public void notifier();

    public void ajouterObservateur(Observateur obs);
    public void supprimerObservateur(Observateur obs);
}
