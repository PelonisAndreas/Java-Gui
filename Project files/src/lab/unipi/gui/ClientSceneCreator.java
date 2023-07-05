
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
import java.util.List;


public class ClientSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {	
    //=============FlowPane=============
    FlowPane buttonFlowPane,buttonFlowPane2;
    //=============GridPanes=============
    GridPane rootGridPane, inputFieldsPane,inputFieldsPane2;
    //=============Buttons=============
    Button newClientBtn, updateClientBtn, deleteClientBtn, backBtn,searchBtn,refreshBtn;
    //=============Labels=============
    Label clientFirstNameLbl,clientLastNameLbl,licenceNumberLbl,AMLbl,emailLbl,addressLbl,phoneNumberLbl,searchLbl;
    //=============TextFields=============
    TextField clientFirstNameField, clientLastNameField,licenceNumberField,AMField,emailField,addressField,phoneNumberField,searchField;
    //=============TableView=============
    TableView<Client> ClientView;

    public ClientSceneCreator(double width, double height) {
        super(width, height);

        rootGridPane = new GridPane();
        inputFieldsPane = new GridPane();
        inputFieldsPane2=new GridPane();
        
        buttonFlowPane = new FlowPane();
        buttonFlowPane2= new FlowPane();
        
        //=============Labels=============
        clientFirstNameLbl = new Label("First Name: ");
        clientLastNameLbl = new Label("Last Name: ");
        licenceNumberLbl = new Label("Licence Number: ");
        AMLbl = new Label("AM: ");
        emailLbl = new Label("E-mail: ");
        addressLbl = new Label("Address: ");
        phoneNumberLbl = new Label("Phone Number: ");
        searchLbl=new Label("Search By AM");
        
        //=============TextFields=============
        clientFirstNameField = new TextField();
        clientLastNameField = new TextField();
        licenceNumberField = new TextField();
        AMField = new TextField();
        emailField = new TextField();
        addressField = new TextField();
        phoneNumberField = new TextField();
        searchField=new TextField();
        

        //=============Buttons=============
        newClientBtn = new Button("Add");
        updateClientBtn = new Button("Update");
        deleteClientBtn = new Button("Delete");
        searchBtn=new Button("Search");
        refreshBtn=new Button("Refresh");
        backBtn = new Button("Go Back");
        ClientView = new TableView<>();

		//=============Events=============
        backBtn.setOnMouseClicked(this);
        newClientBtn.setOnMouseClicked(this);
        updateClientBtn.setOnMouseClicked(this);
        deleteClientBtn.setOnMouseClicked(this);
        ClientView.setOnMouseClicked(this);
        searchBtn.setOnMouseClicked(this);
        refreshBtn.setOnMouseClicked(this);
        
        //=============Προσαρμογη των buttonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newClientBtn);
        buttonFlowPane.getChildren().add(updateClientBtn);
        buttonFlowPane.getChildren().add(deleteClientBtn);
        buttonFlowPane.getChildren().add(refreshBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        //=============Προσαρμογη των buttonFlowPane2 για το κουμπι αναζητησης=============
        buttonFlowPane2.getChildren().add(searchBtn);
        buttonFlowPane2.setAlignment(Pos.CENTER_RIGHT);
        
        //=============Προσαρμογη των inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(clientFirstNameLbl, 0, 0);
        inputFieldsPane.add(clientFirstNameField, 1, 0);
        inputFieldsPane.add(clientLastNameLbl, 0, 1);
        inputFieldsPane.add(clientLastNameField, 1, 1);
        inputFieldsPane.add(licenceNumberLbl, 0, 2);
        inputFieldsPane.add(licenceNumberField, 1, 2);
        inputFieldsPane.add(AMLbl, 0, 3);
        inputFieldsPane.add(AMField, 1, 3);
        inputFieldsPane.add(emailLbl, 0, 4);
        inputFieldsPane.add(emailField, 1, 4);
        inputFieldsPane.add(addressLbl, 0, 5);
        inputFieldsPane.add(addressField, 1, 5);
        inputFieldsPane.add(phoneNumberLbl, 0, 6);
        inputFieldsPane.add(phoneNumberField, 1, 6);
        
        inputFieldsPane2.setVgap(10);
        inputFieldsPane2.setHgap(10);
        inputFieldsPane2.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane2.add(searchField, 1, 0);
        inputFieldsPane2.add(searchLbl, 0, 0);

      //=============Προσαρμογη των rootGridPane=============
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(inputFieldsPane2, 1, 1);
        rootGridPane.add(ClientView, 0,0);
        rootGridPane.add(buttonFlowPane, 0, 3);
        rootGridPane.add(buttonFlowPane2, 1 ,2);
        rootGridPane.add(backBtn, 1, 3);

        //=============Προσαρμογη των ClientView=============
        TableColumn<Client, String> clientFirstNameColumn = new TableColumn<>("First Name");
        clientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ClientView.getColumns().add(clientFirstNameColumn);

        TableColumn<Client, String> clientLastNameColumn = new TableColumn<>("Last Name");
        clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ClientView.getColumns().add(clientLastNameColumn);
        
        TableColumn<Client, String> licenceNumberColumn = new TableColumn<>("Licence No.");
        licenceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("licenceNumber"));
        ClientView.getColumns().add(licenceNumberColumn);
        
        TableColumn<Client, String> AMColumn = new TableColumn<>("AM");
        AMColumn.setCellValueFactory(new PropertyValueFactory<>("AM"));
        ClientView.getColumns().add(AMColumn);
        
        TableColumn<Client, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ClientView.getColumns().add(emailColumn);
        
        TableColumn<Client, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ClientView.getColumns().add(addressColumn);
        
        TableColumn<Client, Long> phoneNumberColumn = new TableColumn<>("Phone No.");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        ClientView.getColumns().add(phoneNumberColumn);
        
    }

