package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

import charm.Charms;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;


public class ShopWindow {
	
	private User current;
	private Main app;
	InventoryWindow inv;
	Charms charm = new Charms();
	
	Stage primaryStage;
	Scene scene;
	BorderPane bp;
	
	ScrollPane sp;
	FlowPane fpcart;
	FlowPane fpcharm;
	GridPane gp, gpcharm, gpcart;
	HBox hbox;
	
	VBox heartvbox, coffeevbox, whetvbox, twinvbox, smokevbox;
	VBox hvbox, cvbox, wvbox, tvbox, svbox;
	Border border;

	Label heart, coffee, whet, twin, smoke; 
	Label cheart, ccoffee, cwhet, ctwin, csmoke; 
	Label qheart, qcoffee, qwhet, qtwin, qsmoke; 
	Label Hprice, Cprice, Wprice, Tprice, SBprice;
	Label Hstock, Cstock, Wstock, Tstock, SBstock;
	Label cshop, labelcoin, labelcart, labeltotal;
	Label empty;
	
	ImageView heartImg;
	ImageView coffeeImg;
	ImageView whetImg;
	ImageView twinImg;
	ImageView smokeImg;
	
	Button co, clear;
	int total=0;
	
	private List<String> cart = new ArrayList<>();
	

	int hstocks = 10; int hPrice = 5;
	int cstocks = 7; int cPrice = 8;
	int wstocks = 6; int wPrice = 6;
	int tstocks = 3; int tPrice = 5;
	int sbstocks = 12; int sbPrice = 6;

