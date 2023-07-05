package lab.unipi.gui;
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.stage.Stage;

public class MainFX extends Application {
	    //=============Stage=============
	    static Stage mainStage;
	    //=============������=============
	    static Scene cityScene, mainScene,clientScene,vehicleScene,rentalScene,storeScene;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	   public void start(Stage primaryStage) {

	       mainStage = primaryStage;
	       
	       //=============���������� Main ������=============
	       SceneCreator mainSceneCreator = new MainSceneCreator(650, 450);
	       mainScene = mainSceneCreator.createScene();
	       
	       //=============���������� City ������=============
	       SceneCreator citySceneCreator = new CitySceneCreator(1000, 450);
           cityScene = citySceneCreator.createScene();
           
	       //=============���������� Store ������=============
           SceneCreator storeSceneCreator=new StoreSceneCreator(800,450);
           storeScene= storeSceneCreator.createScene();
           
	       //=============���������� Client ������=============
           SceneCreator clientSceneCreator=new ClientSceneCreator(1000,450);
           clientScene= clientSceneCreator.createScene();
           
	       //=============���������� Vehicle ������============= 
           SceneCreator vehicleSceneCreator=new VehicleSceneCreator(1000,500);
           vehicleScene= vehicleSceneCreator.createScene();
           
	       //=============���������� Vehicle Rental ������=============   
           SceneCreator rentalSceneCreator=new RentalSceneCreator(1100,600);
           rentalScene= rentalSceneCreator.createScene();
           
	       primaryStage.setTitle("Car Rental Service");
	       primaryStage.setScene(mainScene);
	       primaryStage.show();
	  }
}

