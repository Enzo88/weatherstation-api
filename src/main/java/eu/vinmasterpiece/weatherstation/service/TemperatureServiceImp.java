package eu.vinmasterpiece.weatherstation.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TemperatureServiceImp implements TemperatureService {
    @Override
    public Double getCurrentTemperature() {
        return null;
    }

    @Override
    public Double getMaxTemperatureToday() {
        return null;
    }

    @Override
    public Double getMinTemperatureToday() {
        return null;
    }

    @Override
    public Double getMaxTemperatureInDay(Date date) {
        return null;
    }

    @Override
    public Double getMinTemperatureInDay(Date date) {
        return null;
    }
}
