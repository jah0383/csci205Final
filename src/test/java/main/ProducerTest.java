package main;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerTest {

    private static final double EPSILON = 1.0E-12;
    private Producer testProducer;

    @BeforeEach
    void setup(){
       testProducer = new Producer("Test P",100,100,2,1.5, Color.LIGHTGREEN);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void getDnaPerSecond() {
        testProducer.setInitialDisplay();
        testProducer.buy((long) 100);
        assertEquals(testProducer.getDnaPerSecond(), 50, EPSILON);
        testProducer.buy((long) 200);
        assertEquals(testProducer.getDnaPerSecond(), 100, EPSILON);
    }

    @Test
    void buy() {
        long cost = 100;
        long costFailed = -1;
        assertEquals(testProducer.buy((long) 90), costFailed, EPSILON);         //Tests buying with insufficient DNA, return -1
        assertEquals(testProducer.buy((long) 100), cost, EPSILON);              //Tests buying producer for first time with Exact DNA, returns 100
        assertEquals(testProducer.buy((long) 200), cost * 1.5, EPSILON);  //Tests buying producer for second time with extra DNA, returns 150
    }
}