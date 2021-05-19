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
public class DeliveryModifyScreenController implements Initializable {

    private deliveryOrder selectedDeliveryOrder;

    private int orderCount;

    //menu
    //food
    private item hamburger;
    private item hotDog;
    //drink
    private item coke;

    //orderLists
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
//address fields

    @FXML
    private TextField streetNumField;
    @FXML
    private TextField streetNameField;
    @FXML
    private TextField apartmentField;
    @FXML
    private TextField zipField;

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

//get the list,order, and currentorder number 
    /**
     *
     * @param dineInList-ObservableLis
     * @param deliveryList-ObservableList
     * @param order-deliveryList
     * @param orderNumber-Integer gets two types of lists the dineInLists and
     * deliveryList, the deliveryorder thats currently selected, and the current
     * order number
     */
    public void getListOrder(ObservableList<dineInOrder> dineInList, ObservableList<deliveryOrder> deliveryList, deliveryOrder order, int orderNumber) {
//getting lists
        dineInOrderList = dineInList;
        deliveryOrderList = deliveryList;
//changing order to a dine in order to be able to get correct destination
        selectedDeliveryOrder = order;
        orderCount = orderNumber;
    }
//action event to add food to the order and display it to listview

    /**
     * adds burger item to the DeliveryOrder
     */
    public void addBurger() {
        //hamburger is 5 dollars
        hamburger = new item("Hamgburger", 5);
        selectedDeliveryOrder.addItem(hamburger);
        //displaying the hamburger to the table view
        orderTableView.getItems().add(hamburger);
        //set the orders total price
        selectedDeliveryOrder.setTotal(selectedDeliveryOrder.getTotal() + hamburger.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDeliveryOrder.getTotal()) + "$");

    }

    /**
     * adds hot dog item to the DeliveryOrder
     */
    public void addHotDog() {
        hotDog = new item("Hot Dog", 2);
        selectedDeliveryOrder.addItem(hotDog);
        //displaying the hotdog to the table view
        orderTableView.getItems().add(hotDog);
        //set the orders total price
        selectedDeliveryOrder.setTotal(selectedDeliveryOrder.getTotal() + hotDog.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDeliveryOrder.getTotal()) + "$");
    }

    /**
     * adds coke item to the DeliveryOrder
     */
    public void addCoke() {
        coke = new item("Coke", 1);
        selectedDeliveryOrder.addItem(coke);
        //displaying the hotdog to the table view
        orderTableView.getItems().add(coke);
        //set the orders total price
        selectedDeliveryOrder.setTotal(selectedDeliveryOrder.getTotal() + coke.price);
        //display the Text total
        totalPriceText.setText("Total:" + Integer.toString(selectedDeliveryOrder.getTotal()) + "$");

    }

    //display the order in table view and the orderName/orderTable
    /**
     * displays the orders items in the table view and sets the text fields to
     * the name and address
     */
    public void displayOrder() {
//        food.getItems().addAll(selectedOrder.getOrder());
        if (selectedDeliveryOrder.getOrder() != null) {
            //adding 
            orderTableView.getItems().addAll(selectedDeliveryOrder.getOrder());
            orderNameField.setText(selectedDeliveryOrder.getOrderName());

            streetNumField.setText(selectedDeliveryOrder.getStreetNum());
            streetNameField.setText(selectedDeliveryOrder.getStreetName());
            apartmentField.setText(selectedDeliveryOrder.getApartNum());
            zipField.setText(selectedDeliveryOrder.getZipCode());

        }
    }

    /**
     * sets the delivery orders name and destination then displays the
     * FXMLDocument file. it then returns the lists that were passed to this
     * controller back to the original one
     */
    @FXML
    private void finishOrder(ActionEvent event) throws IOException {
        selectedDeliveryOrder.setOrderName(orderNameField.getText());
        //setting the address methods
        selectedDeliveryOrder.setStreetNum(streetNumField.getText());
        selectedDeliveryOrder.setStreetName(streetNameField.getText());
        selectedDeliveryOrder.setApartNum(apartmentField.getText());
        selectedDeliveryOrder.setZipCode(zipField.getText());
        //setting the destination 
        selectedDeliveryOrder.setDestination(selectedDeliveryOrder.getDestination());

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
     * @param url-url
     * @param rb-ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        priceCol.setCellValueFactory(new PropertyValueFactory<item, Integer>("price"));
        itemCol.setCellValueFactory(new PropertyValueFactory<item, String>("food"));
       
        if (orderNameField.getText().matches("")) {
            done.setDisable(true);
            orderFieldsInvalid.setText("Enter a valid input for street number and zip code.");
        }
        
        //adding textField listener

        ChangeListener<String> addressListener = ((observable, oldValue, newValue) -> {
            if (streetNumField.getText().matches("[0-9]+") && zipField.getText().matches("[0-9]+") && orderNameField.getText().matches("[a-zA-Z]+")) {
                orderFieldsInvalid.setText(null);
                done.setDisable(false);
            } else {
                orderFieldsInvalid.setText("Enter a valid input for order name, street number and zip code.");
                done.setDisable(true);
            }
        });

        // streetNumField streetNameField apartmentField zipField
        orderNameField.textProperty().addListener(addressListener);
        //
        streetNumField.textProperty().addListener(addressListener);
        zipField.textProperty().addListener(addressListener);
    }

}
