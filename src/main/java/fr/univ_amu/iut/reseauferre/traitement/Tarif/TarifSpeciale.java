package fr.univ_amu.iut.reseauferre.traitement.Tarif;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Train.*;

public class TarifSpeciale implements PolitiqueTarifaire {

    @Override
    public void getPrix(TrainPassager trainPassager) {

    }

    @Override
    public void getPrix(Streaming trainStream) {

    }

    @Override
    public void getPrix(Wifi trainWifi) {

    }


    @Override
    public void getPrix(TrainMarchandise trainMarchandise) {

    }

    @Override
    public void getPrix(Sillon sillon) {

    }

    @Override
    public double getCoutTotal() {
        return 0;
    }
}
