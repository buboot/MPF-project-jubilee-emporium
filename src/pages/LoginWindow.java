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
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Main;


public class LoginWindow {
	
	private Main app;
	
	Stage primaryStage;
	Scene scene;
	StackPane stack;
	GridPane gp;
	FlowPane fp;
	
	ImageView logo;
	
	Label email, pass, click;
	TextField tfem;
	PasswordField pfpass;
	Button login;
	
	Media vid, bgm;
	MediaPlayer mp4, mp3;
	MediaView mv;
	
	
	public LoginWindow(Main app) {
		this.app = app;
		stack = new StackPane();
		fp = new FlowPane(Orientation.VERTICAL);
		gp = new GridPane();
		
		logo = RegisWindow.loadImage("Assets/image/logo.png");
		
		email = new Label("Email");
		pass = new Label("Password");
		click = new Label("Click here to register");
		
		tfem = new TextField();
		pfpass = new PasswordField();
		login = new Button("Login");
		
		bgm = new Media(new File("Assets/audio/bgm.mp3").toURI().toString());
		vid = new Media(new File("Assets/video/video.mp4").toURI().toString());
		mp4 = new MediaPlayer(vid);
		mp3 = new MediaPlayer(bgm);
		mv = new MediaView(mp4);
		
		//layout
		fp.getChildren().addAll(logo, gp);
		
		gp.add(email, 0, 1);
		gp.add(tfem, 0, 2);
		gp.add(pass, 0, 3);
		gp.add(pfpass, 0, 4);
		gp.add(click, 0, 5);
		gp.add(login, 0, 6);
		
		gp.setMaxWidth(400);
		
		
		fp.setVgap(20);
		fp.setMaxSize(40, 600);
		FlowPane.setMargin(gp, new Insets(0, 0, 0, 120));
		gp.setHgap(50);
		
		//stackpane
		mv.setFitHeight(1080);
		mv.setFitWidth(1920);
		stack.getChildren().addAll(mv, fp);
		mp4.setAutoPlay(true);
		//mp3.setAutoPlay(true);
		mp4.setCycleCount(MediaPlayer.INDEFINITE);
		//mp3.setCycleCount(MediaPlayer.INDEFINITE);
		//style
		click.setUnderline(true);
		login.setPrefSize(645, 5);
		
		//debug
//		fp.setStyle("-fx-background-color: #7b4207");
//		gp.setStyle("-fx-background-color: #7b2807");
		
		//setup
		//bp.setCenter(fp);
		
		//button validation
		click.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				app.showRegisterPage();
			}
		});
		
		login.setOnAction(e -> {
			Alert a = new Alert(AlertType.ERROR);
			//a.setTitle("Input error");
			
			String em = tfem.getText();
			String pas = pfpass.getText();
			
			boolean tru = app.loginUser(em, pas);
			if(em.equals("admin@gmail.com") && pas.equals("admin")) {
				app.showAdminWindow();
			}
			else if(em.isEmpty()) {
				a.setTitle("Empty Field Error");
				a.setContentText("Email must be filled in.");
				a.showAndWait();
				return;
			}
			else if(pas.isEmpty()) {
				a.setTitle("Empty Field Error");
				a.setContentText("Password must be filled in.");
				a.showAndWait();
				return;
			}
			else if(tru) {
				app.showShop();
			}
			else if(!tru){
				a.setTitle("Error");
				a.setContentText("Your email or password is wrong.");
				a.showAndWait();
			}
			
		});
		
		//style
		Font font = new Font(new File("src/Assets/font/AlegreyaSansSC-Bold.ttf").toURI().toString(), 15);
		
		changeLabelStyles(gp);
		
		login.setStyle("-fx-background-color: #bf6600;-fx-font-family: 'Trebuchet MS'; -fx-font-size: 16; -fx-font-weight: bold;-fx-text-fill: white");
		
		
		click.setUnderline(true);
		fp.setAlignment(Pos.CENTER);
		gp.setVgap(5);
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

	public void StartPage(Stage primaryStage) {
		this.primaryStage = primaryStage;                                 
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public StackPane getStackPane() {
		// TODO Auto-generated method stub
		return stack;
	}

	public Parent getScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
