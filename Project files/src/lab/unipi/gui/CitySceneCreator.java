
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


public class CitySceneCreator extends SceneCreator implements EventHandler<MouseEvent> {	
    //=============FlowPane=============
    FlowPane buttonFlowPane;
    
    //=============GridPanes=============
    GridPane rootGridPane, inputFieldsPane;
    
    //=============Buttons=============
    Button newCityBtn, updateCityBtn, deleteCityBtn, backBtn,refreshBtn;
    
    //=============Labels=============
    Label cityNameLbl;
    
    //=============TextFields=============
    TextField cityNameField, codeField;
    
    //=============TableView=============
    TableView<City> cityView;
    
    private int tempcode;
    public CitySceneCreator(double width, double height) {
        super(width, height);
        

        rootGridPane = new GridPane();
        buttonFlowPane = new FlowPane();

        cityNameLbl = new Label("City Name: ");
        cityNameField = new TextField();

        newCityBtn = new Button("New City");
        updateCityBtn = new Button("Update");
        deleteCityBtn = new Button("Delete");
        refreshBtn=new Button("Refresh");
        backBtn = new Button("Go Back");
        
        inputFieldsPane = new GridPane();
        cityView = new TableView<>();

        //=============Προσαρμογη των Εvent=============
        backBtn.setOnMouseClicked(this);
        newCityBtn.setOnMouseClicked(this);
        updateCityBtn.setOnMouseClicked(this);
        deleteCityBtn.setOnMouseClicked(this);
        cityView.setOnMouseClicked(this);
        refreshBtn.setOnMouseClicked(this);

        //=============Προσαρμογη των ButtonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newCityBtn);
        buttonFlowPane.getChildren().add(updateCityBtn);
        buttonFlowPane.getChildren().add(deleteCityBtn);
        buttonFlowPane.getChildren().add(refreshBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);

        //=============Προσαρμογη των inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(cityNameLbl, 0, 0);
        inputFieldsPane.add(cityNameField, 1, 0);

        //=============Προσαρμογη των rootGridPane=============
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(cityView, 0, 0);
        rootGridPane.add(buttonFlowPane, 0, 2);
        rootGridPane.add(backBtn, 1, 2);

        //=============Προσαρμογη των  cityView=============
        TableColumn<City, String> cityNameColumn = new TableColumn<>("City Name");
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        cityView.getColumns().add(cityNameColumn);

        TableColumn<City, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        cityView.getColumns().add(codeColumn);
    }

    @Override
    public Scene createScene() {
        return new Scene(rootGridPane, width, height);
    }

    @Override
    public void handle(MouseEvent event) {
    	//=============Refresh για τον πινακα cityView=============
		if(event.getSource() == refreshBtn) {
            tableSync();
        }
    	//=============Back Button=============
        if (event.getSource() == backBtn) {
            MainFX.mainStage.setTitle("Car Rental Service");
            MainFX.mainStage.setScene(MainFX.mainScene);
        }
        //=============Δημιουργια νεου αντικειμενου City=============
        if (event.getSource() == newCityBtn) {
            String cityName = cityNameField.getText();
            int counter=0;
            
            if(!cityName.equals("")) {//=============Ελεγχος για την περιπτωση που τα textfield ειναι κενα.=============
            	
    		 for(City testclient: cityList) {
	            	
	            	if(cityName.equals(testclient.getCity_name())) {//=============Ελεγχος για την περιπτωση που το αντικειμενο city υπαρχει ηδη στο συστημα.=============
	            		
	            		counter++;//=============Counter που προσθετει +1 οταν βρεθει το ιδιο αντικειμενο στο συστημα.=============
	            		
	            	}
	            	
    		 }
    		 if(counter==0) {
    	            createCity(cityName);
    		 }
            tableSync();
            clearTextFields();
            }
        }
        
        //=============Ενημερωση σε αντικειμενο του συστηματος αν θελει να αλλαξει το ονομα της πολης.=============
        
        if (event.getSource() == updateCityBtn) {
            String cityName2 = cityNameField.getText();
            
            updateCity(cityName2);

            tableSync();
            clearTextFields();
        }
        
        //=============Διαγραφη ενος αντικειμενου απο το συστημα.=============
        if (event.getSource() == deleteCityBtn) {
            deleteCity(cityNameField.getText());

            tableSync();
            clearTextFields();
        }
        //=============Προβολη ενος αντικειμενου απο τον πινακα και βαζει τις τιμες του στα TextField=============
        if (event.getSource() == cityView) {
            City selectedCity = cityView.getSelectionModel().getSelectedItem();
            if (selectedCity != null) {
                cityNameField.setText(selectedCity.getCity_name());
                tempcode=selectedCity.getCode();//=============Ο μοναδικος κωδικος για οταν θελει να χρησιμοποιησει αυτον τον τροπο για αλλαγη στοιχειων=============
            }
        	
        }
    }

    //=============Μεθοδος που δημιουργει το αντικειμενο και το καταχωρει=============
    public void createCity(String name) {
        City temp = new City(name);
        cityList.add(temp);
    }


    //=============Μεθοδος Ενημερωσης των αντικειμενων=============
    public void updateCity(String name) {
        for (City temp : cityList) {
            if (temp.getCode()==tempcode) {//=============ευρεση του αντικειμενου στον πινακα με βαση τον μοναδικο κωδικο=============
            	temp.setCity_name(name);
            	tempcode=-1;
                for(int j = 0; j < storeList.size(); j++) {//=============ελεγχος σε περιπτωση που υπαρχει καταχωρημενο καταστημα στην πολη αυτη να το διαγραφει=============
                										   //=============Αλλαζει η πολη και ετσι δεν υπαρχουν τα καταστηματα=============
                	if(storeList.get(j).getCity_name().equals(name)) {
                		storeList.remove(j);
                	}
                }
            }
        }
    }
    //=============Μεθοδος Διαγραφης των αντικειμενων.=============
    public void deleteCity(String name) {
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getCity_name().equals(name)) {
                cityList.remove(i);
                for(int j = 0; j < storeList.size(); j++) {
                	if(storeList.get(j).getCity_name().equals(name)) {
                		storeList.remove(j);
                	}
                }
                break;
            }
        }
    }
    
    //=============Μεθοδος που κραταει τον πινακα ενημερωμενο οποτε δημιουργειται/ενημερωνεται/διαγραφεται ενα αντικειμενο στο συστημα=============
    public void tableSync() {
        List<City> items = cityView.getItems();
        items.clear();
        for (City temp : cityList) {
                items.add(temp);        
        }
    }
    
    //=============Μεθοδος καθαρισμου των TextField=============
    public void clearTextFields() {
        cityNameField.setText("");
    }
}
