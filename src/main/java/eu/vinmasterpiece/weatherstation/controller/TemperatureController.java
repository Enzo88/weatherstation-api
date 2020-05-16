package eu.vinmasterpiece.weatherstation.controller;

import eu.vinmasterpiece.weatherstation.service.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Log
@Tag(name = "Temperature data")
@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    TemperatureService service;

    @Operation(summary = "Get current temperature",
            description = "Get current temperature",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/current", produces = "application/json")
    public ResponseEntity<Double> getCurrentTemperature() {
        Double temp = service.getCurrentTemperature();
        return temp != null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today max temperature",
            description = "Get today max temperature",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/maxtoday", produces = "application/json")
    public ResponseEntity<Double> getMaxTemperatureToday() {
        Double temp = service.getMaxTemperatureToday();
        return temp != null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today min temperature",
            description = "Get today min temperature",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/mintoday", produces = "application/json")
    public ResponseEntity<Double> getMinTemperatureToday() {
        Double temp = service.getMinTemperatureToday();
        return temp != null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get max temperature in day",
            description = "Get max temperature for a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/maxday", produces = "application/json")
    public ResponseEntity<Double> getMaxTemperatureDay(@Parameter(description = "Date", required = true) @RequestParam() Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double temp = service.getMaxTemperatureInDay(date);
        return temp != null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get min temperature in day",
            description = "Get min temperature in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/minday", produces = "application/json")
    public ResponseEntity<Double> getMinTemperatureDay(@Parameter(description = "Date", required = true) @RequestParam() Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double temp = service.getMinTemperatureInDay(date);
        return temp != null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

}
