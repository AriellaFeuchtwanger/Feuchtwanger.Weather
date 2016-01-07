package feuchtwanger.feuchtwangerweather;

public class CurrentWeather {
    //Array of weather objects and a main object
    private City city;
    private WeatherList[] list;

    public double getTempMin(int day) {
        return list[day].getMin();
    }

    public double getTempMax(int day) {
        return list[day].getMax();
    }

    public String getIcon(int day) {
        return list[day].getIcon();
    }

    public String getDescription(int day) {
        return list[day].getDescription();
    }

    public String getMain(int day) {
        return list[day].getMain();
    }

    public String getCountry() {
        return city.getCountry();
    }

    public String getName() {
        return city.getName();
    }
}
