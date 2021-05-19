/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author mhick
 */
public class FXMLDocumentController implements Initializable {
//the current order

    int orderCount = 0;
//order lists
    ObservableList<dineInOrder> dineInOrderList = FXCollections.observableArrayList();
    ObservableList<deliveryOrder> deliveryOrderList = FXCollections.observableArrayList();

    // choice box list
    ObservableList<String> pullDownList = FXCollections.observableArrayList("Order Number", "Order Name", "Table", "Address", "Total");

//buttons

    @FXML
    private Button modDeliveryButton;

    @FXML
    private Button modDineInButton;

    @FXML
    private Button newDineInOrderButton;

    @FXML
    private Button newDeliveryOrderButton;

    @FXML
    private Button deleteButton;

    //delivery table veiw and columns
    @FXML
    private TableView<deliveryOrder> deliveryTableView;

    @FXML
    private TableColumn<deliveryOrder, Integer> DelOrderNumCol;
    @FXML
    private TableColumn<deliveryOrder, Integer> AddressCol;
    @FXML
    private TableColumn<deliveryOrder, String> DelNameCol;
    @FXML
    private TableColumn<deliveryOrder, Integer> DelTotalCol;

    //Dine in table view and columns
    @FXML
    private TableView<dineInOrder> dineInTableView;
    @FXML
    private TableColumn<dineInOrder, Integer> orderNumCol;
    @FXML
    private TableColumn<dineInOrder, Integer> TableCol;
    @FXML
    private TableColumn<dineInOrder, String> nameCol;
    @FXML
    private TableColumn<dineInOrder, Integer> totalCol;


    @FXML
    private void newDineInOrder(ActionEvent event) throws IOException {
        orderCount++;
        dineInOrder order = new dineInOrder(orderCount, "", 0, "");

        dineInOrderList.add(order);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyOrderScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //acces the controller and call a method
        ModifyOrderScreenController controller = loader.getController();
        //giving the list to the other scene so it can give it back

        controller.getListOrder(dineInOrderList, deliveryOrderList, order, orderCount);
        controller.displayOrder();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setMaximized(true);
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void newDeliveryOrder(ActionEvent event) throws IOException {
        orderCount++;
        deliveryOrder order = new deliveryOrder(orderCount, "", 0, "");

        deliveryOrderList.add(order);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("deliveryModifyScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //acces the controller and call a method
        DeliveryModifyScreenController controller = loader.getController();
        //giving the list to the other scene so it can give it back

        controller.getListOrder(dineInOrderList, deliveryOrderList, order, orderCount);
        controller.displayOrder();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setMaximized(true);
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void modifyDeliveryOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("deliveryModifyScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //acces the controller and call a method
        DeliveryModifyScreenController controller = loader.getController();
        //giving the list to the other scene so it can give it back

        controller.getListOrder(dineInOrderList, deliveryOrderList, deliveryTableView.getSelectionModel().getSelectedItem(), orderCount);
        controller.displayOrder();

        // controller.giveOrder(allOrders.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setMaximized(true);
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void modifyDineInOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyOrderScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //acces the controller and call a method
        ModifyOrderScreenController controller = loader.getController();
        //giving the list to the other scene so it can give it back

        controller.getListOrder(dineInOrderList, deliveryOrderList, dineInTableView.getSelectionModel().getSelectedItem(), orderCount);
        controller.displayOrder();

        // controller.giveOrder(allOrders.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setMaximized(true);
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     *
     * @param event
     */
    @FXML
    public void deleteOrder(ActionEvent event) {
//removes selected order from the list
        if (deliveryTableView.getSelectionModel().isEmpty()) {
            dineInOrderList.remove(dineInTableView.getSelectionModel().getSelectedItem());
            //clears table
            dineInTableView.getItems().clear();
//resdisplays the tableView with correct list
            dineInTableView.getItems().addAll(dineInOrderList);
        } else {
            deliveryOrderList.remove(deliveryTableView.getSelectionModel().getSelectedItem());
            //clears table
            deliveryTableView.getItems().clear();
//resdisplays the tableView with correct list
            deliveryTableView.getItems().addAll(deliveryOrderList);
        }
    }

    /**
     *
     * @param dineInList
     * @param deliveryList
     * @param orderNumber gets two types of lists the dineInLists and
     * deliveryList, the dine In order thats currently selected, and the current
     * order number then sets table views with the lists received
     */
    public void returnList(ObservableList<dineInOrder> dineInList, ObservableList<deliveryOrder> deliveryList, int orderNumber) {
        //returning the lists
        dineInOrderList = dineInList;
        deliveryOrderList = deliveryList;
//setting the table views
       dineInTableView.getItems().addAll(dineInOrderList);
     
        deliveryTableView.getItems().addAll(deliveryOrderList);
        
        orderCount = orderNumber;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //sets up columns for dine in table
        orderNumCol.setCellValueFactory(new PropertyValueFactory<dineInOrder, Integer>("orderNum"));
        TableCol.setCellValueFactory(new PropertyValueFactory<dineInOrder, Integer>("destination"));
        nameCol.setCellValueFactory(new PropertyValueFactory<dineInOrder, String>("orderName"));
        totalCol.setCellValueFactory(new PropertyValueFactory<dineInOrder, Integer>("total"));
        //sets up columns for delivery table
        DelOrderNumCol.setCellValueFactory(new PropertyValueFactory<deliveryOrder, Integer>("orderNum"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<deliveryOrder, Integer>("destination"));
        DelNameCol.setCellValueFactory(new PropertyValueFactory<deliveryOrder, String>("orderName"));
        DelTotalCol.setCellValueFactory(new PropertyValueFactory<deliveryOrder, Integer>("total"));

        modDeliveryButton.disableProperty().bind(Bindings.isEmpty(deliveryTableView.getSelectionModel().getSelectedItems()));
        modDineInButton.disableProperty().bind(Bindings.isEmpty(dineInTableView.getSelectionModel().getSelectedItems()));
       


    }
}
