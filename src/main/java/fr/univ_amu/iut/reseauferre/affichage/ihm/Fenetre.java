package fr.univ_amu.iut.reseauferre.affichage.ihm;

import fr.univ_amu.iut.reseauferre.affichage.Systeme;
import fr.univ_amu.iut.reseauferre.traitement.ApplicationHyperFrisette;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.StructureReseau.Ligne;
import fr.univ_amu.iut.reseauferre.traitement.Train.Train;
import fr.univ_amu.iut.reseauferre.traitement.Train.TrainPassager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Fenetre extends Application{

    private ApplicationHyperFrisette appli;

    private  Titre                titre;
    private  TableauDeparts       tableauDepart;
    private  TableauArrivees      tableauArrivees;
    private  TableauPerturbations perturbations;
    private  TableauEtatReseau    etatReseau;

    public Fenetre(){
        this.appli = new ApplicationHyperFrisette();

        titre           = new Titre();
        tableauDepart   = new TableauDeparts(new Dimension(350, 30));
        tableauArrivees = new TableauArrivees(new Dimension(350, 350));
        perturbations   = new TableauPerturbations(new Dimension(800, 350));
        etatReseau      = new TableauEtatReseau(new Dimension(350,350));

        // on ajoute l'heure de début
        titre.getHeure().setText(appli.getTempsCourant().toString());

        tableauArrivees.actualiser(appli.getTempsCourant());
        tableauDepart.actualiser(appli.getTempsCourant());
        etatReseau.actualiser(appli.getTempsCourant());
        perturbations.actualiser(appli.getTempsCourant());

        // on ajoute à l'appli les observateurs
        // qui attentendt un changement d'heure
        appli.ajouterObservateur(tableauDepart);
        appli.ajouterObservateur(tableauArrivees);
        appli.ajouterObservateur(perturbations);
        appli.ajouterObservateur(etatReseau);

        // ecouteur bouton
        titre.getActualiseur().setOnMouseClicked(new EventHandler<MouseEvent>(){
             public void handle(MouseEvent me){
                LocalTime nouvelleHeure = appli.incrementerHeure(10);
                System.out.println(nouvelleHeure.toString());
                titre.getHeure().setText(nouvelleHeure.toString());
            }
        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application HyperFrisette");

        Group root = new Group();
        BorderPane bp = new BorderPane();

        // ajout au tout dans le borderPane
        bp.setTop(titre);
        bp.setLeft  (tableauDepart);
        bp.setRight (tableauArrivees);
        bp.setBottom(perturbations);
        bp.setCenter(etatReseau);

        // ajout des elements
        root.getChildren().add(bp);
        primaryStage.setScene(new Scene(root, 1050, 550));
        primaryStage.show();

    }

    public static void main(String[] args){
        try {
            launch(args);
        }
        catch(Exception e){
            System.out.println("Exception inconnue");
            e.getMessage();
            e.printStackTrace();
        }
    }
}
