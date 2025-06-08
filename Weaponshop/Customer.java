import java.util.ArrayList;
import java.util.Random;

public class Customer {
    public String id;
    public int strength;
    public int speed;
    public int stamina;
    public int reach;
    public ArrayList<Weapon> inventory;

    public Customer(String id) {
        this.id = id;
        this.strength = new Random().nextInt(20) + 10;
        this.speed = new Random().nextInt(20) + 10;
        this.stamina = new Random().nextInt(20) + 10;
        this.reach = new Random().nextInt(5) + 3;
        this.inventory = new ArrayList<>();
    }
}