package eu.vinmasterpiece.weatherstation.repository;

import eu.vinmasterpiece.weatherstation.model.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface DayRepository extends JpaRepository<DayEntity, Integer> {
    DayEntity findByDate(Date date);
}
