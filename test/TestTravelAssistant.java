import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestTravelAssistant {

    TravelAssistant assistant;

    @Before
    public void setup() {
        assistant = new TravelAssistant();
    }

    @Test
    public void test() {
        assistant.addCity("A", true, 10, 100);
        assistant.addCity("B", true, 10, 100);
        assistant.addCity("C", true, 10, 100);

        assistant.addFlight("A", "B", 10, 1);
        assistant.addFlight("A", "C", 10, 300);
        assistant.addTrain("B", "C", 10, 1);

        List<String> output = assistant.planTrip("A", "C",  true, 1,0,0);

        Assert.assertEquals("Expected output length", 3, output.size());
        Assert.assertEquals("Starting should be", "start A", output.get(0));
        Assert.assertEquals("Expected output length", "fly B", output.get(1));
        Assert.assertEquals("Expected output length", "train C", output.get(2));
    }

    @Test
    public void test2() {
        assistant.addCity("A", true, 10, 100);
        assistant.addCity("B", true, 10, 100);
        assistant.addCity("C", true, 10, 100);

        assistant.addFlight("A", "B", 10, 1);
        assistant.addFlight("A", "C", 10, 300);
        assistant.addTrain("B", "C", 100000, 1);

        List<String> output = assistant.planTrip("A", "C",  true, 1,1,0);

        Assert.assertEquals("Expected output length", 2, output.size());
        Assert.assertEquals("Starting should be", "start A", output.get(0));
        Assert.assertEquals("Expected output length", "fly C", output.get(1));
    }
}
