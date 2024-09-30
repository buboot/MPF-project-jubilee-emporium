package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pages.AdminWindow;
import pages.InventoryWindow;
import pages.LoginWindow;
import pages.RegisWindow;
import pages.ShopWindow;
import pages.User;

public class Main extends Application{
	
	static Media bgm = new Media(new File("Assets/audio/bgm.mp3").toURI().toString()); ;
	static MediaPlayer mp3 = new MediaPlayer(bgm);;
	
	private Stage primaryStage;
	private List<User> users = new ArrayList<>();
	private User current;
	
	private void addAdmin() {
		users.add(new User("Admin", "admin@gmail.com", "admin"));
	}
	
	public static void main(String[] args) {
		mp3.setAutoPlay(true);
		mp3.setCycleCount(MediaPlayer.INDEFINITE);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		addAdmin();
		
		showLoginPage();
	}
	
	public void showLoginPage() {
        LoginWindow login= new LoginWindow(this);
        Scene scene = new Scene(login.getStackPane(), 1440, 800);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showRegisterPage() {
        RegisWindow registerPage = new RegisWindow(this);
        Scene scene = new Scene(registerPage.getBorderPane(), 1440, 800);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showShop() {
        ShopWindow shop = new ShopWindow(this, current);
        Scene scene = new Scene(shop.getBorderPane(), 1440, 800);
        primaryStage.setTitle("Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
    public void showInventory() {
        InventoryWindow shop = new InventoryWindow(this, current);
        Scene scene = new Scene(shop.getBorderPane(), 1440, 800);
        primaryStage.setTitle("Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showAdminWindow() {
        AdminWindow adm = new AdminWindow(this, current);
        Scene scene = new Scene(adm.getBorderPane(), 1440, 800);
        primaryStage.setTitle("Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
    public void registerUser(String uname, String email, String password) {
        users.add(new User(uname, email, password));
    }
    
    public boolean loginUser(String email, String password) {
        for (User user : users) {
            if (user.getUserEmail().equals(email) && user.getUserPass().equals(password)) {
                current = user;
                return true;
            }
        }
        return false;
    }
    
}
