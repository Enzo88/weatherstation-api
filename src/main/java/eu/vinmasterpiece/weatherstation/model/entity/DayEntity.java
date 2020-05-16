package eu.vinmasterpiece.weatherstation.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "day", schema = "public", catalog = "weather")
public class DayEntity {
    private int id;
    private Date date;
    private double minTemp;
    private double maxTemp;
    private double minHum;
    private double maxHum;
    private double minPressure;
    private double maxPressure;
    private List<WeatherDataEntity> weathersData;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "min_temp")
    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    @Basic
    @Column(name = "max_temp")
    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    @Basic
    @Column(name = "min_hum")
    public double getMinHum() {
        return minHum;
    }

    public void setMinHum(double minHum) {
        this.minHum = minHum;
    }

    @Basic
    @Column(name = "max_hum")
    public double getMaxHum() {
        return maxHum;
    }

    public void setMaxHum(double maxHum) {
        this.maxHum = maxHum;
    }

    @Basic
    @Column(name = "min_pressure")
    public double getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(double minPressure) {
        this.minPressure = minPressure;
    }

    @Basic
    @Column(name = "max_pressure")
    public double getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(double maxPressure) {
        this.maxPressure = maxPressure;
    }

    @OneToMany(mappedBy="day", fetch = FetchType.LAZY)
    public List<WeatherDataEntity> getWeathersData() {
        return weathersData;
    }

    public void setWeathersData(List<WeatherDataEntity> weathersData) {
        this.weathersData = weathersData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayEntity dayEntity = (DayEntity) o;
        return id == dayEntity.id &&
                Double.compare(dayEntity.minTemp, minTemp) == 0 &&
                Double.compare(dayEntity.maxTemp, maxTemp) == 0 &&
                Double.compare(dayEntity.minHum, minHum) == 0 &&
                Double.compare(dayEntity.maxHum, maxHum) == 0 &&
                Double.compare(dayEntity.minPressure, minPressure) == 0 &&
                Double.compare(dayEntity.maxPressure, maxPressure) == 0 &&
                Objects.equals(date, dayEntity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, minTemp, maxTemp, minHum, maxHum, minPressure, maxPressure);
    }
}
