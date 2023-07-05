package lab.unipi.gui;
import lab.unipi.core.*;
import java.util.ArrayList;

import javafx.scene.Scene;

public abstract class SceneCreator {
    double width;
    double height;

    public SceneCreator(double width, double height) {
        this.width = width;
        this.height = height;
    }

    abstract Scene createScene();
    static ArrayList<City> cityList=new ArrayList<>();
    static ArrayList<Vehicle> vehicleList= new ArrayList<>();
    static ArrayList<Store> storeList= new ArrayList<>();
    static ArrayList<Client> clientList= new ArrayList<>();
    static ArrayList<Rental> rentalList= new ArrayList<>();

}
