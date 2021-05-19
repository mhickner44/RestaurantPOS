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
public class deliveryOrder extends order {

    private String destination;
    private String streetNum="";
    private String streetName="";
    private String apartNum="";
    private String zipCode="";

    /**
     *
     * @param orderNum-Integer
     * @param name-String
     * @param total-Integer
     * @param destination-String
     * 
     */
    public deliveryOrder(int orderNum, String name, int total, String destination) {
        super(orderNum, name, total);
        this.destination = destination;

    }

    /**
     *
     * @return-String
     *gets  the address of where the order needs to go
     */
    @Override
    public String getDestination() {
        destination = streetNum + " " + streetName + " " + apartNum + " " + zipCode;
        return destination;
    }

    /**
     *
     * @param destination
     * sets the address of where the order should go
     */
    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     *  String
     * String
     * gets the street number of the orders destination
     */
    public String getStreetNum() {
        return streetNum;
    }

    /**
     *
     * @param streetNum
     * sets the street number for the orders address
     */
    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    /**
     *
     * @return-String
     * gives you the street name  of the Destination String
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     *
     * @param streetName
     *sets the street name of  the Destination String
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     *
     * @return-String
     * gives you the apartment number of the Destination String
     */
    public String getApartNum() {
        return apartNum;
    }

    /**
     *
     * @param apartNum
     * sets the apartment number for use in the destination
     * 
     */
    public void setApartNum(String apartNum) {
        this.apartNum = apartNum;
    }

    /**
     *
     * @return-String
     * gets the zip code number for use in the destination
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     *
     * @param zipCode
     * sets the apartment number for use in the destination
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
