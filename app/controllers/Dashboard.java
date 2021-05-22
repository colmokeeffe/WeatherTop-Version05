package controllers;
import java.util.*;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class Dashboard extends Controller
{


  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    Collections.sort(stations, Comparator.comparing(Station::getName,String.CASE_INSENSITIVE_ORDER));
    for(Station station:stations){
      if(station.readings.size()!= 0) {
    station.maxTempVal = StationAnalytics.getMaxTemp(station.readings).temperature;
    station.minTempVal = StationAnalytics.getMinTemp(station.readings).temperature;
    station.maxWindSpeedVal = StationAnalytics.getMaxWindSpeed(station.readings).windSpeed;
    station.minWindSpeedVal = StationAnalytics.getMinWindSpeed(station.readings).windSpeed;
    station.maxPressureVal = StationAnalytics.getMaxPressure(station.readings).pressure;
    station.minPressureVal = StationAnalytics.getMinPressure(station.readings).pressure;
    render("dashboard.html", member, stations);
  }}}

  public static void addStation (String name, float lat, float lng, Date date)
  {
    Logger.info ("Adding a new station called " + name);
    Member member = Accounts.getLoggedInMember();
    Station station= new Station (name,lat,lng,date);
    member.stations.add(station);
    member.save();
    redirect ("/dashboard");
  }

  public static void deleteStation (Long id)
  {
    Logger.info ("Removing station" );
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }
}