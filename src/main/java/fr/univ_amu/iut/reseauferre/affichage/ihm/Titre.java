package fr.univ_amu.iut.reseauferre.affichage.ihm;

import com.sun.javafx.geom.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Titre extends Pane {

    private Button actualiseur;
    private Label heure = new LabelTitre("HH:MM", new Dimension2D(150, 50), 150);// ajoute les element et spécifie taille partie de droite

    public Titre(){

        actualiseur = new Button("Actualiser");
        this.setPrefSize(800, 50);
        this.setStyle("-fx-background-color: rgba(6, 29, 11, 0.1)");

        Label titre = new LabelTitre("Réseau HyperFrisette", new Dimension2D(800,50), 150);
        titre.setAlignment(Pos.CENTER);

        //
        actualiseur.relocate(100,100);

        // partie de droite actualisation heure
        HBox temps = new HBox();

        actualiseur.setPrefSize(75,25);

        temps.getChildren().addAll(heure, actualiseur);
        actualiseur.setStyle("-fx-margin-top: 20px;");
        temps.setPrefSize(300,50);


        BorderPane bp = new BorderPane();
        bp.setPrefSize(900,50);
        bp.setLeft(titre);
        bp.setRight(temps);
        titre.setAlignment(Pos.CENTER);

        this.getChildren().addAll(bp);
    }

    public Button getActualiseur() {
        return actualiseur;
    }

    public Label getHeure() {
        return heure;
    }
}
