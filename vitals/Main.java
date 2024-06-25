package vitals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
    
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        Map<String, Boolean> tempResults = checkMeasure("Temperature", temperature, 0, 45);
        Map<String, Boolean> socResults = checkMeasure("State of Charge", soc, 20, 80);
        Map<String, Boolean> chargeResults = checkMeasure("Charge Rate", chargeRate, 0, 0.8f);
        return tempResults.get("status") && socResults.get("status") && chargeResults.get("status");
    }

    static Map<String, Boolean> checkMeasure(String measureName, float measureValue, float lowerLimit, float upperLimit){
        Map<String, Boolean> results = new HashMap<>();
        boolean status = true;
        if (measureValue < lowerLimit) {
            System.out.println(measureName + " is too low!");
            status = false;
        } else if (measureValue > upperLimit) {
            System.out.println(measureName + " is too high!");
            status = false;
        }
        results.put("status", status);
        return results;
    }

    public static void main(String[] args) {
        // Test cases
        assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(50, 85, 0.0f) == false);
        assert(batteryIsOk(-1, 70, 0.7f) == false);
        assert(batteryIsOk(25, 10, 0.7f) == false);
        assert(batteryIsOk(25, 70, 0.9f) == false);
        System.out.println("Some more tests needed");
    }
}
