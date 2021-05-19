/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhick
 */
public class ModifyOrderScreenController implements Initializable {
    
    private dineInOrder selectedDineIn;
    
    private int orderCount;
    
    private boolean negative = (true);

    //menu
    //food
    private item hamburger;
    private item hotDog;
    //drink
    private item coke;
    
    ObservableList<dineInOrder> dineInOrderList = FXCollections.observableArrayList();
    ObservableList<deliveryOrder> deliveryOrderList = FXCollections.observableArrayList();
    
    @FXML
    private Button done;

    //food buttons
    @FXML
    private Button burgerButton;
    
    @FXML
    private Button hotDogButton;
    //Drink buttons
    @FXML
    private Button cokeButton;

    //table number and order number text Fields/ error handling text
    @FXML
    private TextField orderNameField;
    
    @FXML
    private Text orderFieldsInvalid;
    
    @FXML
    private TextField orderTableField;
    
    @FXML
    private Text totalPriceText;

//    @FXML
//    private ListView food;
    @FXML
    private TableView<item> orderTableView;
    
    @FXML
    private TableColumn<item, Integer> priceCol;
    
    @FXML
    private TableColumn<item, String> itemCol;
    
    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Parent allOrders = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(allOrders);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }

//get the list,order, and currentorder number 
    /**
     *
     * @param dineInList
     * @param deliveryList
     * @param order
     * @param orderNumber
     *
     * gets two types of lists the dineInLists and deliveryList, the dine In
     * order thats currently selected, and the current order number
     */
    public void getListOrder(ObservableList<dineInOrder> dineInList, ObservableList<deliveryOrder> deliveryList, dineInOrder order, int orderNumber) {
//getting lists
        dineInOrderList = dineInList;
        deliveryOrderList = deliveryList;
//changing order to a dine in order to be able to get correct destination
        selectedDineIn = order;
        orderCount = orderNumber;
    }
//action event to add food to the order and display it to listview

    /**
     * adds burger item to the DineInOrder
     */
    public void addBurger() {
        //hamburger is 5 dollars
        hamburger = new item("Hamgburger", 5);
        selectedDineIn.addItem(hamburger);
        //displaying the hamburger to the table view
        orderTableView.getItems().add(hamburger);
        //set the orders total price
        selectedDineIn.setTotal(selectedDineIn.getTotal() + hamburger.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDineIn.getTotal()) + "$");
        
    }

    /**
     * adds hot dog item to the DineInOrder
     */
    public void addHotDog() {
        hotDog = new item("Hot Dog", 2);
        selectedDineIn.addItem(hotDog);
        //displaying the hotdog to the table view
        orderTableView.getItems().add(hotDog);
        //set the orders total price
        selectedDineIn.setTotal(selectedDineIn.getTotal() + hotDog.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDineIn.getTotal()) + "$");
    }

    /**
     * adds coke item to the DineInOrder
     */
    public void addCoke() {
        coke = new item("Coke", 1);
        selectedDineIn.addItem(coke);
        //displaying the hotdog to the table view
        orderTableView.getItems().add(coke);
        //set the orders total price
        selectedDineIn.setTotal(selectedDineIn.getTotal() + coke.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDineIn.getTotal()) + "$");
        
    }

    //display the order in table view and the orderName/orderTable
    /**
     * displays the orders items in the table view and sets the text fields to
     * the name and address
     */
    public void displayOrder() {
//        food.getItems().addAll(selectedOrder.getOrder());
        if (selectedDineIn.getOrder() != null) {
            //adding 
            orderTableView.getItems().addAll(selectedDineIn.getOrder());
            orderNameField.setText(selectedDineIn.getOrderName());
            orderTableField.setText(selectedDineIn.getDestination());
        }
    }

    /**
     * sets the delivery orders name and destination then displays the
     * FXMLDocument file. it then returns the lists that were passed to this
     * controller back to the original one
     */
    @FXML
    private void finishOrder(ActionEvent event) throws IOException {
        selectedDineIn.setOrderName(orderNameField.getText());
        selectedDineIn.setDestination(orderTableField.getText());

//         selectedOrder.setTable(Integer.parseInt(orderTableField.getText()));
//        
//        selectedOrder.setTable(Integer.parseInt(orderTableField.getText()));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);

        //acces the controller and call a method
        FXMLDocumentController controller = loader.getController();
        controller.returnList(dineInOrderList, deliveryOrderList, orderCount);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        window.setMaximized(true);
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        priceCol.setCellValueFactory(new PropertyValueFactory<item, Integer>("price"));
        itemCol.setCellValueFactory(new PropertyValueFactory<item, String>("food"));
        
        if (orderTableField.getText().matches("") && orderNameField.getText().matches("")) {
            done.setDisable(true);
            orderFieldsInvalid.setText("Enter a valid input for order name and a number for table");
        }

        //adding textField listener
        //and checks for wrong input
        ChangeListener<String> textFieldListener = ((observable, oldValue, newValue) -> {
            if ( orderNameField.getText().matches("[a-zA-Z]+") && orderTableField.getText().matches("[0-9]+")) {
                orderFieldsInvalid.setText(null);                
                done.setDisable(false);
            } else {
                orderFieldsInvalid.setText("Enter a valid input for order name and a number for table");
                done.setDisable(true);
            }
        });
        
        orderNameField.textProperty().addListener(textFieldListener);
        orderTableField.textProperty().addListener(textFieldListener);
    }
    
}
