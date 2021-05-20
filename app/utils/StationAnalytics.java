package utils;

import models.Reading;
import models.Station;


import java.util.List;

public class StationAnalytics {


    public static Reading getMinTemp(List<Reading> readings) {
        Reading minTemp = null;
           if (readings.size() > 0) {
            minTemp = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minTemp.temperature) {
                    minTemp = reading;
                }
            }
        }
        return minTemp;
    }

    public static Reading getMaxTemp(List<Reading> readings) {
        Reading maxTemp = null;
        if (readings.size() > 0) {
            maxTemp = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxTemp.temperature) {
                    maxTemp = reading;
                }
            }
        }
        return maxTemp;
    }

    public static Reading getMinWindSpeed(List<Reading> readings) {
        Reading minWind = null;
        if (readings.size() > 0) {
            minWind = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minWind.windSpeed) {
                    minWind = reading;
                }
            }
        }
        return minWind;
    }

    public static Reading getMaxWindSpeed(List<Reading> readings) {
        Reading maxWind = null;
        if (readings.size() > 0) {
            maxWind = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWind.windSpeed) {
                    maxWind = reading;
                }
            }
        }
        return maxWind;
    }

    public static Reading getMinPressure(List<Reading> readings) {
        Reading minPressure = null;
        if (readings.size() > 0) {
            minPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minPressure.pressure) {
                    minPressure = reading;
                }
            }
        }
        return minPressure;
    }

    public static Reading getMaxPressure(List<Reading> readings) {
        Reading maxPressure = null;
        if (readings.size() > 0) {
            maxPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure.pressure) {
                    maxPressure = reading;
                }
            }
        }
        return maxPressure;
    }

}
