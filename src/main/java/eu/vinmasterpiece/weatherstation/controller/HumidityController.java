package eu.vinmasterpiece.weatherstation.controller;

import eu.vinmasterpiece.weatherstation.service.HumidityService;
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
@Tag(name = "Humidity data")
@RestController
@RequestMapping("/humidity")
public class HumidityController {

    @Autowired
    HumidityService service;

    @Operation(summary = "Get current humidity",
            description = "Get current humidity",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/current", produces = "application/json")
    public ResponseEntity<Double> getCurrentHumidity() {
        Double humidity = service.getCurrentHumidity();
        return humidity != null ? ResponseEntity.ok(humidity) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today max humidity",
            description = "Get today max humidity",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/maxtoday", produces = "application/json")
    public ResponseEntity<Double> getMaxHumidityToday() {
        Double humidity = service.getMaxHumidityToday();
        return humidity != null ? ResponseEntity.ok(humidity) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today min humidity",
            description = "Get today min humidity",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/mintoday", produces = "application/json")
    public ResponseEntity<Double> getMinHumidityToday() {
        Double humidity = service.getMinHumidityToday();
        return humidity != null ? ResponseEntity.ok(humidity) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get max humidity day",
            description = "Get max humidity in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/maxday", produces = "application/json")
    public ResponseEntity<Double> getMaxHumidityDay(@Parameter(description = "Date", required = true) @RequestParam("date") Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double humidity = service.getMaxHumidityDay(date);
        return humidity != null ? ResponseEntity.ok(humidity) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get min humidity day",
            description = "Get min humidity in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/minday", produces = "application/json")
    public ResponseEntity<Double> getMinHumidityDay(@Parameter(description = "Date", required = true) @RequestParam("date") Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double humidity = service.getMinHumidityDay(date);
        return humidity != null ? ResponseEntity.ok(humidity) : ResponseEntity.notFound().build();
    }

}
