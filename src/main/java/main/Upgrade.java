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

public class Upgrade{
    private Producer producer;
    private String name;
    private boolean purchasedGainX2;
    private boolean purchasedGainX8;
    private boolean purchasedPeriodX4;
    private boolean purchasedPeriodX16;

    public Upgrade(String name, Producer producer) {
        this.producer = producer;
        this.name = name;
        this.purchasedGainX2 = false;
        this.purchasedGainX8 = false;
        this.purchasedPeriodX4 = false;
        this.purchasedPeriodX16 = false;
    }

    public boolean isPurchasedGainX2() {
        return purchasedGainX2;
    }

    public void setPurchasedGainX2(boolean purchasedGainX2) {
        this.purchasedGainX2 = purchasedGainX2;
    }

    public boolean isPurchasedGainX8() {
        return purchasedGainX8;
    }

    public void setPurchasedGainX8(boolean purchasedGainX8) {
        this.purchasedGainX8 = purchasedGainX8;
    }

    public boolean isPurchasedPeriodX4() {
        return purchasedPeriodX4;
    }

    public void setPurchasedPeriodX4(boolean purchasedPeriodX4) {
        this.purchasedPeriodX4 = purchasedPeriodX4;
    }

    public boolean isPurchasedPeriodX16() {
        return purchasedPeriodX16;
    }

    public void setPurchasedPeriodX16(boolean purchasedPeriodX16) {
        this.purchasedPeriodX16 = purchasedPeriodX16;
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
        setPurchasedGainX2(true);
        return isPurchasedGainX2();
    }

    public boolean upgradeGainX8(){
        double currentGainMult = getCurrentGainMult();
        double newGainMult = currentGainMult * 8;
        onPurchace(newGainMult, 0);
        setPurchasedGainX8(true);
        return isPurchasedGainX8();
    }

    public boolean upgradePeriodX4(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 4;
        onPurchace(0, newPeriodMult);
        setPurchasedPeriodX4(true);
        return isPurchasedPeriodX4();
    }

    public boolean upgradePeriodX16(){
        double currentPeriodMult = getCurrentPeriodMult();
        double newPeriodMult = currentPeriodMult / 16;
        onPurchace(0, newPeriodMult);
        setPurchasedPeriodX16(true);
        return isPurchasedPeriodX16();
    }
}