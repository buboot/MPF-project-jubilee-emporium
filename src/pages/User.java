package pages;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String userName;
	private String userEmail;
	private String userPass;
	private List<String> inventory;
	private int coins;
	
	public User(String userName, String userEmail, String userPass) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.inventory = new ArrayList<>();
		this.coins = 100;
	}
	
	public User(String email, String password, List<String> inventory, int money) {
        this.userEmail = email;
        this.userPass = password;
        this.inventory = inventory;
        this.coins = money;
    }

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserPass() {
		return userPass;
	}
	
	public List<String> getInventory() {
        return inventory;
    }

    public int getCoins() {
        return coins;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
}

