
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

        //=============���������� ��� �vent=============
        backBtn.setOnMouseClicked(this);
        newCityBtn.setOnMouseClicked(this);
        updateCityBtn.setOnMouseClicked(this);
        deleteCityBtn.setOnMouseClicked(this);
        cityView.setOnMouseClicked(this);
        refreshBtn.setOnMouseClicked(this);

        //=============���������� ��� ButtonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newCityBtn);
        buttonFlowPane.getChildren().add(updateCityBtn);
        buttonFlowPane.getChildren().add(deleteCityBtn);
        buttonFlowPane.getChildren().add(refreshBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);

        //=============���������� ��� inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(cityNameLbl, 0, 0);
        inputFieldsPane.add(cityNameField, 1, 0);

        //=============���������� ��� rootGridPane=============
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(cityView, 0, 0);
        rootGridPane.add(buttonFlowPane, 0, 2);
        rootGridPane.add(backBtn, 1, 2);

        //=============���������� ���  cityView=============
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
    	//=============Refresh ��� ��� ������ cityView=============
		if(event.getSource() == refreshBtn) {
            tableSync();
        }
    	//=============Back Button=============
        if (event.getSource() == backBtn) {
            MainFX.mainStage.setTitle("Car Rental Service");
            MainFX.mainStage.setScene(MainFX.mainScene);
        }
        //=============���������� ���� ������������ City=============
        if (event.getSource() == newCityBtn) {
            String cityName = cityNameField.getText();
            int counter=0;
            
            if(!cityName.equals("")) {//=============������� ��� ��� ��������� ��� �� textfield ����� ����.=============
            	
    		 for(City testclient: cityList) {
	            	
	            	if(cityName.equals(testclient.getCity_name())) {//=============������� ��� ��� ��������� ��� �� ����������� city ������� ��� ��� �������.=============
	            		
	            		counter++;//=============Counter ��� ��������� +1 ���� ������ �� ���� ����������� ��� �������.=============
	            		
	            	}
	            	
    		 }
    		 if(counter==0) {
    	            createCity(cityName);
    		 }
            tableSync();
            clearTextFields();
            }
        }
        
        //=============��������� �� ����������� ��� ���������� �� ����� �� ������� �� ����� ��� �����.=============
        
        if (event.getSource() == updateCityBtn) {
            String cityName2 = cityNameField.getText();
            
            updateCity(cityName2);

            tableSync();
            clearTextFields();
        }
        
        //=============�������� ���� ������������ ��� �� �������.=============
        if (event.getSource() == deleteCityBtn) {
            deleteCity(cityNameField.getText());

            tableSync();
            clearTextFields();
        }
        //=============������� ���� ������������ ��� ��� ������ ��� ����� ��� ����� ��� ��� TextField=============
        if (event.getSource() == cityView) {
            City selectedCity = cityView.getSelectionModel().getSelectedItem();
            if (selectedCity != null) {
                cityNameField.setText(selectedCity.getCity_name());
                tempcode=selectedCity.getCode();//=============� ��������� ������� ��� ���� ����� �� �������������� ����� ��� ����� ��� ������ ���������=============
            }
        	
        }
    }

    //=============������� ��� ���������� �� ����������� ��� �� ���������=============
    public void createCity(String name) {
        City temp = new City(name);
        cityList.add(temp);
    }


    //=============������� ���������� ��� ������������=============
    public void updateCity(String name) {
        for (City temp : cityList) {
            if (temp.getCode()==tempcode) {//=============������ ��� ������������ ���� ������ �� ���� ��� �������� ������=============
            	temp.setCity_name(name);
            	tempcode=-1;
                for(int j = 0; j < storeList.size(); j++) {//=============������� �� ��������� ��� ������� ������������ ��������� ���� ���� ���� �� �� ���������=============
                										   //=============������� � ���� ��� ���� ��� �������� �� �����������=============
                	if(storeList.get(j).getCity_name().equals(name)) {
                		storeList.remove(j);
                	}
                }
            }
        }
    }
    //=============������� ��������� ��� ������������.=============
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
    
    //=============������� ��� ������� ��� ������ ����������� ����� �������������/������������/����������� ��� ����������� ��� �������=============
    public void tableSync() {
        List<City> items = cityView.getItems();
        items.clear();
        for (City temp : cityList) {
                items.add(temp);        
        }
    }
    
    //=============������� ���������� ��� TextField=============
    public void clearTextFields() {
        cityNameField.setText("");
    }
}
