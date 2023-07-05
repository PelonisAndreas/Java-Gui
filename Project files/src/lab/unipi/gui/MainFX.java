package lab.unipi.gui;
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.stage.Stage;

public class MainFX extends Application {
	    //=============Stage=============
	    static Stage mainStage;
	    //=============Σκηνες=============
	    static Scene cityScene, mainScene,clientScene,vehicleScene,rentalScene,storeScene;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	   public void start(Stage primaryStage) {

	       mainStage = primaryStage;
	       
	       //=============Δημιουργια Main Σκηνης=============
	       SceneCreator mainSceneCreator = new MainSceneCreator(650, 450);
	       mainScene = mainSceneCreator.createScene();
	       
	       //=============Δημιουργια City Σκηνης=============
	       SceneCreator citySceneCreator = new CitySceneCreator(1000, 450);
           cityScene = citySceneCreator.createScene();
           
	       //=============Δημιουργια Store Σκηνης=============
           SceneCreator storeSceneCreator=new StoreSceneCreator(800,450);
           storeScene= storeSceneCreator.createScene();
           
	       //=============Δημιουργια Client Σκηνης=============
           SceneCreator clientSceneCreator=new ClientSceneCreator(1000,450);
           clientScene= clientSceneCreator.createScene();
           
	       //=============Δημιουργια Vehicle Σκηνης============= 
           SceneCreator vehicleSceneCreator=new VehicleSceneCreator(1000,500);
           vehicleScene= vehicleSceneCreator.createScene();
           
	       //=============Δημιουργια Vehicle Rental Σκηνης=============   
           SceneCreator rentalSceneCreator=new RentalSceneCreator(1100,600);
           rentalScene= rentalSceneCreator.createScene();
           
	       primaryStage.setTitle("Car Rental Service");
	       primaryStage.setScene(mainScene);
	       primaryStage.show();
	  }
}

