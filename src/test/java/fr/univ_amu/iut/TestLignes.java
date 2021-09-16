package fr.univ_amu.iut;


import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.*;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vincent on 30/10/2017.
 */
public class TestLignes {

    @Test
    public void testAjoutLigne(){
        Lignes.ajouterLigneSiGaresExistent("Marseille", "Strasbourg");
        System.out.println(Lignes.getMesLignes().size());
        assertTrue(Lignes.getMesLignes().size() == 33);
    }

    @Test
    public void testDuplicatLigneAvecMemesGaresDepartEtArrivee(){
        Ligne ligneDupliquee = new Ligne(Gare.Paris, Gare.Rennes);
        System.out.println(Lignes.ligneExisteDeja(ligneDupliquee));
        assertTrue(Lignes.ligneExisteDeja(ligneDupliquee));
    }

    @Test
    public void testDuplicatLigneAvecGaresInversees(){
        Ligne ligneDupliquee = new Ligne(Gare.Nice, Gare.Marseille);
        System.out.println(Lignes.ligneExisteDeja(ligneDupliquee));
        Lignes.ajouterLigne(ligneDupliquee);
        assertTrue(Lignes.ligneExisteDeja(ligneDupliquee));
    }

    @Test
    public void testNonDuplicatLigne(){
        Ligne ligneNonDupliquee = new Ligne(Gare.Lille, Gare.Lyon);
        System.out.println(Lignes.ligneExisteDeja(ligneNonDupliquee));
        assertFalse(Lignes.ligneExisteDeja(ligneNonDupliquee));
    }

    @Test
    public void testAjoutLigneFauteDeFrappe(){
        Lignes.ajouterLigneSiGaresExistent("LDFSJ", "£¨M%");
        System.out.println(Lignes.getMesLignes().size());
        assertTrue(Lignes.getMesLignes().size() == 32);
    }
}
