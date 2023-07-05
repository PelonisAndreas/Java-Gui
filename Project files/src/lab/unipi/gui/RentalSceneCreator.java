package lab.unipi.gui;


import lab.unipi.core.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class RentalSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	//=============FlowPane=============
    FlowPane buttonFlowPane;
    //=============GridPanes=============
    GridPane rootGridPane, inputFieldsPane;
    //=============Buttons=============
    Button newRentBtn, updateRentBtn, deleteRentBtn, backBtn,refreshBtn;
    //=============Labels=============
    Label StoreRentLbl,StoreReturnLbl,LicenceNumLbl,ClientFirstNameLbl,ClientLastNameLbl,DateRentDayLbl,DateRentMonthLbl,
    DateRentYearLbl,DateRentHourLbl,DateReturnDayLbl,DateReturnMonthLbl,DateReturnYearLbl,DateReturnHourLbl,CostLbl;
    //=============TextFields=============
    TextField StoreRentField,StoreReturnField,LicenceNumField,ClientFirstNameField,ClientLastNameField,DateRentDayField,DateRentMonthField,
    DateRentYearField,DateRentHourField,DateReturnDayField,DateReturnMonthField,DateReturnYearField,DateReturnHourField,CostField;
    //=============TableView=============
    TableView<Rental> rentalView;
    private int tempcode;

    public RentalSceneCreator(double width, double height) {
        super(width, height);
        //  Initialize fields
        rootGridPane = new GridPane();
        buttonFlowPane = new FlowPane();
        
        //=============Labels και TextFields=============
        StoreRentLbl = new Label("Store Name to Rent: ");
        StoreRentField = new TextField();
        StoreReturnLbl = new Label("Store Name to Return: ");
        StoreReturnField = new TextField();
        LicenceNumLbl = new Label("Licence Number: ");
        LicenceNumField = new TextField();
        ClientFirstNameLbl = new Label("Client's First Name: ");
        ClientFirstNameField = new TextField();
        ClientLastNameLbl = new Label("Client's Last Name: ");
        ClientLastNameField = new TextField();
        DateRentDayLbl = new Label("Day of rent: (1-31) ");
        DateRentDayField = new TextField();
        DateRentMonthLbl = new Label("Month of rent: (0-11) ");
        DateRentMonthField = new TextField();
        DateRentYearLbl = new Label("Year of rent: ");
        DateRentYearField = new TextField();
        DateRentHourLbl = new Label("Hour of rent: (0-23) ");
        DateRentHourField = new TextField();
        DateReturnDayLbl = new Label("Day of return: (1-31) ");
        DateReturnDayField = new TextField();
        DateReturnMonthLbl = new Label("Month of return: (0-11) ");
        DateReturnMonthField = new TextField();
        DateReturnYearLbl = new Label("Year of return: ");
        DateReturnYearField = new TextField();
        DateReturnHourLbl = new Label("Hour of return: (0-23) ");
        DateReturnHourField = new TextField();
        CostLbl = new Label("Cost: ");
        CostField = new TextField();
        
        //=============Buttons=============
        newRentBtn = new Button("Rent");
        updateRentBtn = new Button("Update");
        deleteRentBtn = new Button("Cancel Rent");
        refreshBtn=new Button("Refresh");
        backBtn = new Button("Go Back");
        inputFieldsPane = new GridPane();
        rentalView = new TableView<>();
        

        		
		//=============Events=============
        backBtn.setOnMouseClicked(this);
        newRentBtn.setOnMouseClicked(this);
        updateRentBtn.setOnMouseClicked(this);
        deleteRentBtn.setOnMouseClicked(this);
        rentalView.setOnMouseClicked(this);
		refreshBtn.setOnMouseClicked(this);

        //=============Προσαρμογη των buttonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newRentBtn);
        buttonFlowPane.getChildren().add(updateRentBtn);
        buttonFlowPane.getChildren().add(deleteRentBtn);
        buttonFlowPane.getChildren().add(refreshBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);

        //=============Προσαρμογη των inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(LicenceNumLbl, 0, 0);
        inputFieldsPane.add(LicenceNumField, 1, 0);
        inputFieldsPane.add(ClientFirstNameLbl, 0, 1);
        inputFieldsPane.add(ClientFirstNameField, 1, 1);
        inputFieldsPane.add(ClientLastNameLbl, 0, 2);
        inputFieldsPane.add(ClientLastNameField, 1,2);
        inputFieldsPane.add(DateRentDayLbl, 0, 3);
        inputFieldsPane.add(DateRentDayField, 1, 3);
        inputFieldsPane.add(DateRentMonthLbl, 0, 4);
        inputFieldsPane.add(DateRentMonthField, 1, 4);
        inputFieldsPane.add(DateRentYearLbl, 0, 5);
        inputFieldsPane.add(DateRentYearField, 1, 5);
        inputFieldsPane.add(DateRentHourLbl, 0, 6);
        inputFieldsPane.add(DateRentHourField, 1, 6);
        inputFieldsPane.add(DateReturnDayLbl, 0, 7);
        inputFieldsPane.add(DateReturnDayField, 1, 7);
        inputFieldsPane.add(DateReturnMonthLbl, 0, 8);
        inputFieldsPane.add(DateReturnMonthField, 1, 8);
        inputFieldsPane.add(DateReturnYearLbl, 0, 9);
        inputFieldsPane.add(DateReturnYearField, 1, 9);
        inputFieldsPane.add(DateReturnHourLbl, 0, 10);
        inputFieldsPane.add(DateReturnHourField, 1, 10);
        inputFieldsPane.add(StoreRentLbl, 0, 11);
        inputFieldsPane.add(StoreRentField, 1, 11);
        inputFieldsPane.add(StoreReturnLbl, 0, 12);
        inputFieldsPane.add(StoreReturnField, 1, 12);
        inputFieldsPane.add(CostLbl, 0, 13);
        inputFieldsPane.add(CostField, 1, 13);

        //=============Προσαρμογη των rootGridPane=============
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(rentalView, 0, 0);
        rootGridPane.add(buttonFlowPane, 0, 2);
        rootGridPane.add(backBtn, 1, 2);

        //=============Προσαρμογη των rentalView=============
        TableColumn<Rental, Integer> rentalcodeColumn = new TableColumn<>("Rental Code");
        rentalcodeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalCode"));
        rentalView.getColumns().add(rentalcodeColumn);
        
        TableColumn<Rental, Date> DateRentNameColumn = new TableColumn<>("Date of Rent");
        DateRentNameColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
        rentalView.getColumns().add(DateRentNameColumn);
        
        TableColumn<Rental, Date> DateReturnColumn = new TableColumn<>("Date of Return");
        DateReturnColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
        rentalView.getColumns().add(DateReturnColumn);
        
        TableColumn<Rental, String> clientFirstNameColumn = new TableColumn<>("Last Name");
        clientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientLastName"));
        rentalView.getColumns().add(clientFirstNameColumn);
        
        TableColumn<Rental, String> clientLastNameColumn = new TableColumn<>("First Name");
        clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientFirstName"));
        rentalView.getColumns().add(clientLastNameColumn);
        
        
        TableColumn<Rental, String> storeRentColumn = new TableColumn<>("Store to Rent");
        storeRentColumn.setCellValueFactory(new PropertyValueFactory<>("storeRent"));
        rentalView.getColumns().add(storeRentColumn);
        
        TableColumn<Rental, String> storeReturnColumn = new TableColumn<>("Store to Return");
        storeReturnColumn.setCellValueFactory(new PropertyValueFactory<>("storeReturn"));
        rentalView.getColumns().add(storeReturnColumn);
        
        TableColumn<Rental, String> VehicleLicenceColumn = new TableColumn<>("Vehicle Licence");
        VehicleLicenceColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleLicence"));
        rentalView.getColumns().add(VehicleLicenceColumn);
        
        TableColumn<Rental, Double> CostColumn = new TableColumn<>("Rent Cost");
        CostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        rentalView.getColumns().add(CostColumn);
    }
    

    @Override
    public Scene createScene() {
        return new Scene(rootGridPane, width, height);
    }

    @Override
    public void handle(MouseEvent event) {
    	
		if(event.getSource() == refreshBtn) {
            tableSync();
        }
    	
        if (event.getSource() == backBtn) {
            MainFX.mainStage.setTitle("Car Rental Service");
            MainFX.mainStage.setScene(MainFX.mainScene);
        }
        
        
        if (event.getSource() == newRentBtn) {
        	
            String storeRent = StoreRentField.getText();
            String storeReturn = StoreReturnField.getText();
            String licenceNum = LicenceNumField.getText();
            String clientFirstName = ClientFirstNameField.getText();
            String clientLastName = ClientLastNameField.getText();
            int dateRentDay = -1;
            int dateRentMonth = -1;
            int dateRentYear = 0;
            int dateRentHour = -1;
            int dateReturnDay = -1;
            int dateReturnMonth = -1;
            int dateReturnYear = 0;
            int dateReturnHour = -1;
            double cost=0;
            
            //----- Ελεγχος για την εισοδο κενων TextFields
	       	 if(!storeRent.equals("") && !storeReturn.equals("") && !licenceNum.equals("") && !clientFirstName.equals("") && !clientLastName.equals("")) {
	    		 
	    		 //----- Aρχικοποιηση μεταβλητων αναλογα των εισοδων στα TextFiels και Εxception Handling
	    			try {
	    	             dateRentDay =	Integer.parseInt(DateRentDayField.getText());
	    	             dateRentMonth = Integer.parseInt(DateRentMonthField.getText());
	    	             dateRentYear = Integer.parseInt(DateRentYearField.getText());
	    	             dateRentHour = Integer.parseInt(DateRentHourField.getText());
	    	             dateReturnDay = Integer.parseInt(DateReturnDayField.getText());
	    	             dateReturnMonth = Integer.parseInt(DateReturnMonthField.getText());
	    	             dateReturnYear = Integer.parseInt(DateReturnYearField.getText());
	    	             dateReturnHour = Integer.parseInt(DateReturnHourField.getText());
	    	             cost=Double.parseDouble(CostField.getText());
	    				
		        		 //----- Ελεγχος για την εγκυροτητα των τιμων
		      			 if( (dateRentDay>=0 && dateRentDay<=6) && (dateRentMonth>=0&& dateRentMonth<=11) && (dateRentHour>=0 && dateRentHour<=23) &&
		      					(dateReturnDay>=0 && dateReturnDay<=6) && (dateReturnMonth>=0 && dateReturnMonth<=11) && (dateReturnHour>=0 && dateReturnHour<=23)) {
		      				
		      				 //----- Δημιουργια των ημερομηνιων
		      	            Date dateRent=new Date(dateRentYear,dateRentMonth,dateRentDay,dateRentHour,0);
		      	            Date dateReturn=new Date(dateReturnYear,dateReturnMonth,dateReturnDay,dateReturnHour,0);
		      				 
		      				 
		      	            createRent(dateRent,dateReturn,storeRent,storeReturn,licenceNum,clientFirstName,clientLastName,cost);
		      	            tableSync();
		      		       	clearTextFields();
			        	 } else {
			        		 rentalView.getItems().clear();
			        		 rentalView.setPlaceholder(new Label("Make sure you entered the correct values"));
			        	 }
		           		 
			   		} catch(NumberFormatException e) {
			   			rentalView.getItems().clear();
			   			rentalView.setPlaceholder(new Label("Make sure you entered the correct values"));
			   		}
			    			clearTextFields();
			 }
			       	 
	       	 else {
	   				rentalView.getItems().clear();
	   				rentalView.setPlaceholder(new Label("Make sure you entered the correct values"));
	       	 }
	       	
            
            tableSync();
            clearTextFields();
        }
        
        
        
        //----- DELETE Button
        if(event.getSource()==deleteRentBtn) {
        	
            String storeRent = StoreRentField.getText();
            String storeReturn = StoreReturnField.getText();
            String licenceNum = LicenceNumField.getText();
            String clientFirstName = ClientFirstNameField.getText();
            String clientLastName = ClientLastNameField.getText();
            int dateRentDay =	Integer.parseInt(DateRentDayField.getText());
            int dateRentMonth = Integer.parseInt(DateRentMonthField.getText());
            int dateRentYear = Integer.parseInt(DateRentYearField.getText());
            int dateRentHour = Integer.parseInt(DateRentHourField.getText());
            int dateReturnDay = Integer.parseInt(DateReturnDayField.getText());
            int dateReturnMonth = Integer.parseInt(DateReturnMonthField.getText());
            int dateReturnYear = Integer.parseInt(DateReturnYearField.getText());
            int dateReturnHour = Integer.parseInt(DateReturnHourField.getText());
            double cost=Double.parseDouble(CostField.getText());
            
            Date dateRent=new Date(dateRentYear,dateRentMonth,dateRentDay,dateRentHour,0);
            Date dateReturn=new Date(dateReturnYear,dateReturnMonth,dateReturnDay,dateReturnHour,0);
        	
            deleteRent(dateRent,dateReturn,storeRent,storeReturn,licenceNum,clientFirstName,clientLastName,cost);
            
            tableSync();
            clearTextFields();
        }
        
        
        //----- Event για οταν επιλεξει ενα αντικειμενο απτον πινακα
        if (event.getSource() == rentalView) {
        	
            Rental selectedRent = rentalView.getSelectionModel().getSelectedItem();
            
            if (selectedRent != null) {
            	
            	StoreRentField.setText(selectedRent.getStoreRent());
            	StoreReturnField.setText(selectedRent.getStoreReturn());
            	LicenceNumField.setText(selectedRent.getVehicleLicence());
            	ClientFirstNameField.setText(selectedRent.getClientFirstName());
            	ClientLastNameField.setText(selectedRent.getClientLastName());
                DateRentDayField.setText(Integer.toString(selectedRent.getDateOfRent().getDate()));
                DateRentMonthField.setText(Integer.toString(selectedRent.getDateOfRent().getMonth()));
                DateRentYearField.setText(Integer.toString(selectedRent.getDateOfRent().getYear()));
                DateRentHourField.setText(Integer.toString(selectedRent.getDateOfRent().getHours()));
                DateReturnDayField.setText(Integer.toString(selectedRent.getDateOfReturn().getDate()));
                DateReturnMonthField.setText(Integer.toString(selectedRent.getDateOfReturn().getMonth()));
                DateReturnYearField.setText(Integer.toString(selectedRent.getDateOfReturn().getYear()));
                DateReturnHourField.setText(Integer.toString(selectedRent.getDateOfReturn().getHours()));
                CostField.setText(Double.toString(selectedRent.getCost()));
                tempcode=selectedRent.getRentalCode();

            }
        }
        
    }
    
    //----- CREATE RENT -----
    public void createRent(Date dateOfRent, Date dateOfReturn, String storeRent,String storeReturn,String licenceNum,String clientFirstName,String clientLastName,double cost){
 
    	
    	//+++++ Δημιουργια και αρχικοποιηση μεταβλητων ελεγχου και συγκριση τους με στοιχεια των διαφορων Lists +++++
         	
    	//================Counter για τον ελεγχο υπαρξης του καταστηματος ενοικιασης στην Λιστα================
    	int S1counter=0;
        for (Store store1 : storeList) {
            if(store1.getStore_name().equals(storeRent)) {
            	S1counter++;
            	break;
            }
        }
       
    	//================Counter για τον ελεγχο υπαρξης του καταστηματος επιστροφης στην Λιστα================
    	int S2counter=0;
        for (Store store2 : storeList) {
            if(store2.getStore_name().equals(storeReturn)) {
            	S2counter++;
            	break;
            }
        }
      
    	//================Counter για τον ελεγχο υπαρξης του πελατη στην Λιστα================
        int Ccounter=0;
        for (Client client : clientList) {
            if(client.getName().equals(clientFirstName) && client.getSurname().equals(clientLastName)) {
            	Ccounter++;
            	break;
            }
        }
        
    	//================Δημιουργια αντικειμενων Date για τις 2 ημερομηνιες και μιας now για την τωρινη ημερομηνια================
        
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        
        cal1.setTime(dateOfRent);
        
        cal2.setTime(dateOfReturn);
        
        Date now= new Date();
        
        
        
    	//================Counter για τον ελεγχο υπαρξης του αριθμου κυκλοφοριας στην Λιστα================
        int Vcounter=0;
        double VRentPrice=0;
        for (Vehicle vehicle : vehicleList) {
            if(vehicle.getCode().equals(licenceNum) ) {
                Vcounter++;
                VRentPrice=vehicle.getRent_price();
            }
        }
    	
        
        
        //----- Οταν οι προηγουμενες μεταβλητες ειναι ισες με 1 και η ημερομηνια επιστροφης δεν ειναι πιο πριν απτην ημερομηνια ενοικιασης αλλα και την τωρινη και το συνολικο κοστος ειναι αναλογο των ημερομηνιων πραγματοποιειται η if
    	if(Vcounter==1 && Ccounter==1 && S1counter==1 && S2counter==1 && dateOfRent.compareTo(dateOfReturn)<0 && (dateOfRent.compareTo(now)>0 && dateOfReturn.compareTo(now)>0) && VRentPrice*daysBetween(cal1.getTime(),cal2.getTime())==cost) {
	        Rental temp = new Rental(dateOfRent,dateOfReturn,clientFirstName,clientLastName,licenceNum,storeRent,storeReturn,cost);
	        rentalList.add(temp);
	    }
    }
    
    //=============Μεθοδος Διαγραφης των αντικειμενων.=============
    public void deleteRent(Date dateOfRent, Date dateOfReturn, String storeRent,String storeReturn,String licenceNum,String clientFirstName,String clientLastName,double cost) {
    	
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        Date now= new Date();
        cal1.setTime(now);
        
        cal2.setTime(dateOfRent);

        //----- Ελεγχος για το αν εχει περασει η ημερομηνια ενοικιασης και εχουν απομεινει τουλαχιστον 2 μερες μεχρι την ημερομηνια επιστροφης
        if(now.compareTo(dateOfRent)<0 && daysBetween(cal1.getTime(),cal2.getTime())>=2) {
            for (int i = 0; i < rentalList.size(); i++) {
                if (rentalList.get(i).getRentalCode()==tempcode) {
                    rentalList.remove(i);
                    break;
                }
            }
        }
    }
    
    //=============Μεθοδος καθαρισμου των TextField=============
    public void clearTextFields() {
    	StoreRentField.setText("");
    	StoreReturnField.setText("");
    	LicenceNumField.setText("");
    	ClientFirstNameField.setText("");
    	ClientLastNameField.setText("");
    	DateRentDayField.setText("");
    	DateRentMonthField.setText("");
    	DateRentYearField.setText("");
    	DateRentHourField.setText("");
    	DateReturnDayField.setText("");
    	DateReturnMonthField.setText("");
    	DateReturnYearField.setText("");
    	DateReturnHourField.setText("");
    	CostField.setText("");
        
    }
    
  //=============Μεθοδος που κραταει τον πινακα ενημερωμενο οποτε δημιουργειται/διαγραφεται ενα αντικειμενο στο συστημα=============
    public void tableSync() {
        List<Rental> items = rentalView.getItems();
        items.clear();
        for (Rental temp : rentalList) {
                items.add(temp);        
        }
    }
    //+++++++++++++++++Υπολογισμος διαφορας των 2 ημερομηνιων σε μερες+++++++++++++++++
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}