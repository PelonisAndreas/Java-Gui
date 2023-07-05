package lab.unipi.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
	//=============FlowPane=============
    FlowPane rootFlowPane;
    //=============Buttons=============
    Button cityBtn, vehicleBtn, vehicleRentalBtn,storeBtn,clientBtn;

    public MainSceneCreator(double width, double height) {
        super(width, height);

        rootFlowPane = new FlowPane();
        cityBtn = new Button("Cities");
        vehicleBtn = new Button("Vehicles");
        storeBtn = new Button("Store");
        clientBtn = new Button("Client");
        vehicleRentalBtn = new Button("Vehicle Rental");

		//=============Events=============
        cityBtn.setOnMouseClicked(this);
        storeBtn.setOnMouseClicked(this);
        vehicleBtn.setOnMouseClicked(this);
        clientBtn.setOnMouseClicked(this);
        vehicleRentalBtn.setOnMouseClicked(this);

        //=============Προσαρμογη των rootFlowPane=============
        rootFlowPane.setHgap(10);
        rootFlowPane.setAlignment(Pos.CENTER);
        
        rootFlowPane.getChildren().add(cityBtn);
        rootFlowPane.getChildren().add(vehicleBtn);
        rootFlowPane.getChildren().add(storeBtn);
        rootFlowPane.getChildren().add(clientBtn);
        rootFlowPane.getChildren().add(vehicleRentalBtn);
    }

    @Override
    public Scene createScene() {
        return new Scene(rootFlowPane, width, height);
    }

	@Override
	public void handle(MouseEvent event) {
		//=============Πηγαινει στην City=============
        if (event.getSource() == cityBtn) {
            MainFX.mainStage.setTitle("City Window");
            MainFX.mainStage.setScene(MainFX.cityScene);
        }
		//=============Πηγαινει στην Store=============
        if (event.getSource() == storeBtn) {
            MainFX.mainStage.setTitle("Store Window");
            MainFX.mainStage.setScene(MainFX.storeScene);
        }
		//=============Πηγαινει στην Client=============
        if (event.getSource() == clientBtn) {
            MainFX.mainStage.setTitle("Client Window");
            MainFX.mainStage.setScene(MainFX.clientScene);
        }
		//=============Πηγαινει στην Vehicle=============
        if (event.getSource() == vehicleBtn) {
            MainFX.mainStage.setTitle("Vehicle Window");
            MainFX.mainStage.setScene(MainFX.vehicleScene);
        }
		//=============Πηγαινει στην Vehicle Rental=============
        if (event.getSource() == vehicleRentalBtn) {
            MainFX.mainStage.setTitle("Rental Window");
            MainFX.mainStage.setScene(MainFX.rentalScene);
        }
        
    }
	
    
}