package eu.vinmasterpiece.weatherstation.controller;

import eu.vinmasterpiece.weatherstation.model.WeatherData;
import eu.vinmasterpiece.weatherstation.model.WeatherDataDay;
import eu.vinmasterpiece.weatherstation.model.entity.WeatherDataEntity;
import eu.vinmasterpiece.weatherstation.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log
@Tag(name = "Weather operations")
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService service;

    @Operation(summary = "Add new weather data",
            description = "Add weather data with current data/time",
            responses = {
                    @ApiResponse(description = "Data inserted successfully", responseCode = "200"),
                    @ApiResponse(description = "Error while inserting data", responseCode = "500"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WeatherDataEntity> addWeather(@RequestBody(description = "Current weather data", required = true) @org.springframework.web.bind.annotation.RequestBody WeatherData weatherData) {
        if(weatherData == null || (weatherData.getHumidity() == null && weatherData.getPressure() == null && weatherData.getTemperature() == null)) return ResponseEntity.badRequest().build();
        WeatherDataEntity weatherDataEntity = service.addWeatherData(weatherData);
        return weatherDataEntity != null ? ResponseEntity.ok(weatherDataEntity) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Operation(summary = "Get current weather data",
            description = "Get current weather data",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/current", produces = "application/json")
    public ResponseEntity<WeatherData> getCurrentWeather() {
        WeatherData current = service.getCurrentWeather();
        return current != null ? ResponseEntity.ok(current) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get weather",
            description = "Get current in a specific moment",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/weather", produces = "application/json")
    public ResponseEntity<WeatherData> getWeather(@Parameter(description = "Date and time of needed weather data", required = true) @RequestParam("timestamp") Date date) {
        if(date == null) return ResponseEntity.badRequest().build();
        WeatherData weatherData = service.getWeather(date);
        return weatherData != null ? ResponseEntity.ok(weatherData) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get weather for a day",
            description = "Get current in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/weatherinday", produces = "application/json")
    public ResponseEntity<List<WeatherDataDay>> getAllDayWeather(@Parameter(description = "Date of needed weather data", required = true) @RequestParam("date") Date date) {
        if(date == null) return ResponseEntity.badRequest().build();
        List<WeatherDataDay> weatherData = service.getAllDayWeatherData(date);
        return weatherData != null && !weatherData.isEmpty() ? ResponseEntity.ok(weatherData) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get weather for a specific interval",
            description = "Get current in a specific interval",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/weatherinterval", produces = "application/json")
    public ResponseEntity<List<WeatherDataDay>> getWeathersInterval(@Parameter(description = "Start date of needed weather data", required = true) @RequestParam("startDate") Date startDate,
                                                                    @Parameter(description = "End date of needed weather data", required = true) @RequestParam("startDate") Date endDate) {
        if(startDate == null || endDate == null) return ResponseEntity.badRequest().build();
        List<WeatherDataDay> weatherData = service.getWeathersInInterval(startDate, endDate);
        return weatherData != null && !weatherData.isEmpty() ? ResponseEntity.ok(weatherData) : ResponseEntity.notFound().build();
    }


}
