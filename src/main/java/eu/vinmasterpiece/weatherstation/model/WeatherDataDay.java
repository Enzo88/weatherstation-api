package eu.vinmasterpiece.weatherstation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDataDay extends WeatherData {
    Date date;
}
