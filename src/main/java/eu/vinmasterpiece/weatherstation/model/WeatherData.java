package eu.vinmasterpiece.weatherstation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherData {
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
