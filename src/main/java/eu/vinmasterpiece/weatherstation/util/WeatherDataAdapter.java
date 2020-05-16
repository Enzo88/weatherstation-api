package eu.vinmasterpiece.weatherstation.util;

import eu.vinmasterpiece.weatherstation.model.WeatherData;
import eu.vinmasterpiece.weatherstation.model.entity.WeatherDataEntity;

public class WeatherDataAdapter extends WeatherData {
    private WeatherDataEntity weatherDataEntity;

    public WeatherDataAdapter(WeatherDataEntity weatherDataEntity) {
        this.weatherDataEntity = weatherDataEntity;
    }

    @Override
    public Double getTemperature() {
        return weatherDataEntity.getTemperature();
    }

    @Override
    public Double getHumidity() {
        return weatherDataEntity.getHumidity();
    }

    @Override
    public Double getPressure() {
        return weatherDataEntity.getPressure();
    }
}
