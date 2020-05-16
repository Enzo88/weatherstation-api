package eu.vinmasterpiece.weatherstation.service;

import eu.vinmasterpiece.weatherstation.model.WeatherData;
import eu.vinmasterpiece.weatherstation.model.WeatherDataDay;
import eu.vinmasterpiece.weatherstation.model.entity.WeatherDataEntity;

import java.util.Date;
import java.util.List;

public interface WeatherService {

    public WeatherDataEntity addWeatherData(WeatherData weatherData);

    public WeatherData getCurrentWeather();

    public WeatherData getWeather(Date date);

    public List<WeatherDataDay> getAllDayWeatherData(Date date);

    public List<WeatherDataDay> getWeathersInInterval(Date startDate, Date endDate);
}
