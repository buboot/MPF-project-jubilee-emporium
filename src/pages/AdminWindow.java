package pages;

import java.io.File;

import charm.Charms;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;

public class AdminWindow {
	private static final double MAX_SCALE = 1.5;
	 private static final double MIN_SCALE = 0.75;
	 private static final double ZOOM_FACTOR = 0.1;
	 private static final double ROTATE_ANGLE = 15;
	
	private Main app;
	private User current;
	Charms charm;
	Items item;
	ShopWindow s;
	
	Stage primaryStage;
	Scene scene;
	BorderPane root;
	
	
	public AdminWindow(Main app, User current) {
		this.app = app;
		this.current = current;
		
		this.charm = new Charms();

		
		//============================================================================
		
		
		root = new BorderPane();
        root.setStyle("-fx-background-color: #7b4207;");
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem logoutItem = new MenuItem("Logout");
        menu.getItems().add(logoutItem);
        menuBar.getMenus().add(menu);

        Label adminText = new Label("ADMIN PAGE");
        adminText.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 18; -fx-font-weight: bold;");
        adminText.setTextFill(Color.ANTIQUEWHITE);

        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(10, 120, 10, 120));
        contentBox.setPrefWidth(1440);
        contentBox.setSpacing(18);
        contentBox.setStyle("-fx-background-color: #7b4207;");
        contentBox.setAlignment(Pos.CENTER);
        
        

        HBox charmBox1 = createCharmBox("Heart", "Assets/image/heart.png", 5, 10, charm.hdesc);
        HBox charmBox2 = createCharmBox("Coffee", "Assets/image/coffee.png", 8, 7, charm.cdesc);
        HBox charmBox3 = createCharmBox("Whetstone", "Assets/image/whetstone.png", 6, 6, charm.wdesc);
        HBox charmBox4 = createCharmBox("Twin Heart", "Assets/image/twinheart.png", 5, 3, charm.tdesc);
        HBox charmBox5 = createCharmBox("Smoke Bomb", "Assets/image/smokebomb.png", 6, 12, charm.sdesc);

        contentBox.getChildren().addAll(charmBox1, charmBox2, charmBox3, charmBox4, charmBox5);
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(contentBox);
        scroll.getContent().setLayoutX(0);
        scroll.getContent().setLayoutY(0);
        scroll.setStyle("-fx-background-color: #7b4207");
        
        VBox mainContentBox = new VBox();
        //mainContentBox.setPadding(new Insets(40));
        mainContentBox.setSpacing(20);
        mainContentBox.setAlignment(Pos.TOP_CENTER);
        mainContentBox.getChildren().addAll(adminText, scroll);
        
        

        StackPane centerPane = new StackPane(mainContentBox);
        centerPane.setAlignment(Pos.TOP_CENTER);
        //centerPane.setPadding(new Insets(20));

        logoutItem.setOnAction(e -> {
            app.showLoginPage();
        });
        
        
        
        
        root.setTop(menuBar);
        root.setCenter(centerPane);
        //scene = new Scene(root, 1440, 800);
	}
	
	
	
	private HBox createCharmBox(String name, String imagePath, double price, int stock, String description) {
        HBox charmBox = new HBox();
        charmBox.setPadding(new Insets(5, 80, 5, 80));
        charmBox.setSpacing(10);
        charmBox.setStyle("-fx-background-color: #a95121; -fx-border-color: #A0522D; -fx-border-width: 2;");
        charmBox.setPrefWidth(800);
        charmBox.setPrefHeight(220);

        VBox imageBox = new VBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setSpacing(10);
        imageBox.setPrefWidth(300);
        imageBox.setPrefHeight(180);

        //ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = charmImage(imagePath);
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(100);
        imageView.setOnMouseClicked(e -> {
        	charm.popupCharm(name, imagePath, description);
        });

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 18; -fx-font-weight: bold;");
        nameLabel.setTextFill(Color.ANTIQUEWHITE);;
        nameLabel.setPrefWidth(100);
        nameLabel.setMaxWidth(200);

        imageBox.getChildren().addAll(nameLabel,imageView);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(10);
        detailsBox.setAlignment(Pos.CENTER_LEFT);
        detailsBox.setPrefWidth(650);

        HBox priceStockBox = new HBox();
        priceStockBox.setSpacing(10);
        priceStockBox.setAlignment(Pos.CENTER_LEFT);

        Label priceLabel = new Label("Price:");
        priceLabel.setStyle("-fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 12; -fx-font-weight: bold;");
        TextField priceField = new TextField(String.valueOf(price));
        priceField.setPrefWidth(150);

        Label stockLabel = new Label("Stock:");
        stockLabel.setStyle("-fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 12; -fx-font-weight: bold;");
        Spinner<Integer> stockSpinner = new Spinner<>(1, 100, stock);

        priceStockBox.getChildren().addAll(priceLabel, priceField, stockLabel, stockSpinner);

        VBox descriptionBox = new VBox();
        descriptionBox.setSpacing(5);
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 12; -fx-font-weight: bold;");
        TextArea descriptionArea = new TextArea(description);
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefWidth(600);
        descriptionArea.setPrefHeight(60);

        descriptionBox.getChildren().addAll(descriptionLabel, descriptionArea);

        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #683404; -fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 12; -fx-font-weight: bold;");
        updateButton.setPrefWidth(100);
        updateButton.setOnAction(e -> {
            try {
                int newPrice = Integer.parseInt(priceField.getText());
                int newStock = stockSpinner.getValue();
                String newDescription = descriptionArea.getText();

                if (newPrice <= 0 || newStock <= 0 || newDescription.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Price and stock must be more than 0, and description must be filled in.");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Charm information updated successfully.");

                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Price must be numeric and more than 0.");
            }
        });

        detailsBox.getChildren().addAll(priceStockBox, descriptionBox, updateButton);
        charmBox.getChildren().addAll(imageBox, detailsBox);

        return charmBox;
    }
	


	
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
 
	
	private ImageView charmImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	public void changeScene(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void StartPage(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public BorderPane getBorderPane() {
		return root;
	}
	
	
}


