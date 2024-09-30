package pages;

import java.io.File;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Main;

public class RegisWindow {
	
	private Main app;
	Stage primaryStage;
	
	Scene scene;
	BorderPane bp;
	FlowPane fp;
	GridPane gp;
	VBox vbox;
	
	Label name, email, pass, confpass, loginhere, blank;
	
	TextField tfname, tfemail;
	PasswordField pfpass, pfconfpass;
	
	Button button;
	
	ImageView logo;
	Image bg;
	
	Background backg;
	BackgroundImage bgimg;
	
	public RegisWindow(Main app) {
		this.app = app;
		
		bp = new BorderPane();
		gp = new GridPane();
		fp = new FlowPane(Orientation.VERTICAL);
		vbox = new VBox();
		
		name = new Label("Name");
		pass = new Label("Password");
		confpass = new Label("Confirm Password");
		email = new Label("Email");
		blank = new Label(" ");
		loginhere = new Label("Login here");
		
		tfname = new TextField();
		tfemail = new TextField();
		pfpass = new PasswordField();
		pfconfpass = new PasswordField();
		
		button = new Button("Register Account");
		
		logo = loadImage("Assets/image/logo.png");
		bg = bgImage("Assets/image/register bg.jpg");
		
		bgimg = new BackgroundImage(bg,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		
		backg = new Background(bgimg); 
		
		
		//setup
		
		//gp.getChildren().add(logo);
		
		fp.getChildren().addAll(logo, gp);
		//fp.setPrefSize(500, 500);
		
		gp.add(name, 0, 3);
		gp.add(tfname, 0, 4);
		gp.add(email, 0, 5);
		gp.add(tfemail, 0, 6);
		gp.add(pass, 0, 7);
		gp.add(pfpass, 0, 8);
		gp.add(confpass, 0, 9);
		gp.add(pfconfpass, 0, 10);
		gp.add(loginhere, 0, 11);
		gp.add(button, 0, 12);
		
		gp.setMaxWidth(400);
		
		//fp.setPrefSize(50, 50);s
		fp.setMaxSize(50, 600);
		FlowPane.setMargin(gp, new Insets(0, 0, 0, 120));
		gp.setHgap(50);
		
		
		
		
		//vbox.getChildren().addAll(name, tfname, email, tfemail, 
				//pass, pfpass, confpass, pfconfpass, loginhere, button);
		
		//validation
		
		loginhere.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				app.showLoginPage();
			}
		});
		
		button.setOnAction(e -> {
			Alert a = new Alert(AlertType.ERROR);
			Alert yey = new Alert(AlertType.INFORMATION);
			//a.setTitle("Input error");

			String uname = tfname.getText();
			String mail = tfemail.getText();
			int atIndex = mail.indexOf('@');
			String pw = pfpass.getText();	
			String cpw = pfconfpass.getText();	
			
			if(uname.isEmpty()) {
				a.setTitle("Input error");
				a.setContentText("Name must be filled in.");
				a.showAndWait();
				return;
			}
			else if(uname.length() < 3 || uname.length() > 12) {
				a.setTitle("Name error");
				a.setContentText("Name must be 3-12 characters long.");
				a.showAndWait();
				return;
			}
			else if(mail.isEmpty()) {
				a.setTitle("Email error");
				a.setContentText("Email must be filled in.");
				a.showAndWait();
				return;
			}
			else if(!mail.endsWith("@gmail.com")) {
				a.setTitle("Email error");
				a.setContentText("Email must end with '@gmail.com'.");
				a.showAndWait();
				return;
			}
			else if(mail.contains(" ")) {
				a.setTitle("Email error");
				a.setContentText("Email must not contain space.");
				a.showAndWait();
				return;
			}
			else if(atIndex != mail.lastIndexOf('@')) {
				a.setTitle("Email error");
				a.setContentText("Email must only contain 1 '@'.");
				a.showAndWait();
				return;
			}
			else if(pw.isEmpty()) {
				a.setTitle("Password error");
				a.setContentText("Password must be filled in.");
				a.showAndWait();
				return;
			}
			else if(pw.length()<8) {
				a.setContentText("Password length cant be less than 8");
				a.showAndWait();
				return;
			}
			else if(!pw.matches("^[a-zA-Z0-9]+$")) {
				a.setContentText("Password must be alphanumerical");
				a.showAndWait();
				return;
			} 
			else if(cpw.isEmpty()) {
				a.setTitle("Password error");
				a.setContentText("Please confirm your password.");
				a.showAndWait();
				return;
			}
			else if(!cpw.equals(pw)) {
				a.setTitle("Password error");
				a.setContentText("Password and confirm password must be the same.");
				a.showAndWait();
				return;
			}else {
				app.registerUser(uname, mail, cpw);
				yey.setTitle("WOOHOO!");
				yey.setContentText("Account made! Welcome "+ uname + ", You can log in now");
				yey.showAndWait();
			}
			
			
					
		});
		
		
		//style
//		Font font = new Font("Impact", 18);
		button.setPrefSize(645, 5);
		button.setStyle("-fx-background-color: #bf6600;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 16; -fx-font-weight: bold;-fx-text-fill: white");
		button.setTextFill(Color.WHITESMOKE);
		
		loginhere.setUnderline(true);
		
		changeLabelStyles(gp);
		//debug
		
//		gp.setStyle("-fx-background-color: #7b4207");
//		fp.setStyle("-fx-background-color: #7b2807");
//		
//		System.out.println(gp.getPrefHeight());
//		System.out.println(gp.getPrefWidth());
//		System.out.println(fp.getPrefWidth());
//		System.out.println(fp.getPrefHeight());
		
		//layout
		
		//bp.setTop(logo);
		bp.setCenter(fp);
		bp.setBackground(backg);
		
		
		fp.setAlignment(Pos.CENTER);
		gp.setVgap(5);
	}
	

	static Image bgImage(String url) {
		File file = new File(url);
		return new Image(file.toURI().toString());
	}
	
	static ImageView loadImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	public void StartPage(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public BorderPane getBorderPane() {
		return bp;
	}
	
	public void changeLabelStyles(GridPane gridpane) {
        for (var node : gridpane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setTextFill(Color.WHITESMOKE); 	
                label.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 16; -fx-font-weight: bold;");  
            }
        }
	}


}
