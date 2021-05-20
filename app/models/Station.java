package models;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import play.db.jpa.Model;
import static java.util.Comparator.comparing;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

@Entity
public class Station extends Model {
  public String name;
  public float lat;
  public float lng;
  public Date date;
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  public int windDirection;
  public int minTemp;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public double windChill;
  public double maxTempVal;
  public double minTempVal;
  public double maxWindSpeedVal;
  public double minWindSpeedVal;
  public int maxPressureVal;
  public int minPressureVal;


  public Station(String name, float lat, float lng, Date date)
  {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    this.date=date;
  }

  public String getName(){
    return name;
  }

  public float getLat(){
    return lat;
  }

  public float getLng(){
    return lng;
  }

  public static String codeToString(int code) {
    switch (code) {
      case 100:
        return "Clear";
      case 200:
        return "Partial Clouds";
      case 300:
        return "Cloudy";
      case 400:
        return "Light Showers";
      case 500:
        return "Heavy Showers";
      case 600:
        return "Rain";
      case 700:
        return "Snow";
      case 800:
        return "Thunder";
      default:
        return "Unable to Determine Current Weather";
    }
  }

  public static String codeToIcon(int code) {
    switch (code) {
      case 100:
        return "sun icon";
      case 200:
        return "cloud sun icon";
      case 300:
        return "Cloud icon";
      case 400:
        return "cloud sun rain icon";
      case 500:
        return "cloud showers heavy icon";
      case 600:
        return "cloud rain icon";
      case 700:
        return "snowflake icon";
      case 800:
        return "bolt icon";
      default:
        return "red question circle icon";
    }
  }

  public double celsiusToFahrenheit(double temperature) {
    if (temperature != 0) {
      return (temperature * 9 / 5) + 32;
    } else return 0;
  }

  public static int speedToBeaufort(double windSpeed) {
    if (windSpeed >= 1 && windSpeed <= 5) {
      return 1;
    } else if (windSpeed >= 6 && windSpeed <= 11) {
      return 2;
    } else if (windSpeed >= 12 && windSpeed <= 19) {
      return 3;
    } else if (windSpeed >= 20 && windSpeed <= 28) {
      return 4;
    } else if (windSpeed >= 29 && windSpeed <= 38) {
      return 5;
    } else if (windSpeed >= 39 && windSpeed <= 49) {
      return 6;
    } else if (windSpeed >= 50 && windSpeed <= 61) {
      return 7;
    } else if (windSpeed >= 62 && windSpeed <= 74) {
      return 8;
    } else if (windSpeed >= 75 && windSpeed <= 88) {
      return 9;
    } else if (windSpeed >= 89 && windSpeed <= 102) {
      return 10;
    } else if (windSpeed >= 103 && windSpeed <= 117) {
      return 11;
    } else return 0;
  }

  public static String windToString(int windDirection) {

    if (windDirection >= 11.25 && windDirection < 33.75) {
      return "North North East";
    } else if (windDirection >= 33.75 && windDirection < 56.25) {
      return "North East";
    } else if (windDirection >= 56.25 && windDirection < 78.75) {
      return "East North East";
    } else if (windDirection >= 78.75 && windDirection < 101.25) {
      return "East";
    } else if (windDirection >= 101.25 && windDirection < 123.75) {
      return "East South East";
    } else if (windDirection >= 123.75 && windDirection < 146.25) {
      return "South East";
    } else if (windDirection >= 146.25 && windDirection < 168.75) {
      return "South South East";
    } else if (windDirection >= 168.75 && windDirection < 191.25) {
      return "South";
    } else if (windDirection >= 191.25 && windDirection < 213.75) {
      return "South South West";
    } else if (windDirection >= 213.75 && windDirection < 236.25) {
      return "South West";
    } else if (windDirection >= 236.25 && windDirection < 258.75) {
      return "West South West";
    } else if (windDirection >= 258.75 && windDirection < 281.25) {
      return "West";
    } else if (windDirection >= 281.25 && windDirection < 303.75) {
      return "West North West";
    } else if (windDirection >= 303.75 && windDirection < 326.25) {
      return "North West";
    } else if (windDirection >= 326.25 && windDirection < 348.75) {
      return "North North West";
    } else return "North";
  }

  public String feelsLike(double temperature, double windSpeed) {

    windChill = 13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temperature * Math.pow(windSpeed, 0.16);
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(windChill);
  }
}


