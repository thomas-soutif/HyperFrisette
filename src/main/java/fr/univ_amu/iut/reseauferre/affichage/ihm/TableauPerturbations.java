package fr.univ_amu.iut.reseauferre.affichage.ihm;

import com.sun.javafx.geom.Dimension2D;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Gestion_Importation_Exportation.Importation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class TableauPerturbations extends Pane implements Observateur{

    private VBox conteneur = new VBox();
    private VBox listeTrains = new VBox();

    public TableauPerturbations(Dimension size){
        this.setPrefSize(size.width,size.height);
        this.setStyle("-fx-background-color: rgba(6, 29, 11, 0.1)");

        // titre de ce Pane
        LabelTitre titre = new LabelTitre("Perturbations: Pannes", new Dimension2D(500,35), 150);
        titre.setAlignment(Pos.CENTER);

        // liste qui contient tous les trains en deplacement
        listeTrains.setPrefSize(100, 265);

        // vbox qui contient le titre et la liste
        conteneur.getChildren().addAll(titre, listeTrains);
        this.getChildren().addAll(conteneur);

    }

    @Override
    public void actualiser(LocalTime date) {

        Set<Sillon> sillons = new TreeSet<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {


                if( o1.gethDepart().isAfter(o2.gethDepart()))
                    return 1;
                if (o2.gethDepart().isAfter(o1.gethDepart()))
                    return -1;
                else
                    return -1;

            }
        });

        sillons.addAll(Importation.importerSillons());

        conteneur.getChildren().remove(listeTrains);
        listeTrains = new VBox();

        for(Sillon sillon: sillons){
            // on s'assure que le train est en panne

            if(sillon.getTrain() != null && sillon.getTrain().enPanne()){
                String label = "Train " + sillon.getTrain().getId() + " | Départ " + sillon.getLigne().getGareDepart() + " | Arrivée "+ sillon.getLigne().getGareArrivee()+ " | Problème: Panne";
                listeTrains.getChildren().add(new LabelTitre(label, new Dimension2D(500, 10), 100));
            }
        }
        conteneur.getChildren().addAll(listeTrains);
    }
}
