package fr.univ_amu.iut;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import org.junit.Test;

public class SillonTest {


    @Test

    public void testerConversionHeureDepartEtArrivee(){

        for(Sillon s : Controleur.getSillons())
        {
            System.out.println(s.gethDepart().getHour() + " ==> " + s.gethArrive().getHour() );

        }



    }

}
