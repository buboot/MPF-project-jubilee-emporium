package charm;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Charms {
	
	 private static final double MAX_SCALE = 1.5;
	 private static final double MIN_SCALE = 0.75;
	 private static final double ZOOM_FACTOR = 0.1;
	 private static final double ROTATE_ANGLE = 15;
	
	 public String hdesc = "Adds an additional hit point but lightly weakens your attack power";
     public String cdesc = "Super meter continuously fills in addition to what you earn";
     public String wdesc = "Your first parry move doubles as a damaging axe attack.";
     public String tdesc = "Adds two additional hit points but weakens your attack power.";
     public String sdesc = "You will not take damage during a dash. A great defense maneuver";
	 
	public Charms() {
		
	}
	
	public Stage popupCharm(String name, String imagePath, String desc) {
		Stage viewCharm = new Stage();
		
		BorderPane root = new BorderPane();
		Scene scCharm = new Scene(root,400,400);
		
        root.setBackground(new Background(new BackgroundFill(Color.web("#a04404"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: white;");
        Label descriptionLabel = new Label(desc);
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: white;");
        ImageView imageView = charmImage(imagePath);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        Button zoomInButton = new Button("+");
        Button zoomOutButton = new Button("-");
        Button rotateLeftButton = new Button("←");
        Button rotateRightButton = new Button("→");

        zoomInButton.setOnAction(e -> {
            double newScaleX = imageView.getScaleX() + ZOOM_FACTOR;
            double newScaleY = imageView.getScaleY() + ZOOM_FACTOR;
            if (newScaleX <= MAX_SCALE && newScaleY <= MAX_SCALE) {
                imageView.setScaleX(newScaleX);
                imageView.setScaleY(newScaleY);
            }
        });

        zoomOutButton.setOnAction(e -> {
            double newScaleX = imageView.getScaleX() - ZOOM_FACTOR;
            double newScaleY = imageView.getScaleY() - ZOOM_FACTOR;
            if (newScaleX >= MIN_SCALE && newScaleY >= MIN_SCALE) {
                imageView.setScaleX(newScaleX);
                imageView.setScaleY(newScaleY);
            }
        });

        rotateLeftButton.setOnAction(e -> imageView.setRotate(imageView.getRotate() - ROTATE_ANGLE));
        rotateRightButton.setOnAction(e -> imageView.setRotate(imageView.getRotate() + ROTATE_ANGLE));

        HBox buttonBox = new HBox(10, zoomInButton, zoomOutButton, rotateLeftButton, rotateRightButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox charmBox = new VBox(10, nameLabel, imageView, descriptionLabel, buttonBox);
        charmBox.setAlignment(Pos.CENTER);

        root.setCenter(charmBox);
        
        viewCharm.setScene(scCharm);
        viewCharm.showAndWait();
		return viewCharm;
	}
	
	private ImageView charmImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
}