    @Override
    public Scene createScene() {
        return new Scene(rootGridPane, width, height);
    }

    @Override
    public void handle(MouseEvent event) {
    	//=============Refresh για τον πινακα StoreView=============
    	if(event.getSource() == refreshBtn) {
    		tableSync();
    	}
    	
    	//=============Back Button=============
        if (event.getSource() == backBtn) {
            MainFX.mainStage.setTitle("Car Rental Service");
            MainFX.mainStage.setScene(MainFX.mainScene);
        }
        
        //=============Δημιουργια νεου αντικειμενου Store=============
        if (event.getSource() == newClientBtn) {
        	 String AM= AMField.getText();
        	 String licenceNumber= licenceNumberField.getText();
        	 String name= clientFirstNameField.getText();
        	 String surname= clientLastNameField.getText();
        	 String email= emailField.getText();
        	 String address= addressField.getText();
        	 long phoneNumber= 0;
        	 
        	 int counter = 0;
        	 
        	 //=============Ελεγχος για την περιπτωση που τα textfield ειναι κενα.=============
        	 if(!AM.equals("") && !licenceNumber.equals("") && !name.equals("") && !surname.equals("") && !email.equals("") && !address.equals("")) {
        		 
        		//=============Try Catch Exceptions για τις τιμες με τυπους long=============
     			try {
	           		 phoneNumber= Long.parseLong(phoneNumberField.getText());
	           		 
	        		 
	        		 for(Client testclient: clientList) {
	    	            	
	    	            	if(AM.equals(testclient.getAM()) || licenceNumber.equals(testclient.getLicenceNumber())) {//=============Ελεγχος για το αν υπαρχουν ηδη καταχωρημενα το ΑΜ ή ο αριθμος κυκλοφοριας=============
	    	            		
	    	            		counter++;////=============Counter που προσθετει +1 οταν βρεθει ενα απο τα 2 στο συστημα.=============
	    	            		
	    	            	}
	    	            	
	        		 }
	        		 
	        		 //=============Υπολογιζει οτι ο αριθμος τηλεφωνου ειναι 10 ψηφιος=============
	        		 int length = 0;
	        		 long temp = 1;
	        		 while (temp <= phoneNumber) {
	        		     length++;
	        		     temp *= 10;
	        		 }
	        		 
	      				 if(counter==0 && length==10) {//=============Αν ο αριθμος τηλεφωνου ειναι 10ψηφιος και τα ΑΜ/Αριθμος κυκλοφοριας δεν εχουν καταχωρηθει ,δημιουργει το αντικειμενο=============
	      					 createClient(AM ,licenceNumber,name ,surname ,email ,address ,phoneNumber);
	      		        	 tableSync();
	      		        	 clearTextFields();
		        		 } else {//=============Αλλιως βγαζει καταλληλο μηνυμα=============
		     				ClientView.getItems().clear();
		     				ClientView.setPlaceholder(new Label("Make sure you inserted a 10 digit number OR that AM/Licence No. are original"));
		        		   }
	           		 
    			} catch(NumberFormatException e) {//=============Αν βρεθει λανθασμενη τιμη βγαζει καταλληλο μηνυμα=============
    				ClientView.getItems().clear();
    				ClientView.setPlaceholder(new Label("Make sure you inserted 10 digit number on phone"));
    			}
     			clearTextFields();
        	 }
        }
        
        //=============Ενημερωση σε αντικειμενο του συστηματος.=============
        if (event.getSource() == updateClientBtn) {
        	String AM=AMField.getText();
        	String licenceNumber= licenceNumberField.getText();
        	String name= clientFirstNameField.getText();
        	String surname= clientLastNameField.getText();
        	String email= emailField.getText();
        	String address= addressField.getText();
        	long phoneNumber= 0;
         
        	if(!AM.equals("") && !licenceNumber.equals("") && !name.equals("") && !surname.equals("") && !email.equals("") && !address.equals("")) {
        		//=============Try Catch Exceptions για τις τιμες με τυπους long=============
	        	try {
	        		phoneNumber= Long.parseLong(phoneNumberField.getText());
		   		 //=============Υπολογιζει οτι ο αριθμος τηλεφωνου ειναι 10 ψηφιος=============
		   		 int length = 0;
		   		 long temp = 1;
		   		 while (temp <= phoneNumber) {
		   		     length++;
		   		     temp *= 10;
		   		 }
		   		 
		 				if(length==10) {//=============Αν ο αριθμος τηλεφωνου ειναι 10ψηφιος,δημιουργει το αντικειμενο=============
		 		            updateClient(AM ,licenceNumber,name ,surname ,email ,address ,phoneNumber);
		 		        	 tableSync();
		 		        	 clearTextFields();
		       		 } else {//=============Αλλιως βγαζει καταλληλο μηνυμα=============
		    				ClientView.getItems().clear();
		    				ClientView.setPlaceholder(new Label("Make sure you inserted a 10 digit number"));
		       		   }
	        	} catch(NumberFormatException e) {//=============Αν βρεθει λανθασμενη τιμη βγαζει καταλληλο μηνυμα=============
					ClientView.getItems().clear();
					ClientView.setPlaceholder(new Label("Make sure you inserted 10 digit number on phone"));
				}
	        }
        	clearTextFields();
        }
	       //=============Διαγραφη ενος αντικειμενου απο το συστημα.=============
        if (event.getSource() == deleteClientBtn) {
            deleteClient(AMField.getText());

            tableSync();
            clearTextFields();
        }
        
      //=============Προβολη ενος αντικειμενου απο τον πινακα και βαζει τις τιμες του στα TextField=============
        if (event.getSource() == ClientView) {
        	
            Client selectedClient = ClientView.getSelectionModel().getSelectedItem();
            
            if (selectedClient != null) {
            	
            	clientFirstNameField.setText(selectedClient.getName());
            	clientLastNameField.setText(selectedClient.getSurname());
            	addressField.setText(selectedClient.getAddress());
            	emailField.setText(selectedClient.getEmail());
            	phoneNumberField.setText(Long.toString(selectedClient.getPhoneNumber()));
            	licenceNumberField.setText(selectedClient.getLicenceNumber());
            	AMField.setText(selectedClient.getAM());

            }
        }
            
 	       //=============Αναζητηση αντικειμενων μεσω του AM=============
 	       if (event.getSource() == searchBtn) {
               if(searchField.getText().equals("")) {
                   tableSync();
                   clearTextFields(); 
               } else {
            	   SearchClient(searchField.getText());
               }
 	       }
	       
        
    }
    
