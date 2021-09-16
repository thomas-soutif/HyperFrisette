package fr.univ_amu.iut.reseauferre.traitement.Controleur;


import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Exportation;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.time.LocalTime;
import java.util.*;

/**
 * Created by w16002657 on 13/11/17.
 */
public class AttributionGlouton implements AttributionSillon {
    @Override
    public void attribuer(List<Ligne> lignesDuTrajet, Train train) {
        int Cpt = 0;
        Set<Sillon> listSillonDonne = new HashSet<>();
        LocalTime heureDepartSillonPrecedent = null;
        for (Ligne ligne : lignesDuTrajet) {
            for (Sillon sillon : Controleur.getSillons()) {
                if (heureDepartSillonPrecedent == null) {
                    if (sillon.getTrain() == null && sillon.getLigne().equals(ligne)) {
                        train.setPrioriteMax(Cpt);
                        sillon.setTrain(train);
                        sillon.setPriorite(Cpt);
                        ++Cpt;
                        heureDepartSillonPrecedent = sillon.gethDepart();
                        listSillonDonne.add(sillon); // On stocke ce Sillon attribue
                    //    Exportation.exporterSillons(Controleur.getSillons());
                        break;
                    }
                } else {
                    if (sillon.getTrain() == null && sillon.getLigne().equals(ligne) && sillon.gethDepart().isAfter(heureDepartSillonPrecedent)) {
                        train.setPrioriteMax(Cpt);
                        sillon.setTrain(train);
                        sillon.setPriorite(Cpt);
                        ++Cpt;
                        listSillonDonne.add(sillon); // On stocke ce Sillon attribue
                        heureDepartSillonPrecedent = sillon.gethDepart();
                        break;
                    }
                }

            }
        }
        if (!listSillonDonne.isEmpty())
            ApplicationHyperFrisette.calculerCoutTotalTrajet(listSillonDonne); // Prévient HyperFrisette qu'il vient de donne ces sillons à cet entreprise
    }


}
