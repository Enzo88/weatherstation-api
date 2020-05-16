package eu.vinmasterpiece.weatherstation.service;

import java.util.Date;

public interface PressureService {

    public Double getCurrentPressure();

    public Double getMaxPressureToday();

    public Double getMinPressureToday();

    public Double getMaxPressureDay(Date date);

    public Double getMinPressureDay(Date date);

}
