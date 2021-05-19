/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2021
 * Instructor: Prof. Chris Dancy
 *
 * Name: Michael Gertz
 * Section: 02
 * Date: 5/19/2021
 * Time: 1:38 PM
 *
 * Project: csci205SP21finalproject
 * Package: main
 * Class: upgrade
 *
 * Description: THIS IS A DESCRIPTION Y’ALL AND I NEED TO CHANGE THIS!
 *
 * ****************************************
 */
package main;

import javafx.beans.property.SimpleBooleanProperty;

public class Upgrade{
    private Producer producer;
    private String name;
    private SimpleBooleanProperty purchasedGainX2;
    private SimpleBooleanProperty purchasedGainX8;
    private SimpleBooleanProperty purchasedPeriodX4;
    private SimpleBooleanProperty purchasedPeriodX16;

    /**
     * Creates an Upgrade object that is linked to a specific producer.
     * @param name The name of the upgrade. This should note which producer the upgrades will apply to.
     * @param producer The producer that the upgrades will apply to.
     * @author Michael Gertz
     */
    public Upgrade(String name, Producer producer) {
        this.producer = producer;
        this.name = name;
        this.purchasedGainX2 = new SimpleBooleanProperty(false);
        this.purchasedGainX8 = new SimpleBooleanProperty(false);
        this.purchasedPeriodX4 = new SimpleBooleanProperty(false);
        this.purchasedPeriodX16 = new SimpleBooleanProperty(false);
    }




    public double getCurrentGainMult(){
        return this.producer.getGainMult();
    }

    public double getCurrentPeriodMult(){
        return this.producer.getPeriodMult();
    }

    /**
     * Sets the values of the gain multiplier for the producer,
     * @param newGainMult A double that represents the multiplier for DNA produced
     * @author Michael Gertz
     */
    public void onGainPurchase(double newGainMult){
        this.producer.setGainMult(newGainMult);
    }

    /**
     * Sets the values of the period multiplier for the producer,
     * @param newPeriodMult A double that represents the multiplier for how quickly DNA is produced
     * @author Michael Gertz
     */
    public void onPeriodPurchase(double newPeriodMult){
        this.producer.setPeriodMult(newPeriodMult);
    }

    /**
     * Multiplies the current gain multiplier for the producer by 2, then sets the boolean PurchasedGainX2 to true
     * @return The boolean value of PurchasedGainX2, True if successful, false is unsuccessful
     * @author Michael Gertz
     */
    public boolean upgradeGainX2(){
        double currentGainMult = getCurrentGainMult();
        double newGainMult = currentGainMult * 2;
        onGainPurchase(newGainMult);
        this.purchasedGainX2.set(true);
        return this.purchasedGainX2.get();
    }

    /**
     * Multiplies the current gain multiplier for the producer by 2, then sets the boolean PurchasedGainX8 to true
     * @return The boolean value of PurchasedGainX8, True if successful, false is unsuccessful
     * @author Michael Gertz
     */
    public boolean upgradeGainX8(){
        double currentGainMult = getCurrentGainMult();
        double newGainMult = currentGainMult * 8;
        onGainPurchase(newGainMult);
        this.purchasedGainX8.set(true);
        return this.purchasedGainX2.get();
    }

    /**
     * Multiplies the current gain multiplier for the producer by 2, then sets the boolean PurchasedPeriodX4 to true
     * @return The boolean value of PurchasedPeriodX4, True if successful, false is unsuccessful
     * @author Michael Gertz
     */
    public boolean upgradePeriodX4(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 4;
        onPeriodPurchase(newPeriodMult);
        this.purchasedPeriodX4.set(true);
        return this.purchasedPeriodX4.get();
    }



    /**
     * Multiplies the current gain multiplier for the producer by 2, then sets the boolean PurchasedPeriodX16 to true
     * @return The boolean value of PurchasedPeriodX16, True if successful, false is unsuccessful
     * @author Michael Gertz
     */
    public boolean upgradePeriodX16(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 16;
        onPeriodPurchase(newPeriodMult);
        this.purchasedPeriodX16.set(true);
        return this.purchasedPeriodX16.get();
    }

    public boolean isPurchasedGainX2() {
        return purchasedGainX2.get();
    }

    public SimpleBooleanProperty purchasedGainX2Property() {
        return purchasedGainX2;
    }

    public boolean isPurchasedGainX8() {
        return purchasedGainX8.get();
    }

    public SimpleBooleanProperty purchasedGainX8Property() {
        return purchasedGainX8;
    }

    public boolean isPurchasedPeriodX4() {
        return purchasedPeriodX4.get();
    }

    public SimpleBooleanProperty purchasedPeriodX4Property() {
        return purchasedPeriodX4;
    }

    public boolean isPurchasedPeriodX16() {
        return purchasedPeriodX16.get();
    }

    public SimpleBooleanProperty purchasedPeriodX16Property() {
        return purchasedPeriodX16;
    }
}