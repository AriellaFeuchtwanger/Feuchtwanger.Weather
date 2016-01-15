package feuchtwanger.feuchtwangerweather;

/**
 * Created by Ariella on 1/12/2016.
 */
public class TodayWeather {
    private Main main;
    private Weather[] weather;
    private Wind wind;
    private String name;

    public double getTemp(){
        return main.getTemp();
    }

    public double getTempMin(){
        return main.getTempMin();
    }

    public double getTempMax(){
        return main.getTempMax();
    }

    public String getIcon(){
        return weather[0].getIcon();
    }

    public String getMain(){
        return weather[0].getMain();
    }

    public double getPressure(){return main.getPressure();}

    public double getHumidity(){return main.getHumidity();}

    public double getWindSpeed(){return  wind.getSpeed();}

    public double getWindDegree(){return wind.getDeg();}

    public String getName(){return name;}
}
