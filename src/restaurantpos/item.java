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
public class item {

    int price;
    String food;

    /**
     *
     * @param foodItem
     * @param priceOf
     */
    public item(String foodItem, int priceOf) {
        price = priceOf;
        food = foodItem;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getFood() {
        return food;
    }

    /**
     *
     * @param food
     */
    public void setFood(String food) {
        this.food = food;
    }

    public String toString() {
        String toString;

        toString = food + " "+ price;
        return toString;
    }
}
