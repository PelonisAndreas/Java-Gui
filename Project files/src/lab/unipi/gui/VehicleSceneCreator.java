package lab.unipi.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import lab.unipi.core.Vehicle;
import lab.unipi.core.Car;
import lab.unipi.core.Store;
import lab.unipi.core.TwoWheeled;


public class VehicleSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
	
	 //=============FlowPane=============
	 FlowPane buttonFlowPane ;
	
	 //=============GridPanes=============
	 GridPane rootGridPane, inputFieldsPane , inputFieldsPane2 ;	
	
	 //=============TilePane=============
	 TilePane r ;
	
	 //=============Buttons=============
	 Button newVehicleBtn, updateVehicleBtn, deleteVehicleBtn, backBtn , viewVehicleBtn , searchVehicleBtn ;
	
	 //=============Labels=============
	 Label lncodeLbl , modelLbl , fuelLbl , typeLbl , cubeLbl  , rent_priceLbl , seat_numberLbl , door_numberLbl , seat_heightLbl , luggage_placeLbl , searchLbl;
	
	 //=============TextFields=============
	 TextField lncodeField , modelField , fuelField , typeField , cubeField , rent_priceField ,  seat_numberField , door_numberField , luggage_placeField , seat_heightField ,searchvehicleField ; 
	
	 //=============TableView=============
	 TableView<Vehicle> vehicleTableView;
	 
	 RadioButton radioBtn1 , radioBtn2;
	 
	 
	
	 private static String radio_type;
	 private static String ctype;
	 private String typeff;
	 
	 
	public VehicleSceneCreator(double width, double height) {
		super(width, height);
		vehicleList = new ArrayList<>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();   
		inputFieldsPane = new GridPane();
		inputFieldsPane2 = new GridPane();
		vehicleTableView = new TableView<>();
		
		//----- Δημιουργια TilePane για την τοποθετηση των RadioButtons στο GridPane
		r = new TilePane();	
		
        //=============Labels=============
		searchLbl = new Label("Search");
        lncodeLbl = new Label("License code: ");
        modelLbl = new Label("Model: ");
        fuelLbl = new Label("Fuel Type: ");
        cubeLbl = new Label("Cubic capacity: ");
        typeLbl = new Label("Size/Type: ");    
        rent_priceLbl = new Label("Rent Price: ");
        seat_numberLbl = new Label("(Car) Seat Number: ");
        door_numberLbl = new Label("(Car) Door Number: ");
        seat_heightLbl = new Label("(TwoWheeled) Seat Height: ");  
        
        //=============TextFields=============
        typeField = new TextField();
        luggage_placeLbl = new Label("(TwoWheeled) Luggage Type: ");   
		lncodeField = new TextField();
		modelField = new TextField();
		fuelField = new TextField();
		cubeField = new TextField();
		rent_priceField = new TextField();		
		seat_numberField = new TextField();
		door_numberField = new TextField();
		luggage_placeField = new TextField();
		seat_heightField = new TextField();
		searchvehicleField = new TextField();
		
        //=============Buttons=============
		newVehicleBtn = new Button("New Vehicle");
		updateVehicleBtn = new Button("Update");
		deleteVehicleBtn = new Button("Delete");
		backBtn = new Button("Go Back");
		viewVehicleBtn = new Button("View Vehicles");
		searchVehicleBtn = new Button("Search");
		
		//=============Radio Buttons=============
		radioBtn1 = new RadioButton("Car");
		radioBtn2 = new RadioButton("TwoWheeled");
		
		
		
		
		final ToggleGroup group = new ToggleGroup();
		radioBtn1.setToggleGroup(group);
		radioBtn2.setToggleGroup(group);
		
		//=============Events=============
        backBtn.setOnMouseClicked(this);
        newVehicleBtn.setOnMouseClicked(this);
        updateVehicleBtn.setOnMouseClicked(this);
        deleteVehicleBtn.setOnMouseClicked(this);
        vehicleTableView.setOnMouseClicked(this);
		viewVehicleBtn.setOnMouseClicked(this);
		searchVehicleBtn.setOnMouseClicked(this);
		
        //=============Προσαρμογη των buttonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newVehicleBtn);
        buttonFlowPane.getChildren().add(updateVehicleBtn);
        buttonFlowPane.getChildren().add(deleteVehicleBtn);
        buttonFlowPane.getChildren().add(viewVehicleBtn);
        buttonFlowPane.setAlignment(Pos.TOP_CENTER);
        
        
        
        //----- Τοποθετηση των raadio buttons στο TilePane
        r.getChildren().add(radioBtn1);  
        r.getChildren().add(radioBtn2);

        
        //----- Εισοδος τιμων στα radioButtons 
        radioBtn1.setUserData("Car");
        radioBtn2.setUserData("TwoWheeled");
              
        //----- "Διαβαζει" το καθε radio Button δινοντας αντιστοιχη τιμη στην radio_type
        group.getSelectedToggle();
       
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                

                radio_type = chk.getText(); // η μεταβλητη παιρνει την τιμη του radio button που ειναι επιλεγμενο
            }
        });
       
        
        //----- Customize ComboBox
  
        
        final ComboBox<String> comboBox = new ComboBox<String>();
        
        
        comboBox.getItems().add(
                "Car" 
            );
        
        comboBox.getItems().add(
        		"TwoWheeled"          
            );
       
          
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
               
            	ctype = t1;    //----- το ctype παιρνει τιμη αναλογα ποιο comboBox ειναι επιλεγμενο
                  
                
            	//----- ελεγχος της τιμης ctype και διαμορφωση του tableView αναλογα αν ειναι Car ή TwoWheeled           
            	if (ctype.equals("Car")) {
                	
                    //=============Προσαρμογη των VehicleTableView(Car)=============
	            	vehicleTableView.getColumns().clear();

	                TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
	                lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
	                vehicleTableView.getColumns().add(lncodeColumn);

	                TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
	                modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
	                vehicleTableView.getColumns().add(modelColumn);

	                
	                TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
	                fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
	                vehicleTableView.getColumns().add(fuelColumn);

	                TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
	                typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	                vehicleTableView.getColumns().add(typeColumn);

	                TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
	                cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
	                vehicleTableView.getColumns().add(cubeColumn);
	         
	                TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
	                rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
	                vehicleTableView.getColumns().add(rent_priceColumn);

	                
	                TableColumn<Vehicle, Double> seat_numberColumn = new TableColumn<>("Numb. of Seats");
	                seat_numberColumn.setCellValueFactory(new PropertyValueFactory<>("seat_number"));
	                vehicleTableView.getColumns().add(seat_numberColumn);
	                
	                TableColumn<Vehicle, Integer> door_numberColumn = new TableColumn<>("Numb. of doors");
	                door_numberColumn.setCellValueFactory(new PropertyValueFactory<>("door_number"));
	                vehicleTableView.getColumns().add(door_numberColumn);
	                
                    List<Vehicle> items = vehicleTableView.getItems();

                    vehicleTableView.getItems().clear();
	
                    
                    
                    //----- 
                		for(Vehicle d : vehicleList) {
                			if((d.getType()).equals("Big Car") || (d.getType()).equals("Medium Car") || (d.getType()).equals("Small Car")) {
                				
                				items.add(d);
                			}
                		}               			                	
                	}
                
                else if (ctype.equals("TwoWheeled")) {
                	
                    //=============Προσαρμογη των VehicleTableView(TwoWheeled)=============
					vehicleTableView.getColumns().clear();

			        TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
			        lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
			        vehicleTableView.getColumns().add(lncodeColumn);

			        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
			        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
			        vehicleTableView.getColumns().add(modelColumn);

			        
			        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
			        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
			        vehicleTableView.getColumns().add(fuelColumn);

			        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
			        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
			        vehicleTableView.getColumns().add(typeColumn);

			        TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
			        cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
			        vehicleTableView.getColumns().add(cubeColumn);
			 
			        TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
			        rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
			        vehicleTableView.getColumns().add(rent_priceColumn);

			        
			        TableColumn<Vehicle, Integer> seat_heightColumn = new TableColumn<>("Seat's Height");
			        seat_heightColumn.setCellValueFactory(new PropertyValueFactory<>("seat_height"));
			        vehicleTableView.getColumns().add(seat_heightColumn);

			        TableColumn<Vehicle, String> luggage_placeColumn = new TableColumn<>("Luggage Type");
			        luggage_placeColumn.setCellValueFactory(new PropertyValueFactory<>("luggage_place"));
			        vehicleTableView.getColumns().add(luggage_placeColumn);
			        
                	List<Vehicle> items = vehicleTableView.getItems();              	
                	
                	vehicleTableView.getItems().clear();
                	
                	for(Vehicle d : vehicleList) {
            			if((d.getType()).equals("TwoWheeled Motorcycle") || (d.getType()).equals("TwoWheeled Skutter")) {
            				items.add(d);
            				}
                	}
          
                } 
          }
           });
          
     
        
        
        
        
		
        //=============Προσαρμογη των inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(lncodeLbl, 0, 0);
        inputFieldsPane.add(lncodeField, 1, 0);
        inputFieldsPane.add(modelLbl, 0, 1);
        inputFieldsPane.add(modelField, 1, 1);
        inputFieldsPane.add(fuelLbl, 0, 2);
        inputFieldsPane.add(fuelField, 1, 2);
        inputFieldsPane.add(cubeLbl, 0, 3);
        inputFieldsPane.add(cubeField, 1, 3);
        inputFieldsPane.add(rent_priceLbl, 0, 4);
        inputFieldsPane.add(rent_priceField, 1, 4);
        inputFieldsPane.add(typeLbl, 0, 5);
        inputFieldsPane.add(typeField, 1, 5);

        inputFieldsPane.add(seat_numberLbl, 0, 6);
        inputFieldsPane.add(seat_numberField, 1, 6);
        inputFieldsPane.add(door_numberLbl, 0, 7);
        inputFieldsPane.add(door_numberField, 1, 7);
        inputFieldsPane.add(seat_heightLbl, 0, 8);
        inputFieldsPane.add(seat_heightField, 1, 8);
        inputFieldsPane.add(luggage_placeLbl, 0, 9);
        inputFieldsPane.add(luggage_placeField, 1, 9);
        
        inputFieldsPane2.setVgap(10);
        inputFieldsPane2.setHgap(10);
        inputFieldsPane2.setAlignment(Pos.TOP_LEFT);
        inputFieldsPane2.add(searchLbl, 0, 0);
        
   

        //=============Προσαρμογη των rootGridPane=============
     
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
       
        rootGridPane.add(vehicleTableView, 0,0);
        rootGridPane.add(buttonFlowPane, 0, 3);
        rootGridPane.add(backBtn, 1, 3);
        rootGridPane.add(r, 0, 2);
        rootGridPane.add(comboBox, 1, 2);
        rootGridPane.add(inputFieldsPane2, 1, 1);
        
       
        

	}




	@Override
	public void handle(MouseEvent event) {
		
		if (event.getSource() == newVehicleBtn) {
           
			String lncode = lncodeField.getText();
			String model = modelField.getText();
			String fuel = fuelField.getText();
			final String type1;
			final String type2;
			typeff = typeField.getText(); 		//+++++ μεταβλητη για το typeField ( εχει δηλωθει στην γραμμη 58)
			
			//----- αρχικοποιηση μεταβλητων με αρχικη τιμη ιση με το 0 για την πραγματοποιηση ελεγχου αργοτερα ( αν παραμεινουν 0 τοτε δεν θα μπορει να μπει στους αντιστοιχους ελεγχους if )
			int cube = 0;
			double rent_price = 0;
			int seat_number = 0;
			int door_number = 0;
			double seat_height = 0;
			
			
		
			
			//----- Προσπαθεια για το περασμα τιμης στις cube kai rent_price με Exception Handling
			try {
				cube = Integer.parseInt(cubeField.getText());
				rent_price = Double.parseDouble(rent_priceField.getText());
				

			}catch(NumberFormatException e) {
				vehicleTableView.getItems().clear();
				vehicleTableView.setPlaceholder(new Label("\t\tInsert the correct type of values on the Text Fields and \nonly enter Big/Medium/Small for a Car and Motorcycle/Skutter for a TwoWheeled!"));
			}
			
			//----- Εισοδος αν δεν υπαρξει Exception απο πανω &  στο συγκεκριμενο if περιεχεται και το Car και το TwoWheeled εφοσον οι μεταβλητες cube kai rent_price μοιραζονται και απτα 2
		if(cube != 0 && rent_price != 0 )
		{
			

			try {
					seat_number = Integer.parseInt(seat_numberField.getText());
					door_number = Integer.parseInt(door_numberField.getText());
				} catch(NumberFormatException ee) {
					vehicleTableView.getItems().clear();
					vehicleTableView.setPlaceholder(new Label("\t\tInsert the correct type of values on the Text Fields and \nonly enter Big/Medium/Small for a Car and Motorcycle/Skutter for a TwoWheeled!"));
					}
		
			if(seat_number != 0 && door_number != 0) {
				
				
				//----- η μεταβλητη type1 παιρνει μιαα καινουργια τιμη αναλογα με το typeField ( ειναι Big Car ή Small Car ή Μedium Car)
				type1 = typeField.getText() +" " +radioBtn1.getText();
				
				
				
	
				if(type1.equals("Big Car") || type1.equals("Medium Car") || type1.equals("Small Car")) {
				
					if ( radio_type.equals("Car"))
					{

						
						vehicleTableView.getColumns().clear();
		
				        TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
				        lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
				        vehicleTableView.getColumns().add(lncodeColumn);
		
				        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
				        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
				        vehicleTableView.getColumns().add(modelColumn);
		
				        
				        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
				        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
				        vehicleTableView.getColumns().add(fuelColumn);
		
				        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
				        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
				        vehicleTableView.getColumns().add(typeColumn);
		
				        
				        TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
					    cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
					     vehicleTableView.getColumns().add(cubeColumn);
				        
				 
				        TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
				        rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
				        vehicleTableView.getColumns().add(rent_priceColumn);
		
				        
				        TableColumn<Vehicle, Double> seat_numberColumn = new TableColumn<>("Numb. of Seats");
				        seat_numberColumn.setCellValueFactory(new PropertyValueFactory<>("seat_number"));
				        vehicleTableView.getColumns().add(seat_numberColumn);
				        
				        TableColumn<Vehicle, Integer> door_numberColumn = new TableColumn<>("Numb. of doors");
				        door_numberColumn.setCellValueFactory(new PropertyValueFactory<>("door_number"));
				        vehicleTableView.getColumns().add(door_numberColumn);
						
						}
						
												
					
						int counter = 0; //+++++ αρχικοποιηση μεταβλητης counter = 0  
						
						//------ Ελεγχος για το αν υπαρχει ηδη ο license code που εβαλε ο χρηστης στο lncodeField
						for (Vehicle d : vehicleList) {
				            if ((d.getCode()).equals(lncode)) {
				                       counter++;				//+++++ αν υπαρχει τοτε το counter γινεται 1 και δεν μπορει να μπει στο παρακατω if και να παει στην createCar
				                       
				            }
						}
				        
						//----- Eλεγχος για το counter και για να μην ειναι κενα τα TextFields
						if(counter==0 && !lncode.equals("") && !model.equals("") && !fuel.equals("") && !typeff.equals("")){
				            	createCar(lncode, model , fuel , cube ,type1, rent_price , seat_number , door_number);
								tableSyncC();
								clearTextFields();
				            	}
						else {
							vehicleTableView.getItems().clear();
							vehicleTableView.setPlaceholder(new Label("The TextFields must not be empty and the License code must also be original"));
						}
				
					}
				
			
				else {
					vehicleTableView.getItems().clear();
					vehicleTableView.setPlaceholder(new Label("\t\tInsert the correct type of values on the Text Fields and \nonly enter Big/Medium/Small for a Car and Motorcycle/Skutter for a TwoWheeled!"));
					
					}
			}
		
			
			
			
				
			
			
				
			
			
			type2 = radioBtn2.getText() +" " + typeField.getText() ;
			
			String luggage_place;
			
		if(type2.equals("TwoWheeled Motorcycle") || type2.equals("TwoWheeled Skutter") ) {
				
				
				try {
					seat_height = Double.parseDouble(seat_heightField.getText());
				} catch(NumberFormatException e) {
					vehicleTableView.getItems().clear();
					vehicleTableView.setPlaceholder(new Label("\t\tInsert the correct type of values on the Text Fields and \nonly enter Big/Medium/Small for a Car and Motorcycle/Skutter for a TwoWheeled!"));
				}
				
			
				if ( seat_height != 0) {
				
				seat_height = Double.parseDouble(seat_heightField.getText());
				luggage_place = luggage_placeField.getText();
				
					 if (radio_type.equals("TwoWheeled"))
					{
						vehicleTableView.getColumns().clear();
		
				        TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
				        lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
				        vehicleTableView.getColumns().add(lncodeColumn);
		
				        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
				        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
				        vehicleTableView.getColumns().add(modelColumn);
		
				        
				        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
				        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
				        vehicleTableView.getColumns().add(fuelColumn);
		
				        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
				        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
				        vehicleTableView.getColumns().add(typeColumn);
		
				        TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
				        cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
				        vehicleTableView.getColumns().add(cubeColumn);
				 
				        TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
				        rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
				        vehicleTableView.getColumns().add(rent_priceColumn);
		
				        
				        TableColumn<Vehicle, Integer> seat_heightColumn = new TableColumn<>("Seat's Height");
				        seat_heightColumn.setCellValueFactory(new PropertyValueFactory<>("seat_height"));
				        vehicleTableView.getColumns().add(seat_heightColumn);
		
				        TableColumn<Vehicle, String> luggage_placeColumn = new TableColumn<>("Luggage Type");
				        luggage_placeColumn.setCellValueFactory(new PropertyValueFactory<>("luggage_place"));
				        vehicleTableView.getColumns().add(luggage_placeColumn);
		
					}
						

						int counter = 0;
						
						for (Vehicle d : vehicleList) {
				            if ((d.getCode()).equals(lncode)) {
				                       counter++;
				            }
						}
				        
						if(counter==0 && !lncode.equals("") && !model.equals("") && !fuel.equals("") && !typeff.equals("") && !luggage_place.equals("")){
								createTwoWheeled(lncode, model , fuel , cube ,type2, rent_price , seat_height , luggage_place);
								 tableSyncTW();
								clearTextFields();
				            	}
						else {
							vehicleTableView.getItems().clear();
							vehicleTableView.setPlaceholder(new Label("The TextFields must not be empty and the License code must also be original"));
						}
					 

						
						
					}		
			
					else {
						vehicleTableView.getItems().clear();
						vehicleTableView.setPlaceholder(new Label("\t\tInsert the correct type of values on the Text Fields and \nonly enter Big/Medium/Small for a Car and Motorcycle/Skutter for a TwoWheeled!"));	
					}
		
				}
			
			}	
            clearTextFields();       
        }
		
		
		
		
		
		if (event.getSource() == backBtn) {
            MainFX.mainStage.setTitle("Car Rental Service");
            MainFX.mainStage.setScene(MainFX.mainScene);
        }
		
		
		if (event.getSource() == vehicleTableView) {
           Vehicle selectedVehicle = vehicleTableView.getSelectionModel().getSelectedItem();
           
	           
	           //----- Ελεγχος για την τιμη του radio_type (δλδ του επιλεγμενου radio Button) και δημιουργια των καταλληλων columns του TableView και συμπληρωση των TextFields
	           if (selectedVehicle != null && radio_type.equals("Car")) {
	            	vehicleTableView.getColumns().clear();

	                TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
	                lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
	                vehicleTableView.getColumns().add(lncodeColumn);

	                TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
	                modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
	                vehicleTableView.getColumns().add(modelColumn);

	                
	                TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
	                fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
	                vehicleTableView.getColumns().add(fuelColumn);

	                TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
	                typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	                vehicleTableView.getColumns().add(typeColumn);

	                TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
	                cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
	                vehicleTableView.getColumns().add(cubeColumn);
	         
	                TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
	                rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
	                vehicleTableView.getColumns().add(rent_priceColumn);

	                
	                TableColumn<Vehicle, Double> seat_numberColumn = new TableColumn<>("Numb. of Seats");
	                seat_numberColumn.setCellValueFactory(new PropertyValueFactory<>("seat_number"));
	                vehicleTableView.getColumns().add(seat_numberColumn);
	                
	                TableColumn<Vehicle, Integer> door_numberColumn = new TableColumn<>("Numb. of doors");
	                door_numberColumn.setCellValueFactory(new PropertyValueFactory<>("door_number"));
	                vehicleTableView.getColumns().add(door_numberColumn);
	                
	                
		               lncodeField.setText(selectedVehicle.getCode());
		               modelField.setText(selectedVehicle.getModel());
		               fuelField.setText(selectedVehicle.getFuel());
		               cubeField.setText(Integer.toString(selectedVehicle.getCube()));
		               rent_priceField.setText(Double.toString(selectedVehicle.getRent_price()));
		               seat_numberField.setText(Integer.toString(((Car) selectedVehicle).getSeat_number()));
		               door_numberField.setText(Integer.toString(((Car) selectedVehicle).getDoor_number()));
		               
	            }
			
        
				else if (selectedVehicle != null && radio_type.equals("TwoWheeled")) 
			         {
					vehicleTableView.getColumns().clear();

			        TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
			        lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
			        vehicleTableView.getColumns().add(lncodeColumn);

			        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
			        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
			        vehicleTableView.getColumns().add(modelColumn);

			        
			        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
			        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
			        vehicleTableView.getColumns().add(fuelColumn);

			        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
			        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
			        vehicleTableView.getColumns().add(typeColumn);

			        TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
			        cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
			        vehicleTableView.getColumns().add(cubeColumn);
			 
			        TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
			        rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
			        vehicleTableView.getColumns().add(rent_priceColumn);

			        
			        TableColumn<Vehicle, Integer> seat_heightColumn = new TableColumn<>("Seat's Height");
			        seat_heightColumn.setCellValueFactory(new PropertyValueFactory<>("seat_height"));
			        vehicleTableView.getColumns().add(seat_heightColumn);

			        TableColumn<Vehicle, String> luggage_placeColumn = new TableColumn<>("Luggage Type");
			        luggage_placeColumn.setCellValueFactory(new PropertyValueFactory<>("luggage_place"));
			        vehicleTableView.getColumns().add(luggage_placeColumn);
			        
					
			        	 lncodeField.setText(selectedVehicle.getCode());
			        	 modelField.setText(selectedVehicle.getModel());
			             fuelField.setText(selectedVehicle.getFuel());
			             cubeField.setText(Integer.toString(selectedVehicle.getCube()));
			             rent_priceField.setText(Double.toString(selectedVehicle.getRent_price()));
			             seat_heightField.setText(Double.toString(((TwoWheeled) selectedVehicle).getSeat_height()));
			             luggage_placeField.setText(((TwoWheeled) selectedVehicle).getLuggage_place());
			            
			         }
				}
		
		
		if (event.getSource() == updateVehicleBtn) {
	            String code = lncodeField.getText();
	            String model = modelField.getText();
	            String fuel = fuelField.getText();
	            String cube = cubeField.getText();
	            String rent_price =  rent_priceField.getText();
	            String seat_number = seat_numberField.getText();
	            String door_number = door_numberField.getText();
	            String seat_height = seat_heightField.getText();
	            String luggage_place = luggage_placeField.getText();
	            
	            if ( radio_type.equals("Car"))
	            {
	            	 updateCar(code, model , fuel , Integer.parseInt(cube) , Double.parseDouble(rent_price) ,Integer.parseInt(seat_number) , Integer.parseInt(door_number));
	            	 tableSyncC();
	            }
	           
	            else if (radio_type.equals("TwoWheeled"))
	            {
	            	updateTwoWheeled(code, model , fuel , Integer.parseInt(cube) , Double.parseDouble(rent_price) , Double.parseDouble(seat_height) , luggage_place);
	            	tableSyncTW();
	            }
	            
	            
	            
	            clearTextFields();
	        
		}

		

	
		if (event.getSource() == deleteVehicleBtn) {
			deleteVehicle(lncodeField.getText());

            tableSyncC();
            clearTextFields();
       }
		
		
		//----- με το πατημα του View Vehicle Button δημιουργειται ξανα το TableView δειχνοντας ολα τα Columns ( του Car & του TwoWheeled )
		if (event.getSource() == viewVehicleBtn) {
			vehicleTableView.getColumns().clear();

	        TableColumn<Vehicle, String> lncodeColumn = new TableColumn<>("License No.");
	        lncodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
	        vehicleTableView.getColumns().add(lncodeColumn);

	        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
	        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
	        vehicleTableView.getColumns().add(modelColumn);

	        
	        TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
	        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
	        vehicleTableView.getColumns().add(fuelColumn);

	        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
	        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	        vehicleTableView.getColumns().add(typeColumn);

	        TableColumn<Vehicle, Integer> cubeColumn = new TableColumn<>("CC");
	        cubeColumn.setCellValueFactory(new PropertyValueFactory<>("cube"));
	        vehicleTableView.getColumns().add(cubeColumn);
	 
	        TableColumn<Vehicle, Double> rent_priceColumn = new TableColumn<>("Price/D");
	        rent_priceColumn.setCellValueFactory(new PropertyValueFactory<>("rent_price"));
	        vehicleTableView.getColumns().add(rent_priceColumn);

	        
	        TableColumn<Vehicle, Integer> seat_heightColumn = new TableColumn<>("Seat's Height");
	        seat_heightColumn.setCellValueFactory(new PropertyValueFactory<>("seat_height"));
	        vehicleTableView.getColumns().add(seat_heightColumn);

	        TableColumn<Vehicle, String> luggage_placeColumn = new TableColumn<>("Luggage Type");
	        luggage_placeColumn.setCellValueFactory(new PropertyValueFactory<>("luggage_place"));
	        vehicleTableView.getColumns().add(luggage_placeColumn);
			
			vehicleTableView.getItems().clear();
			List<Vehicle> items = vehicleTableView.getItems(); 

            TableColumn<Vehicle, Double> seat_numberColumn = new TableColumn<>("Numb. of Seats");
            seat_numberColumn.setCellValueFactory(new PropertyValueFactory<>("seat_number"));
            vehicleTableView.getColumns().add(seat_numberColumn);
            
            TableColumn<Vehicle, Integer> door_numberColumn = new TableColumn<>("Numb. of doors");
            door_numberColumn.setCellValueFactory(new PropertyValueFactory<>("door_number"));
            vehicleTableView.getColumns().add(door_numberColumn);
			
			for(Vehicle d : vehicleList) {
				items.add(d);  				
        	}
			
			
			
            tableSync();
            clearTextFields();
       }
		
		
		
		
		
		
		
		
	}




	private void updateTwoWheeled(String code, String model, String fuel, int cube, double rent_price,
			double seat_height, String luggage_place) {
		for (Vehicle d : vehicleList) {
			if ((d.getCode()).equals(code)) {
				
				d.setModel(model);
		         d.setFuel(fuel);
		         d.setCube(cube);
		         d.setRent_price(rent_price);
		         ((TwoWheeled) d).setSeat_height(seat_height);
		         ((TwoWheeled) d).setLuggage_place(luggage_place);
            
        }
		}
		
	}




	private void updateCar(String code, String model, String fuel,int cube, double rent_price, int seat_number,
			int door_number) {
		for (Vehicle d : vehicleList) {
			if ((d.getCode()).equals(code)) {
				
					d.setModel(model);
			         d.setFuel(fuel);
			         d.setCube(cube);
			         d.setRent_price(rent_price);
			         ((Car) d).setSeat_number(seat_number);
			         ((Car) d).setDoor_number(door_number);
                
            }
		}	
	}




	private void deleteVehicle(String text) {
		String code = lncodeField.getText();
		for (int i = 0; i < vehicleList.size(); i++) {
             if (vehicleList.get(i).getCode().equals(code)) {
                vehicleList.remove(i);
                 break;
             }
         }
	}



	private void createTwoWheeled(String lncode, String model,String fuel,  int cube, String type , double rent_price,
			double seat_height, String luggage_place) {
		TwoWheeled c = new TwoWheeled(lncode, model , fuel , type, cube , rent_price , seat_height , luggage_place);
		vehicleList.add(c);
	}




	private void createCar(String lncode, String model, String fuel, int cube, String type, double rent_price,
			int seat_number, int door_number) {
		Car d = new Car(lncode , model , fuel , type ,cube, rent_price , seat_number , door_number);
		vehicleList.add(d);
	}




	// βαζει στην 8εση των Text  τα " "
    public void clearTextFields() {
    		lncodeField.setText("");
    		modelField.setText("");
    		fuelField.setText("");
    		cubeField.setText("");
    		rent_priceField.setText("");
    		typeField.setText("");
    		seat_numberField.setText("");
    		door_numberField.setText("");
    		luggage_placeField.setText("");
    		seat_heightField.setText("");
    		searchvehicleField.setText("");

        }



    //----------------TableSync για το Car
	private void tableSyncC() {
        List<Vehicle> items = vehicleTableView.getItems();
        items.clear();
        for (Vehicle d : vehicleList) {
        	if(d instanceof Car) {
        		items.add((Vehicle) d);
        	}
        }
    }
	
	
	
	//----------------TableSync για το TwoWheeled
	private void tableSyncTW() {
        List<Vehicle> items = vehicleTableView.getItems();
        items.clear();
        for (Vehicle d : vehicleList) {
        	if(d instanceof TwoWheeled) {
        		items.add((Vehicle) d);
        	}
        }
    }

	private void tableSync() {
        List<Vehicle> items = vehicleTableView.getItems();
        items.clear();
        for (Vehicle d : vehicleList) {
            items.add((Vehicle) d);
        }
    }
	
	

	@Override
	Scene createScene() {
        return new Scene(rootGridPane, width, height);

	}

}