  //=============Μεθοδος Αναζητησης των αντικειμενων μεσω του AM=============
	private void SearchClient(String AM2){
      List<Client> items = ClientView.getItems();
      ClientView.getItems().clear();
      for (Client temp2 : clientList) {
          if((temp2.getAM()).equals(AM2)) {
              items.add(temp2);
          } else {
              ClientView.getItems().clear();
              ClientView.setPlaceholder(new Label("Make sure you put an already existing AM in the search bar."));
           }
      }
	
	}
    
	//=============Μεθοδος που δημιουργει το αντικειμενο και το καταχωρει=============
    public void createClient(String AM , String licenceNumber , String name , String surname , String email , String address , long phoneNumber) {
        Client temp = new Client(AM ,licenceNumber,name ,surname ,email ,address ,phoneNumber);
        clientList.add(temp);
    }

  //=============Μεθοδος που κραταει τον πινακα ενημερωμενο οποτε δημιουργειται/ενημερωνεται/διαγραφεται ενα αντικειμενο στο συστημα=============
    public void tableSync() {
        List<Client> items = ClientView.getItems();
        items.clear();
        for (Client temp : clientList) {
                items.add(temp);        
        }
    }
    //=============Μεθοδος Ενημερωσης των αντικειμενων=============
    public void updateClient(String AM , String licenceNumber , String name , String surname , String email , String address , long phoneNumber) {
        for (Client temp : clientList) {
            if (temp.getAM().equals(AM) && temp.getLicenceNumber().equals(licenceNumber)) {
            	temp.setName(name);
            	temp.setSurname(surname);
            	temp.setAddress(address);
            	temp.setEmail(email);
            	temp.setPhoneNumber(phoneNumber);
            }
        }
    }
    //=============Μεθοδος Διαγραφης των αντικειμενων.=============
    public void deleteClient(String AM) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getAM().equals(AM)) {
                clientList.remove(i);
                break;
            }
        }
    }

    //=============Μεθοδος καθαρισμου των TextField=============
    public void clearTextFields() {
        clientFirstNameField.setText("");
        clientLastNameField.setText("");
        licenceNumberField.setText("");
        AMField.setText("");
        emailField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
    }
    
}
