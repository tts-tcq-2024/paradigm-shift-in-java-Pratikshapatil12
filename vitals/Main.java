package vitals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {

    // Define global variables for language support
    private static String language = "EN"; // "DE" for German

    public static void main(String[] args) {
        // Test cases
        assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(50, 85, 0.0f) == false);
        assert(batteryIsOk(-1, 70, 0.7f) == false);
        assert(batteryIsOk(25, 10, 0.7f) == false);
        assert(batteryIsOk(25, 70, 0.9f) == false);
        System.out.println("Some more tests needed");
    }

    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        Map<String, Boolean> tempResults = checkMeasure("Temperature", temperature, 0, 45, 5);
        Map<String, Boolean> socResults = checkMeasure("State of Charge", soc, 20, 80, 4);
        Map<String, Boolean> chargeResults = checkMeasure("Charge Rate", chargeRate, 0, 0.8f, 0.04f);

        return tempResults.get("status") && socResults.get("status") && chargeResults.get("status");
    }

    static Map<String, Boolean> checkMeasure(String measureName, float measureValue, float lowerLimit, float upperLimit, float warningTolerance) {
        Map<String, Boolean> results = new HashMap<>();
        boolean status = true;

        float warningLowerLimit = lowerLimit + warningTolerance;
        float warningUpperLimit = upperLimit - warningTolerance;

        if (measureValue < lowerLimit) {
            printMessage(measureName, "too low", false);
            status = false;
        } else if (measureValue > upperLimit) {
            printMessage(measureName, "too high", false);
            status = false;
        } else if (measureValue < warningLowerLimit) {
            printMessage(measureName, "approaching discharge", true);
        } else if (measureValue > warningUpperLimit) {
            printMessage(measureName, "approaching charge-peak", true);
        }
        
        results.put("status", status);
        return results;
    }

    static void printMessage(String measureName, String condition, boolean isWarning) {
        String message = getMessage(measureName, condition, isWarning);
        System.out.println(message);
    }

    static String getMessage(String measureName, String condition, boolean isWarning) {
        if (language.equals("DE")) {
            switch (measureName) {
                case "Temperature":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "Temperatur nähert sich Entladung";
                            case "approaching charge-peak" -> "Temperatur nähert sich Ladehöhe";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "Temperatur ist zu niedrig!";
                            case "too high" -> "Temperatur ist zu hoch!";
                            default -> "";
                        };
                    }
                case "State of Charge":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "SoC nähert sich Entladung";
                            case "approaching charge-peak" -> "SoC nähert sich Ladehöhe";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "SoC ist zu niedrig!";
                            case "too high" -> "SoC ist zu hoch!";
                            default -> "";
                        };
                    }
                case "Charge Rate":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "Ladegeschwindigkeit nähert sich Entladung";
                            case "approaching charge-peak" -> "Ladegeschwindigkeit nähert sich Ladehöhe";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "Ladegeschwindigkeit ist zu niedrig!";
                            case "too high" -> "Ladegeschwindigkeit ist zu hoch!";
                            default -> "";
                        };
                    }
                default:
                    return "";
            }
        } else {
            switch (measureName) {
                case "Temperature":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "Temperature approaching discharge";
                            case "approaching charge-peak" -> "Temperature approaching charge-peak";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "Temperature is too low!";
                            case "too high" -> "Temperature is too high!";
                            default -> "";
                        };
                    }
                case "State of Charge":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "SoC approaching discharge";
                            case "approaching charge-peak" -> "SoC approaching charge-peak";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "SoC is too low!";
                            case "too high" -> "SoC is too high!";
                            default -> "";
                        };
                    }
                case "Charge Rate":
                    if (isWarning) {
                        return switch (condition) {
                            case "approaching discharge" -> "Charge rate approaching discharge";
                            case "approaching charge-peak" -> "Charge rate approaching charge-peak";
                            default -> "";
                        };
                    } else {
                        return switch (condition) {
                            case "too low" -> "Charge rate is too low!";
                            case "too high" -> "Charge rate is too high!";
                            default -> "";
                        };
                    }
                default:
                    return "";
            }
        }
    }
}
