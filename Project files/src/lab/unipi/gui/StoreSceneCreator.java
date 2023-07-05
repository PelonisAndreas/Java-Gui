package lab.unipi.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import lab.unipi.core.City;

import lab.unipi.core.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
	//=============FlowPane=============
    FlowPane buttonFlowPane , buttonFlowPane2;
    
    //=============GridPanes=============
    GridPane rootGridPane, inputFieldsPane , inputFieldsPane2;
    
    //=============Buttons=============
    Button newStoreBtn, deleteStoreBtn, updateStoreBtn, backBtn ,viewStoreBtn ,searchStoreBtn,refreshBtn;
    
    //=============Labels=============
    Label  storenameLbl , citynameLbl , searchLbl;
    
    //=============TextFields=============
    TextField  storenameField, citynameField , searchstoreField ;
    
    //=============TableView=============
    TableView<Store> storeTableView;
    

	
	private int tempcode;
	
	public StoreSceneCreator(double width, double height) {
		
		super(width, height);
		
		storeList = new ArrayList<>();
		rootGridPane = new GridPane();
		
        buttonFlowPane = new FlowPane();
        buttonFlowPane2 = new FlowPane();
        
        //=============Labels=============
        storenameLbl = new Label("Store's Name: ");
        citynameLbl = new Label("City's Name: ");
        searchLbl = new Label("Search bar");
        //=============TextFields=============
        storenameField = new TextField();
        searchstoreField = new TextField();
        citynameField = new TextField();
        
        //=============Buttons=============
        newStoreBtn = new Button("New Store");
		deleteStoreBtn = new Button("Delete Store");
		updateStoreBtn = new Button("Update Store");
		refreshBtn=new Button("Refresh");
		backBtn = new Button("Back");
		viewStoreBtn = new Button("View Stores");
		searchStoreBtn= new Button("Search Store");
		
       	inputFieldsPane = new GridPane();
       	inputFieldsPane2 = new GridPane();
       	
		storeTableView = new TableView<>();
		
		//=============Events=============
        backBtn.setOnMouseClicked(this);
        newStoreBtn.setOnMouseClicked(this);
        updateStoreBtn.setOnMouseClicked(this);
        deleteStoreBtn.setOnMouseClicked(this);
        storeTableView.setOnMouseClicked(this);
        searchStoreBtn.setOnMouseClicked(this);
        refreshBtn.setOnMouseClicked(this);
        
        //=============���������� ��� buttonFlowPane=============
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newStoreBtn);
        buttonFlowPane.getChildren().add(updateStoreBtn);
        buttonFlowPane.getChildren().add(deleteStoreBtn);
        buttonFlowPane.getChildren().add(refreshBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        
        //=============���������� ��� buttonFlowPane2 ��� �� ������ ����������=============
        buttonFlowPane2.getChildren().add(searchStoreBtn);
        buttonFlowPane2.setAlignment(Pos.CENTER_RIGHT);
        
        
        //=============���������� ��� inputFieldsPane=============
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(storenameLbl, 0, 0);
        inputFieldsPane.add(citynameLbl, 0, 1);
        inputFieldsPane.add(storenameField, 1, 0);
        inputFieldsPane.add(citynameField, 1, 1);
        
        inputFieldsPane2.setVgap(10);
        inputFieldsPane2.setHgap(10);
        inputFieldsPane2.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane2.add(searchstoreField, 1, 0);
        inputFieldsPane2.add(searchLbl, 0, 0);

     
        
        //=============���������� ��� rootGridPane=============
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(inputFieldsPane2, 1, 1);
        rootGridPane.add(storeTableView, 0,0);
        rootGridPane.add(buttonFlowPane, 0, 2);
        rootGridPane.add(buttonFlowPane2, 1 ,2);
        rootGridPane.add(backBtn, 1, 3);
        
        //=============���������� ��� storeTableView=============
        TableColumn<Store, String> storenameColumn = new TableColumn<>("Store's name");
        storenameColumn.setCellValueFactory(new PropertyValueFactory<>("store_name"));
        storeTableView.getColumns().add(storenameColumn);
        
        TableColumn<Store, String> citynameColumn = new TableColumn<>("City's name");
        citynameColumn.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        storeTableView.getColumns().add(citynameColumn);
        
        TableColumn<Store, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        storeTableView.getColumns().add(codeColumn);
        
	}




	@Override
	public void handle(MouseEvent event) {
    	//=============Refresh ��� ��� ������ StoreView=============
			if(event.getSource()== refreshBtn) {
				tableSync();
				clearTextFields();
			}
	    	//=============Back Button=============
			if (event.getSource() == backBtn) {
	           MainFX.mainStage.setTitle("Car Rental Window");
	           MainFX.mainStage.setScene(MainFX.mainScene);
	        }
	        //=============���������� ���� ������������ Store=============
			if (event.getSource() == newStoreBtn) {
                String storename = storenameField.getText();
                String cityname = citynameField.getText();

                int counter=0;

                for(City tempcity: cityList) {

                    if(cityname.equals(tempcity.getCity_name())) {//=============������� ��� ��� ��������� ��� �� ����������� city ������� ��� ��� �������.=============

                        counter++;//=============Counter ��� ��������� +1 ���� ������ �� ����������� ��� �������.=============

                    }

                }

                if(counter!=0) {//=============�� � ���� ��������� ��� ������� ���� ���������� �� �����������=============
                    if(!storename.equals("")) {//=============������� ��� ��� ��������� ��� �� textfield ����� ����.=============
                        createStore(storename, cityname);
                        tableSync();
                        clearTextFields();
                    } else {//=============�� �� TextField ����� ���� ������ ��������� ������=============
                        storeTableView.getItems().clear();
                        storeTableView.setPlaceholder(new Label("Store name must not be empty"));
                        clearTextFields();
                    }

                } else {//=============�� ��� ��������� ��������� ������=============
                    storeTableView.getItems().clear();
                    storeTableView.setPlaceholder(new Label("City must already be in the system"));
                    clearTextFields();
                }
                clearTextFields();
            }
		   //=============������� ���� ������������ ��� ��� ������ ��� ����� ��� ����� ��� ��� TextField=============
	       if (event.getSource() == storeTableView) {
	            Store selectedStore = storeTableView.getSelectionModel().getSelectedItem();
	            if (selectedStore != null) {
	                storenameField.setText(selectedStore.getStore_name());
	                citynameField.setText(selectedStore.getCity_name());
	                tempcode=selectedStore.getCode();
	            }
	        }
	        //=============��������� �� ����������� ��� ����������.=============
	       if (event.getSource() == updateStoreBtn) {
	            String storename = storenameField.getText();
	            String cityname = citynameField.getText();
	            
	            updateStore(storename, cityname);

	            tableSync();
	            clearTextFields();
	        }
	      
	       //=============�������� ���� ������������ ��� �� �������.=============
	       if (event.getSource() == deleteStoreBtn) {
	            deleteStore(storenameField.getText());

	            tableSync();
	            clearTextFields();
	       }
	       
	       
	       
	       //=============��������� ������������ ���� ��� �������� �����=============
	       if (event.getSource() == searchStoreBtn) {
               if(searchstoreField.getText().equals("")) {//=============�� ����� ���� �� TextField �� ������� ��� �� �������� ��� ������=============
                   tableSync();
                   clearTextFields();
               } else {//=============������ ������ ��� �� ����������� �� �� ����� ��� �����=============
            SearchStore(searchstoreField.getText());

               }
	       }
	}      
	
	       

	 //=============������� ���������� ��� ������������ ���� ��� �������� ��� �����=============
	private void SearchStore(String userCity){
        List<Store> items = storeTableView.getItems();
        storeTableView.getItems().clear();
        for (Store d : storeList) {
            if((d.getCity_name()).equals(userCity)) {//=============�� �� ����������� �������� ���� ���� �� �������=============
                items.add(d);
                clearTextFields();
            }
            else {//=============�� ��� ������� ����������� �� �� ����� ��� ����� �� ������� ��� ������ ���� ���� ������=============
            	storeTableView.getItems().clear();
                storeTableView.setPlaceholder(new Label("You must enter an already existing city"));
                clearTextFields();
            }
       }
	}
	

    //=============������� ���������� ��� ������������=============
	private void updateStore(String storename, String cityname) {
		for (Store d : storeList) {
			if (d.getCode()==tempcode) {
            	d.setStore_name(storename);
            	tempcode=-1;
            }
        }
	}

    //=============������� ��������� ��� ������������.=============
	public void deleteStore(String storename) {
	           for (int i = 0; i < storeList.size(); i++) {
	               if (storeList.get(i).getCode()==tempcode) {
	                   storeList.remove(i);
	                   break;
	               }
	           }
}
//=============������� ��� ���������� �� ����������� ��� �� ���������=============
	private void createStore(String storename, String cityname) {
        Store d = new Store(storename , cityname);
        storeList.add(d);
    }


	
	
	@Override
    Scene createScene() {
        return new Scene(rootGridPane, width, height);

    }
	
    //=============������� ��� ������� ��� ������ ����������� ����� �������������/������������/����������� ��� ����������� ��� �������=============
	private void tableSync() {
        List<Store> items = storeTableView.getItems();
        items.clear();
        for (Store d : storeList) {
            items.add((Store) d);
        }
    }
	
	
    //=============������� ���������� ��� TextField=============
    public void clearTextFields() {
            storenameField.setText("");
            citynameField.setText("");
            searchstoreField.setText("");

        }
 }