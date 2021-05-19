/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mhick
 */
public class order {

    int orderNum;
    
    int total;
    String[] foodItems;
    int[] foodPrices;
   //keeps a list of items in the order
    List<item> orderItems = new ArrayList<item>();
   
    String orderName;
    String destination;
    
    dineInOrder dineIn;
    deliveryOrder delivery;

    /**
     *
     * @param orderNum-integer
     * @param name-String
     * @param total-integer
     * 
     * creates an order instance with the order number, name, and total of the meal
     */
    public order(int orderNum, String name, int total) {

        this.orderNum = orderNum;
        this.orderName = name;
        this.total = total;

    }

//setters

    /**
     *
     * @param total-integer
     * sets the total price of the order
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     *
     * @param orderNum-integer
     * sets the orders number to be able to identify the order from others
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     *
     * @param orderName-string
     * set the name of the order for the person who ordered it
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
// overload these methods

    /**
     *
     * @return-String
     * where the order needs to go
     */
    public String destination() {

        return destination;
    }

    /**
     *
     * @param Destination-string
     * sets where the order needs to go
     */
    public void setDestination(String Destination) {
        this.destination = Destination;
    }

//getters

    /**
     *
     * @return-integer order number
     * gives the unique number for this order
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     *
     * @return-integer total
     * gives you the total price of the order
     */
    public int getTotal() {

        return total;
    }

    /**
     *
     * @return-String orderName
     * gets the orders name 
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     *
     * @return-String
     * gives you where the order needs to go
     */
    public String getDestination() {
        return destination;
    }

    //add all the foods to a list 
    //but when you add a food just change the total

    /**
     *
     * @param itemIn -item
     * Adds item into a list in order to know what food has been added to this order
     *  
     * 
     */
    public void addItem(item itemIn) {
        orderItems.add(itemIn);
    }

    /**
     *
     * @return- List
     * gets a list of the items in the order
     * 
     */
    public List<item> getOrder() {
        return orderItems;
    }

}
