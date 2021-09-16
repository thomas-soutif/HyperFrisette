package fr.univ_amu.iut.reseauferre.traitement.Controleur;


import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.TarifType;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;


public class Sillon implements TarifType,Serializable {
    private int id;
    private LocalTime hDepart;
    private LocalTime hArrive;
    private Train train;
    private Ligne ligne;
    private int priorite;




    public Sillon(LocalTime hDepart,LocalTime hArrive,Train train,Ligne ligne, int priorite) {
        //this.id = id;
        this.hDepart = hDepart;
        this.hArrive = hArrive;
        this.train = train;
        this.ligne = ligne;
        this.priorite = priorite;

    }

    public int getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public Ligne getLigne() {return ligne;}

    public LocalTime gethDepart() {
        return hDepart;
    }

    public LocalTime gethArrive() {
    	return hArrive;
    }

    public int getPriorite() { return priorite;}

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public void setPriorite(int priorite) {this.priorite = priorite;}

    public double getPrixHoraire() {
        int heureDepart = this.gethDepart().getHour();
        if(heureDepart >= 0 && heureDepart <= 6)
            return 50;

        else if(heureDepart >= 7 && heureDepart <= 12)
            return 150;

        else if(heureDepart >= 13 && heureDepart <= 18)
            return 100;

        else if(heureDepart >= 13 && heureDepart <= 18)
            return 70;

        else if(heureDepart >= 19 && heureDepart <= 23)
            return 20;

        return 0;
    }


    @Override
    public String toString() {
        return "Sillon{" +
                "id=" + id +
                ", hDepart=" + new SimpleDateFormat("H a").format(hDepart) +
                ", hArrive=" + new SimpleDateFormat("H a").format(hArrive) +
                ", train=" + train +
                ", ligne=" + ligne +
                ", priorite=" + priorite +
                '}'+ '\n';
    }

    public String affichage() {
        return  "Depart=" + hDepart.toString() +
                ", Arrivée=" + hArrive.toString() +
                ", ligne=" + ligne;
    }

    @Override
    public void getCout(PolitiqueTarifaire po) {
        po.getPrix(this);
    }
}
