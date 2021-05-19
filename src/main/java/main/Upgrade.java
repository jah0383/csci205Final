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

    public void onPurchace(double newGainMult, double newPeriodMult){
        if(newGainMult != 0){
            this.producer.setGainMult(newGainMult);
        }
        if(newPeriodMult != 0) {
            this.producer.setPeriodMult(newPeriodMult);
        }
    }

    public boolean upgradeGainX2(){
        double currentGainMult = getCurrentGainMult();
        double newGainMult = currentGainMult * 2;
        onPurchace(newGainMult, 0);
        this.purchasedGainX2.set(true);
        return this.purchasedGainX2.get();
    }

    public boolean upgradeGainX8(){
        double currentGainMult = getCurrentGainMult();
        double newGainMult = currentGainMult * 8;
        onPurchace(newGainMult, 0);
        this.purchasedGainX8.set(true);
        return this.purchasedGainX2.get();
    }

    public boolean upgradePeriodX4(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 4;
        onPurchace(0, newPeriodMult);
        this.purchasedPeriodX4.set(true);
        return this.purchasedPeriodX4.get();
    }



    public boolean upgradePeriodX16(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 16;
        onPurchace(0, newPeriodMult);
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