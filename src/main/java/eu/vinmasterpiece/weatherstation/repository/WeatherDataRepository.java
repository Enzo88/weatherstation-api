package eu.vinmasterpiece.weatherstation.repository;

import eu.vinmasterpiece.weatherstation.model.WeatherData;
import eu.vinmasterpiece.weatherstation.model.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, Integer> {

    @Query("select a from WeatherDataEntity a where a.id = (select max(b.id) from WeatherDataEntity b)")
    public WeatherDataEntity getMaxWeatherData();
}
