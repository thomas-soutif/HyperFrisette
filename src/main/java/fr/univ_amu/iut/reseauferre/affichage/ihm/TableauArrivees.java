package fr.univ_amu.iut.reseauferre.affichage.ihm;

import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Importation;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class TableauArrivees extends Pane implements Observateur {

    private VBox conteneur = new VBox();
    private VBox listeTrains = new VBox();

    public TableauArrivees(Dimension size){
        this.setPrefSize(size.width,size.height);
        this.setStyle("-fx-background-color: rgba(6, 29, 11, 0.1)");

        // titre de ce Pane
        LabelTitre titre = new LabelTitre("Arrivées", new com.sun.javafx.geom.Dimension2D(350,35), 150);
        titre.setAlignment(Pos.CENTER);

        // liste qui contient tous les trains en deplacement
        listeTrains.setPrefSize(100, 265);
        // vbox qui contient le titre et la liste
        conteneur.getChildren().addAll(titre, listeTrains);
        this.getChildren().addAll(conteneur);

    }

    public void actualiser(LocalTime date) {

        Set<Sillon> sillons = new TreeSet<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {


                if( o1.gethArrive().isAfter(o2.gethArrive()))
                    return 1;
                if (o2.gethArrive().isAfter(o1.gethArrive()))
                        return -1;
                else
                    return -1;

            }
        });

        sillons.addAll(Importation.importerSillons());


        conteneur.getChildren().remove(listeTrains);
        listeTrains = new VBox();

        for (Sillon sillon : sillons) {

            if(sillon.getTrain() != null){
                if (sillon.gethArrive().getHour() > date.getHour()) {

                            String label = "Arrivée: " + sillon.gethArrive() + " | Train " + sillon.getTrain().getId() + " | " + sillon.getLigne() + " | "+ sillon.getTrain().getEntreprise().getNom();
                            LabelTitre donnee = new LabelTitre(label, new com.sun.javafx.geom.Dimension2D(500, 10), 100);

                            listeTrains.getChildren().add(donnee);
                }
            }

        }
        conteneur.getChildren().addAll(listeTrains);
    }
}
