/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpos;

/**
 *
 * @author mhick
 */
public class dineInOrder extends order {

    private String destination;

    /**
     *
     * @param orderNum
     * @param name
     * @param total
     * @param destination
     */
    public dineInOrder(int orderNum, String name, int total, String destination) {
        super(orderNum, name, total);
        this.destination = destination;

    }

    /**
     *
     * @return-String
     * gives you the Table the order should go to
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     * sets the table the order should go to
     */
    @Override
    public void setDestination(String destination) {
        this.destination =  destination;
    }

}
