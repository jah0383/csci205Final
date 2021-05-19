package main;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeTest {

    private static final double EPSILON = 1.0E-12;
    private Producer testProducer;
    private Upgrade testUpgrade;

    @BeforeEach
    void setUp(){
        testProducer = new Producer("Test P",100,100,2,1.5, Color.LIGHTGREEN);
        testUpgrade = new Upgrade("Test P Upgrades", testProducer);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void upgradeGainX2() {
        assertFalse(testUpgrade.isPurchasedGainX2());
        assertEquals(1, testUpgrade.getCurrentGainMult(), EPSILON);
        assertTrue(testUpgrade.upgradeGainX2());
        assertEquals(2, testUpgrade.getCurrentGainMult(), EPSILON);
        assertTrue(testUpgrade.isPurchasedGainX2());
        testUpgrade.upgradeGainX8();
        assertEquals(16, testUpgrade.getCurrentGainMult(), EPSILON);
    }

    @Test
    void upgradeGainX8() {
        assertFalse(testUpgrade.isPurchasedGainX8());
        assertEquals(1, testUpgrade.getCurrentGainMult(), EPSILON);
        assertTrue(testUpgrade.upgradeGainX8());
        assertEquals(8, testUpgrade.getCurrentGainMult(), EPSILON);
        assertTrue(testUpgrade.isPurchasedGainX8());
        testUpgrade.upgradeGainX2();
        assertEquals(16, testUpgrade.getCurrentGainMult(), EPSILON);
    }

    @Test
    void upgradePeriodX4() {
        assertFalse(testUpgrade.isPurchasedPeriodX4());
        assertEquals(1, testUpgrade.getCurrentPeriodMult(), EPSILON);
        assertTrue(testUpgrade.upgradePeriodX4());
        assertEquals(0.25, testUpgrade.getCurrentPeriodMult(), EPSILON);
        assertTrue(testUpgrade.isPurchasedPeriodX4());
        testUpgrade.upgradePeriodX16();
        assertEquals(0.015625, testUpgrade.getCurrentPeriodMult(), EPSILON);
    }

    @Test
    void upgradePeriodX16() {
        assertFalse(testUpgrade.isPurchasedPeriodX16());
        assertEquals(1, testUpgrade.getCurrentPeriodMult(), EPSILON);
        assertTrue(testUpgrade.upgradePeriodX16());
        assertEquals(0.0625, testUpgrade.getCurrentPeriodMult(), EPSILON);
        assertTrue(testUpgrade.isPurchasedPeriodX16());
        testUpgrade.upgradePeriodX4();
        assertEquals(0.015625, testUpgrade.getCurrentPeriodMult(), EPSILON);
    }
}