	public ShopWindow(Main app, User current) {
		this.app = app;
		this.current = current;
		
		bp = new BorderPane();
		//scene = new Scene(bp, 1440, 800);
		
		heartvbox = new VBox();
		coffeevbox = new VBox();
		whetvbox = new VBox();
		twinvbox = new VBox();
		smokevbox = new VBox();
		
		hvbox = new VBox();
		cvbox = new VBox();
		wvbox = new VBox();
		tvbox = new VBox();
		svbox = new VBox();
		
		gpcharm = new GridPane();
		gpcart = new GridPane();
		fpcharm = new FlowPane();
		fpcart = new FlowPane();
		gp = new GridPane();
		hbox = new HBox();
		sp = new ScrollPane();
		
		heart = new Label("Heart");
		coffee = new Label("Coffee");
		whet = new Label("Whetstone");
		twin = new Label("Twin Heart");
		smoke = new Label("Smoke Bomb");
		
		co = new Button("Checkout");
		clear = new Button("Clear Cart");
		
		labelcart = new Label("Your Cart");
		labeltotal = new Label("Total : " + total);
		
		empty = new Label("Your cart is currently empty!");
		// ================pane content=================================

		heartImg = charmImage("Assets/image/heart.png");
		coffeeImg = charmImage("Assets/image/coffee.png");
		whetImg = charmImage("Assets/image/whetstone.png");
		twinImg = charmImage("Assets/image/twinheart.png");
		smokeImg = charmImage("Assets/image/smokebomb.png");
		
		
		
		Hstock= new Label("Stock : " + hstocks);
		Cstock = new Label("Stock : " + cstocks);
		Wstock = new Label("Stock : " + wstocks);
		Tstock = new Label("Stock : " + tstocks);
		SBstock = new Label("Stock : " + sbstocks);
		
		Hprice = new Label("Price : " + hPrice);
		Cprice = new Label("Price : " + cPrice);
		Wprice = new Label("Price : " + wPrice);
		Tprice = new Label("Price : " + tPrice);
		SBprice = new Label("Price : " + sbPrice);
		
		//
		
		heartImg.setOnMouseClicked(e -> {
			charm.popupCharm("Heart", "Assets/image/heart.png", charm.hdesc);
		});
		coffeeImg.setOnMouseClicked(e -> {
			charm.popupCharm("Coffee", "Assets/image/coffee.png", charm.cdesc);
		});
		whetImg.setOnMouseClicked(e -> {
			charm.popupCharm("Whetstone", "Assets/image/whetstone.png", charm.wdesc);
		});
		twinImg.setOnMouseClicked(e -> {
			charm.popupCharm("Twin Heart", "Assets/image/twinheart.png", charm.tdesc);
		});
		smokeImg.setOnMouseClicked(e -> {
			charm.popupCharm("Smoke Bomb", "Assets/image/smokebomb.png", charm.sdesc);
		});
		// =============setup charm boxes================================
			
			//heart vbox
			heartvbox.setPrefSize(230, 300);
			heartvbox.setAlignment(Pos.CENTER);
			
			        
			heartvbox.getChildren().addAll(heart, heartImg, Hprice, Hstock);
			changeLabelStyles(heartvbox);
		
			//coffee vbox
			coffeevbox.setPrefSize(230, 300);

			coffeevbox.setAlignment(Pos.CENTER);
			coffeevbox.getChildren().addAll(coffee, coffeeImg, Cprice, Cstock);
			changeLabelStyles(coffeevbox);
			
			//whet vbox
			whetvbox.setPrefSize(230, 300);
		
			whetvbox.setAlignment(Pos.CENTER);
			whetvbox.getChildren().addAll(whet, whetImg, Wprice, Wstock);
			changeLabelStyles(whetvbox);
			
			//twinheart vbox
			twinvbox.setPrefSize(230, 300);
		
			twinvbox.setAlignment(Pos.CENTER);
			twinvbox.getChildren().addAll(twin, twinImg, Tprice, Tstock);
			changeLabelStyles(twinvbox);
			
			//smokebomb vbox
			smokevbox.setPrefSize(230, 300);
		
			smokevbox.setAlignment(Pos.CENTER);
			smokevbox.getChildren().addAll(smoke, smokeImg, SBprice, SBstock);
			changeLabelStyles(smokevbox);
		
			
		// ===============setup gp=======================
			
		gp.add(heartvbox, 0, 0);
		gp.add(coffeevbox, 1, 0);
		gp.add(whetvbox, 0, 1);
		gp.add(twinvbox, 1, 1);
		gp.add(smokevbox, 0, 2);
		gp.setHgap(10);
		gp.setVgap(20);
			
		//fpcharm.getChildren().addAll(heartvbox, coffeevbox, 
				//whetvbox, twinvbox, smokevbox);
			
		// =============setup scroll pane======================
		sp.setContent(gp);
		sp.setPrefWidth(485);
		sp.setMaxHeight(900);
		
		
		//============ button hbox==================
		hbox.getChildren().addAll(co, clear);
			//hbox.setStyle("-fx-background-color: #c4741f");
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(25);
		
		hbox.setPrefHeight(80);
		// ===========validation button===============
		co.setPrefSize(150, 80);
		clear.setPrefSize(150, 80);
		
		co.setOnAction(e -> {
			if (current.getCoins() > total) {
	            current.setCoins(current.getCoins() - total);
	            for (String item : cart) {
	                current.addToInventory(item);
	                
	            }
	            cart.clear();
	            resetTotal();
	            fpcart.getChildren().clear();
	            labeltotal.setText("Total : " + total);
	            labelcoin.setText("Coins: " + current.getCoins());
	        } else {
	            System.out.println("Not enough coins!");
	        }
		});
		
		clear.setOnAction(e -> {
			fpcart.getChildren().clear();
			cart.clear();
			total = 0;
			labeltotal.setText("Total : " + total);
			fpcart.getChildren().add(empty);
			e.consume();
			
		});
		
		//==============gpcharm===================
		int coins = 100;
		
		cshop = new Label("Charm Shop");
		labelcoin = new Label("Coin : " + coins);
		
		gpcharm.add(cshop, 0, 0);
		gpcharm.add(sp, 0, 1);
		gpcharm.add(labelcoin, 0, 2);
		
		GridPane.setHalignment(cshop, HPos.CENTER);
		GridPane.setHalignment(labelcoin, HPos.RIGHT);
		
		gpcharm.setVgap(25);
		
		GridPane.setValignment(cshop, VPos.BOTTOM);
		
		
		//==========cart content===================
		
		int hq = 0;
		//cq, wq, tq, sq;
		
		cheart = new Label("Heart");
		qheart = new Label("Quantity: 1");
		
		ccoffee = new Label("Coffee");
		qcoffee = new Label("Quantity: 1" );
		
		cwhet = new Label("Whetstone" );
		qwhet = new Label("Quantity: 1" );
		
		ctwin = new Label("Twin Heart");
		qtwin = new Label("Quantity: 1");
		
		csmoke = new Label("Smoke Bomb");
		qsmoke = new Label("Quantity: 1");
		
		hvbox.getChildren().addAll(cheart, qheart);
		cvbox.getChildren().addAll(ccoffee, qcoffee);
		wvbox.getChildren().addAll(cwhet, qwhet);
		tvbox.getChildren().addAll(ctwin, qtwin);
		svbox.getChildren().addAll(csmoke, qsmoke);
		
		hvbox.setStyle("-fx-background-color: #a95121");
		cvbox.setStyle("-fx-background-color: #a95121");
		wvbox.setStyle("-fx-background-color: #a95121");
		tvbox.setStyle("-fx-background-color: #a95121");
		svbox.setStyle("-fx-background-color: #a95121");
		
		hvbox.setAlignment(Pos.CENTER);
		cvbox.setAlignment(Pos.CENTER);
		wvbox.setAlignment(Pos.CENTER);
		tvbox.setAlignment(Pos.CENTER);
		svbox.setAlignment(Pos.CENTER);
		
		hvbox.setPrefSize(245, 145);
		cvbox.setPrefSize(245, 145);
		wvbox.setPrefSize(245, 145);
		tvbox.setPrefSize(245, 145);
		svbox.setPrefSize(245, 145);

		//============ONDRAGDETECTED (manually bcs im a noob)======================
		
		heartvbox.setOnDragDetected(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Dragboard db = heartvbox.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
	            content.putString(heart.getText());
	            db.setContent(content);
	            event.consume();
			}
		});
		
		coffeevbox.setOnDragDetected(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Dragboard db = coffeevbox.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
	            content.putString(coffee.getText());
	            db.setContent(content);
	            event.consume();
			}
		});
		
		whetvbox.setOnDragDetected(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Dragboard db = whetvbox.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
	            content.putString(whet.getText());
	            db.setContent(content);
	            event.consume();
			}
		});
		
		twinvbox.setOnDragDetected(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Dragboard db = twinvbox.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
	            content.putString(twin.getText());
	            db.setContent(content);
	            event.consume();
			}
		});
		
		smokevbox.setOnDragDetected(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Dragboard db = smokevbox.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
	            content.putString(smoke.getText());
	            db.setContent(content);
	            event.consume();
			}
		});
		
		//============fpcart===========================
		
		fpcart.setPrefSize(850, 900);
		//fpcart.setStyle("-fx-background-color: #c4741f");
		fpcart.setPadding(new Insets(20));
		fpcart.setHgap(20);
		fpcart.setVgap(20);
		
		
		
		fpcart.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != fpcart && event.getDragboard().hasString()) {
	                event.acceptTransferModes(TransferMode.MOVE);
	            }
	            event.consume();	
			}	
		});
        
		
		fpcart.setOnDragDropped(new EventHandler<DragEvent>() {


			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
	            boolean success = false;
	            if (db.getString().equals(heart.getText())) {
	                fpcart.getChildren().add(hvbox);
	                success = true;
	                cart.add(db.getString());
	                total += hPrice;
	                labeltotal.setText("Total : " + total);
	                hstocks--;
	                Hstock.setText("Stock : " + hstocks);
	            }
	            if (db.getString().equals(coffee.getText())) {
	                fpcart.getChildren().add(cvbox);
	                success = true;
	                cart.add(db.getString());
	                total += cPrice;
	                labeltotal.setText("Total : " + total);
	                cstocks--;
	                Cstock.setText("Stock : " + cstocks);
	            }
	            if (db.getString().equals(whet.getText())) {
	                fpcart.getChildren().add(wvbox);
	                success = true;
	                cart.add(db.getString());
	                total += wPrice;
	                labeltotal.setText("Total : " + total);
	                wstocks--;
	                Wstock.setText("Stock : " + wstocks);
	            }
	            if (db.getString().equals(twin.getText())) {
	                fpcart.getChildren().add(tvbox);
	                success = true;
	                cart.add(db.getString());
	                total += tPrice;
	                labeltotal.setText("Total : " + total);
	                tstocks--;
	                Tstock.setText("Stock : " + tstocks);
	            }
	            if (db.getString().equals(smoke.getText())) {
	                fpcart.getChildren().add(svbox);
	                success = true;
	                cart.add(db.getString());
	                total += sbPrice;
	                labeltotal.setText("Total : " + total);
	                sbstocks--;
	                SBstock.setText("Stock : " + sbstocks);
	            }
	            fpcart.getChildren().remove(empty);
	            event.setDropCompleted(success);
	            event.consume();
				//System.out.println(db.getString());
				
			}
		});;
		
                if (fpcart.getChildren().isEmpty()) {
                	fpcart.getChildren().add(empty);
                } else {
                	fpcart.getChildren().remove(empty);
                }
            
		//=============gpcart======================
		
		gpcart.add(labelcart, 0, 0);
		gpcart.add(fpcart, 0, 1);
		gpcart.add(labeltotal, 0, 2);
		gpcart.add(hbox, 0, 3);
		
		GridPane.setHalignment(labelcart, HPos.CENTER);
		GridPane.setHalignment(hbox, HPos.CENTER);
		gpcart.setVgap(25);
		
		
		
		// ===============menu=============================
			Menu menu = new Menu("Menu");
			MenuItem inventory = new MenuItem("Inventory");
			MenuItem logout = new MenuItem("Logout");
			menu.getItems().addAll(inventory, logout);
			MenuBar menubar = new MenuBar(menu);
			
			inventory.setOnAction(e -> {
				app.showInventory();
			});
			
			logout.setOnAction(e -> {
				app.showLoginPage();
			});
			
			
		// =================style==========================
			co.setStyle("-fx-background-color: #a95121");
			co.setTextFill(Color.ANTIQUEWHITE);
			
			clear.setStyle("-fx-background-color: #a95121");
			clear.setTextFill(Color.ANTIQUEWHITE);
			
			heartvbox.setStyle("-fx-background-color: #a95121");
			coffeevbox.setStyle("-fx-background-color: #a95121");
			whetvbox.setStyle("-fx-background-color: #a95121");
			twinvbox.setStyle("-fx-background-color: #a95121");
			smokevbox.setStyle("-fx-background-color: #a95121");
			bp.setStyle("-fx-background-color: #7b4207");
			gp.setStyle("-fx-background-color: #7b4207");
			sp.setStyle("-fx-background-color: #7b4207");
			
			
			cshop.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 32; -fx-font-weight: bold;");
			labelcart.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 32; -fx-font-weight: bold;");
			empty.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 28; -fx-font-weight: bold;");
			
			
			labelcoin.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 22; -fx-font-weight: bold;");
			labeltotal.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 22; -fx-font-weight: bold;");
			
			cshop.setTextFill(Color.ANTIQUEWHITE);
			labelcart.setTextFill(Color.ANTIQUEWHITE);
			labelcoin.setTextFill(Color.ANTIQUEWHITE);
			labeltotal.setTextFill(Color.ANTIQUEWHITE);
			empty.setTextFill(Color.ANTIQUEWHITE);
			
			changeLabelStyles(hvbox);
			changeLabelStyles(cvbox);
			changeLabelStyles(wvbox);
			changeLabelStyles(tvbox);
			changeLabelStyles(svbox);
			
			co.setStyle("-fx-background-color: #a95121; -fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 22; -fx-font-weight: bold;");
			clear.setStyle("-fx-background-color: #a95121; -fx-text-fill: white;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 22; -fx-font-weight: bold;");
			
//			Button tes = new Button("ViewCharm");
//			tes.setOnAction(e -> {
//				charm.popupCharm("tes", "Assets/image/heart.png", "tes");
//			});
			
		// ================= layout	=====================
			
			bp.setTop(menubar);
			bp.setRight(gpcharm);
			bp.setLeft(gpcart);
			
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
	
	private void resetTotal() {
		total = 0;
	}

	public ImageView charmImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	//tes run
	
	public void StartPage(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public BorderPane getBorderPane() {
		return bp;
	}

	
}
