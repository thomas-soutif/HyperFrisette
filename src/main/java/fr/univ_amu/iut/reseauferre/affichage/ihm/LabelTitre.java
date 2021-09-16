package fr.univ_amu.iut.reseauferre.affichage.ihm;

import com.sun.javafx.geom.Dimension2D;
import javafx.scene.control.Label;

public class LabelTitre extends Label {

    public LabelTitre(String titre, Dimension2D size, int textSize){
        super(titre);
        this.setPrefSize(size.width, size.height);
        this.setStyle("-fx-font-size: " + textSize + "%;");
    }
}
