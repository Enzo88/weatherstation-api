package eu.vinmasterpiece.weatherstation.service;

import java.util.Date;

public interface TemperatureService {

    public Double getCurrentTemperature();

    public Double getMaxTemperatureToday();

    public Double getMinTemperatureToday();

    public Double getMaxTemperatureInDay(Date date);

    public Double getMinTemperatureInDay(Date date);

}
