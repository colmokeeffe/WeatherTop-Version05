package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("dashboard id = " + id);
        if(station.readings.size()!= 0) {
            station.maxTempVal = StationAnalytics.getMaxTemp(station.readings).temperature;
            station.minTempVal = StationAnalytics.getMinTemp(station.readings).temperature;
            station.maxWindSpeedVal = StationAnalytics.getMaxWindSpeed(station.readings).windSpeed;
            station.minWindSpeedVal = StationAnalytics.getMinWindSpeed(station.readings).windSpeed;
            station.maxPressureVal = StationAnalytics.getMaxPressure(station.readings).pressure;
            station.minPressureVal = StationAnalytics.getMinPressure(station.readings).pressure;
            render("station.html",station);
        }
        else station.maxTempVal =0;
        station.minTempVal =0;
        station.maxWindSpeedVal =0;
        station.minWindSpeedVal=0;
        station.maxPressureVal =0;
        station.minPressureVal =0;
        render("station.html", station);
    }

    public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure, Date date)
    {   date = new Date();
        Reading reading = new Reading(code,temperature,windSpeed,windDirection,pressure, date);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }

    public static void deleteReading (Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing" + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
    }


}