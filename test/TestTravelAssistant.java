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

        assistant.addFlight("A", "B", 10, 100);
        assistant.addFlight("A", "C", 10, 500);
        assistant.addTrain("B", "C", 10, 100);

        List<String> output = assistant.planTrip("A", "C",  true, 1,1,1);
        for (String s: output) {
            System.out.println(s);
        }
    }
}
