package eu.vinmasterpiece.weatherstation.service;

import eu.vinmasterpiece.weatherstation.model.WeatherData;
import eu.vinmasterpiece.weatherstation.model.WeatherDataDay;
import eu.vinmasterpiece.weatherstation.model.entity.DayEntity;
import eu.vinmasterpiece.weatherstation.model.entity.WeatherDataEntity;
import eu.vinmasterpiece.weatherstation.repository.DayRepository;
import eu.vinmasterpiece.weatherstation.repository.WeatherDataRepository;
import eu.vinmasterpiece.weatherstation.util.DateUtility;
import eu.vinmasterpiece.weatherstation.util.WeatherDataAdapter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Autowired
    DayRepository dayRepository;

    @Override
    public WeatherDataEntity addWeatherData(WeatherData weatherData) {
        try {
            DayEntity dayEntity = dayRepository.findByDate(DateUtility.getTodaySQLDateWithoutTime());
            WeatherDataEntity weatherDataEntity = null;
            if (dayEntity != null) {
                updateMaxAndMin(dayEntity, weatherData);
            } else {
                dayEntity = buildDayEntity(weatherData);
                dayRepository.save(dayEntity);
            }
            weatherDataEntity = getEntityfromWeatherData(weatherData);
            weatherDataEntity.setDay(dayEntity);
            weatherDataRepository.saveAndFlush(weatherDataEntity);
            weatherDataEntity.setDay(null);
            return weatherDataEntity;
        } catch (Exception e) {
            log.error("Error while save weather data", e);
            return null;
        }
    }

    @Override
    public WeatherData getCurrentWeather() {
        try {
            return new WeatherDataAdapter(weatherDataRepository.getMaxWeatherData());
        } catch (Exception e) {
            log.error("Error while save weather data", e);
            return null;
        }
    }

    @Override
    public WeatherData getWeather(Date date) {
        return null;
    }

    @Override
    public List<WeatherDataDay> getAllDayWeatherData(Date date) {
        return null;
    }

    @Override
    public List<WeatherDataDay> getWeathersInInterval(Date startDate, Date endDate) {
        return null;
    }

    private void updateMaxAndMin(@NotNull DayEntity dayEntity, @NotNull WeatherData weatherData) {
        if (weatherData.getTemperature() != null) {
            dayEntity.setMaxTemp(NumberUtils.max(dayEntity.getMaxTemp(), weatherData.getTemperature()));
            dayEntity.setMinTemp(NumberUtils.min(dayEntity.getMinTemp(), weatherData.getTemperature()));
        }

        if (weatherData.getPressure() != null) {
            dayEntity.setMaxPressure(NumberUtils.max(dayEntity.getMaxPressure(), weatherData.getPressure()));
            dayEntity.setMinPressure(NumberUtils.min(dayEntity.getMinPressure(), weatherData.getPressure()));
        }

        if (weatherData.getHumidity() != null) {
            dayEntity.setMaxHum(NumberUtils.max(dayEntity.getMaxHum(), weatherData.getHumidity()));
            dayEntity.setMinHum(NumberUtils.min(dayEntity.getMinHum(), weatherData.getHumidity()));
        }

        dayRepository.save(dayEntity);
    }

    private DayEntity buildDayEntity(@NotNull WeatherData weatherData) {
        DayEntity dayEntity = new DayEntity();
        dayEntity.setDate(DateUtility.getTodaySQLDateWithoutTime());
        dayEntity.setMinTemp(weatherData.getTemperature());
        dayEntity.setMaxTemp(weatherData.getTemperature());
        dayEntity.setMinHum(weatherData.getHumidity());
        dayEntity.setMaxHum(weatherData.getHumidity());
        dayEntity.setMinPressure(weatherData.getPressure());
        dayEntity.setMaxPressure(weatherData.getPressure());

        return dayEntity;
    }

    private WeatherDataEntity getEntityfromWeatherData(@NotNull WeatherData data) {
        WeatherDataEntity entity = new WeatherDataEntity();
        entity.setDate(Timestamp.from(Instant.now()));
        Optional.ofNullable(data.getTemperature()).ifPresentOrElse(entity::setTemperature, () -> entity.setTemperature(0));
        Optional.ofNullable(data.getHumidity()).ifPresentOrElse(entity::setHumidity, () -> entity.setHumidity(0));
        Optional.ofNullable(data.getPressure()).ifPresentOrElse(entity::setPressure, () -> entity.setPressure(0));
        return entity;
    }
}
