package eu.vinmasterpiece.weatherstation.controller;

import eu.vinmasterpiece.weatherstation.service.PressureService;
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
@Tag(name = "Pressure data")
@RestController
@RequestMapping("/pressure")
public class PressureController {

    @Autowired
    PressureService service;

    @Operation(summary = "Get current Pressure",
            description = "Get current Pressure",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/current", produces = "application/json")
    public ResponseEntity<Double> getCurrentPressure() {
        Double pressure = service.getCurrentPressure();
        return service != null ? ResponseEntity.ok(pressure) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today max Pressure",
            description = "Get today max Pressure",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/maxtoday", produces = "application/json")
    public ResponseEntity<Double> getMaxPressureToday() {
        Double pressure = service.getMaxPressureToday();
        return service != null ? ResponseEntity.ok(pressure) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get today min Pressure",
            description = "Get today min Pressure",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404")
            })
    @GetMapping(value = "/mintoday", produces = "application/json")
    public ResponseEntity<Double> getMinPressureToday() {
        Double pressure = service.getMinPressureToday();
        return service != null ? ResponseEntity.ok(pressure) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get max Pressure day",
            description = "Get max Pressure in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/maxday", produces = "application/json")
    public ResponseEntity<Double> getMaxPressureDay(@Parameter(description = "Date") @RequestParam("date") Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double pressure = service.getMaxPressureDay(date);
        return service != null ? ResponseEntity.ok(pressure) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get min Pressure day",
            description = "Get min Pressure in a specific day",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Error retrieving data", responseCode = "500"),
                    @ApiResponse(description = "No data found", responseCode = "404"),
                    @ApiResponse(description = "Bad request", responseCode = "400")
            })
    @GetMapping(value = "/minday", produces = "application/json")
    public ResponseEntity<Double> getMinPressureDay(@Parameter(description = "Date") @RequestParam("date") Date date) {
        if (date == null) return ResponseEntity.badRequest().build();
        Double pressure = service.getMinPressureDay(date);
        return service != null ? ResponseEntity.ok(pressure) : ResponseEntity.notFound().build();
    }
}
