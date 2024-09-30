package pages;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;

public class InventoryWindow {
	
	private User current;
	private Main app;
	ShopWindow nitip = new ShopWindow(app, current);
	Stage primaryStage;
	Scene scene;
	BorderPane bp;
	
	FlowPane fpinv;
	GridPane gp;
	VBox heartbox, coffeebox, whetbox, twinbox, smokebox;
	
	Button uheart, ucoffee, uwhet, utwin, usmoke;
	
	Label title;
	Label heartq, coffeeq, whetq, twinq, smokeq;
	
	int qh = 1;
	int qc = 1;
	int qw = 1;
	int qt = 1;
	int qs = 1;
	
	
	public InventoryWindow(Main app, User current) {
		this.current = current;
		this.app = app;
		
		bp = new BorderPane();
		//scene = new Scene(bp, 1440, 800);
		
		fpinv = new FlowPane();
		gp = new GridPane();
		
		title = new Label(current.getUserName()  +"'s Inventory");
		
		uheart = new Button("Use Charm");
		ucoffee = new Button("Use Charm");
		uwhet = new Button("Use Charm");
		utwin = new Button("Use Charm");
		usmoke = new Button("Use Charm");
		
		heartq = new Label("Quantity : " + qh);
		coffeeq = new Label("Quantity : " + qc);
		whetq = new Label("Quantity : " + qw);
		twinq = new Label("Quantity : " + qt);
		smokeq = new Label("Quantity : " + qs);
		
		heartbox = new VBox();
		coffeebox = new VBox();
		whetbox = new VBox();
		twinbox = new VBox();
		smokebox = new VBox();
		
		
		// boxes
		
		//heart vbox
		heartbox.setPrefSize(230, 300);
		heartbox.setAlignment(Pos.CENTER);
		heartbox.getChildren().addAll(nitip.heart, nitip.heartImg, heartq, uheart);
		
	
		//coffee vbox
		coffeebox.setPrefSize(230, 300);

		coffeebox.setAlignment(Pos.CENTER);
		coffeebox.getChildren().addAll(nitip.coffee, nitip.coffeeImg, coffeeq, ucoffee);
		
		//whet vbox
		whetbox.setPrefSize(230, 300);
	
		whetbox.setAlignment(Pos.CENTER);
		whetbox.getChildren().addAll(nitip.whet, nitip.whetImg, whetq, uwhet);
		
		//twinheart vbox
		twinbox.setPrefSize(230, 300);
	
		twinbox.setAlignment(Pos.CENTER);
		twinbox.getChildren().addAll(nitip.twin, nitip.twinImg, twinq, utwin);
		
		//smokebomb vbox
		smokebox.setPrefSize(230, 300);
	
		smokebox.setAlignment(Pos.CENTER);
		smokebox.getChildren().addAll(nitip.smoke, nitip.smokeImg, smokeq, usmoke);
		
		//===================fp====================
		
		fpinv.setPrefSize(800, 800);
		//fpinv.setStyle("-fx-background-color: #c4741f");
		fpinv.setAlignment(Pos.TOP_CENTER);
		
		for (String item : current.getInventory()) {
			if (item.equals("Heart")){
				fpinv.getChildren().add(heartbox);
			}
			if (item.equals("Coffee")){
				fpinv.getChildren().add(coffeebox);
			}
			if (item.equals("Whetstone")){
				 fpinv.getChildren().add(whetbox);
			}
			if (item.equals("Twin Heart")){
				 fpinv.getChildren().add(twinbox);
			}
			if (item.equals("Smoke Bomb")){
				 fpinv.getChildren().add(smokebox);
			}
           
        }
		
		uheart.setOnAction(e -> {
			fpinv.getChildren().remove(heartbox);
		});
		ucoffee.setOnAction(e -> {
			fpinv.getChildren().remove(coffeebox);
		});
		uwhet.setOnAction(e -> {
			fpinv.getChildren().remove(whetbox);
		});
		utwin.setOnAction(e -> {
			fpinv.getChildren().remove(twinbox);
		});
		usmoke.setOnAction(e -> {
			fpinv.getChildren().remove(smokebox);
		});
		
		fpinv.setHgap(20);
		fpinv.setVgap(20);
		
		
		//================gp=======================
		
		gp.add(title, 0, 0);
		gp.add(fpinv, 0, 1);
		gp.setAlignment(Pos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		
		gp.setVgap(15);
		
		// ===============menu=============================
		Menu menu = new Menu("Menu");
		MenuItem shop = new MenuItem("Shop");
		MenuItem logout = new MenuItem("Logout");
		menu.getItems().addAll(shop, logout);
		MenuBar menubar = new MenuBar(menu);
		
		shop.setOnAction(e -> {
			app.showShop();
		});
		
		logout.setOnAction(e -> {
			app.showLoginPage();
		});
		
//		logout.setOnAction(e -> {
//			LoginWindow login = new LoginWindow();
//			login.StartPage(primaryStage);
//		});
		//style
		heartbox.setSpacing(8);
		coffeebox.setSpacing(8);
		whetbox.setSpacing(8);
		twinbox.setSpacing(8);
		smokebox.setSpacing(8);
		
		changeLabelStyles(heartbox);
		changeLabelStyles(coffeebox);
		changeLabelStyles(whetbox);
		changeLabelStyles(twinbox);
		changeLabelStyles(smokebox);
		
		heartbox.setStyle("-fx-background-color: #a95121");
		coffeebox.setStyle("-fx-background-color: #a95121");
		whetbox.setStyle("-fx-background-color: #a95121");
		twinbox.setStyle("-fx-background-color: #a95121");
		smokebox.setStyle("-fx-background-color: #a95121");
		
		bp.setStyle("-fx-background-color: #7b4207");
		
		title.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 32; -fx-font-weight: bold;");
		title.setTextFill(Color.ANTIQUEWHITE);
		
		uheart.setStyle("-fx-background-color: #7b4207");
		ucoffee.setStyle("-fx-background-color: #7b4207");
		uwhet.setStyle("-fx-background-color: #7b4207");
		utwin.setStyle("-fx-background-color: #7b4207");
		usmoke.setStyle("-fx-background-color: #7b4207");
		
		uheart.setTextFill(Color.ANTIQUEWHITE);
		ucoffee.setTextFill(Color.ANTIQUEWHITE);
		uwhet.setTextFill(Color.ANTIQUEWHITE);
		utwin.setTextFill(Color.ANTIQUEWHITE);
		usmoke.setTextFill(Color.ANTIQUEWHITE);
		
		//========layout===========
		
		bp.setTop(menubar);
		bp.setCenter(gp);
	}
	
	public void StartPage(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public BorderPane getBorderPane() {
		return bp;
	}
	public void changeLabelStyles(VBox vbox) {
        for (var node : vbox.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setTextFill(Color.ANTIQUEWHITE); 	
                label.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 16; -fx-font-weight: bold;");  
            }
        }
    }
}
