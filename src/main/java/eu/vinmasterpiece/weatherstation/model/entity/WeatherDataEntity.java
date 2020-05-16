package eu.vinmasterpiece.weatherstation.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "weather_data", schema = "public", catalog = "weather")
public class WeatherDataEntity {
    private int id;
    private Timestamp date;
    private double temperature;
    private double humidity;
    private double pressure;
    private DayEntity day;

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
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "temperature")
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "humidity")
    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Basic
    @Column(name = "pressure")
    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @ManyToOne
    @JoinColumn(name="day", nullable=false)
    public DayEntity getDay() {
        return day;
    }

    public void setDay(DayEntity day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDataEntity that = (WeatherDataEntity) o;
        return id == that.id &&
                Double.compare(that.temperature, temperature) == 0 &&
                Double.compare(that.humidity, humidity) == 0 &&
                Double.compare(that.pressure, pressure) == 0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, temperature, humidity, pressure);
    }
}
