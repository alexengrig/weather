package dev.alexengrig.weather.repository;

import dev.alexengrig.weather.entity.HistoricalWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalWeatherRepository extends JpaRepository<HistoricalWeatherEntity, Long> {
}
