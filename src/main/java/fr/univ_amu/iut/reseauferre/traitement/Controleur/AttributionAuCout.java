package fr.univ_amu.iut.reseauferre.traitement.Controleur;

import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.time.LocalTime;
import java.util.*;


public class AttributionAuCout implements AttributionSillon {
    @Override
    public void attribuer(List<Ligne> lignesDuTrajet, Train train) {

        Set<Sillon> queue = new TreeSet<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon s1, Sillon s2) {
                if (s1.getPrixHoraire() < s2.getPrixHoraire())
                    return -1;
                else
                    return 1;
            }
        });
        for (Sillon s : Controleur.getSillons()) {
            if (s.getTrain() == null)
                queue.add(s);
        }
        Set<Sillon> listSillonDonne = new HashSet<>();
        LocalTime heureDepartSillonPrecedent = null;
        int cpt = 0;
        for (Ligne ligne : lignesDuTrajet) {
            for (Sillon s : queue) {
                if (heureDepartSillonPrecedent == null) {
                    if (s.getLigne().equals(ligne)) {
                        heureDepartSillonPrecedent = s.gethDepart();
                        train.setPrioriteMax(cpt);
                        s.setTrain(train);
                        s.setPriorite(cpt);
                        ++cpt;
                        listSillonDonne.add(s);
                        break;
                    }
                } else {
                    if (s.getLigne().equals(ligne) && s.gethDepart().isAfter(heureDepartSillonPrecedent)) {

                        train.setPrioriteMax(cpt);
                        s.setTrain(train);
                        s.setPriorite(cpt);
                        listSillonDonne.add(s);
                        ++cpt;
                        heureDepartSillonPrecedent = s.gethDepart();
                        break;

                    }

                }
            }

        }

        if (!listSillonDonne.isEmpty())
            ApplicationHyperFrisette.calculerCoutTotalTrajet(listSillonDonne);


    }


}
