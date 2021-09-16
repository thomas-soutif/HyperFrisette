package fr.univ_amu.iut.reseauferre.traitement.Controleur;

import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;

import java.util.List;

/**
 * Created by w16002657 on 13/11/17.
 */
public interface AttributionSillon {
    public void attribuer(List<Ligne> lignesDuTrajet, Train train);

}
