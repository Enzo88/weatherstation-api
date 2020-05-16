package eu.vinmasterpiece.weatherstation.service;

import java.util.Date;

public interface HumidityService {

    public Double getCurrentHumidity();

    public Double getMaxHumidityToday();

    public Double getMinHumidityToday();

    public Double getMaxHumidityDay(Date date);

    public Double getMinHumidityDay(Date date);

}
