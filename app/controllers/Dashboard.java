package controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    Collections.sort(stations, Comparator.comparing(Station::getName));
    render("dashboard.html", member, stations);
  }

  public static void addStation (String name, float lat, float lng)
  {
    Logger.info ("Adding a new station called " + name);
    Member member = Accounts.getLoggedInMember();
    Station station= new Station (name,lat,lng);
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