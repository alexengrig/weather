package dev.alexengrig.weather.service;

import dev.alexengrig.weather.domain.HistoricalWeather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoricalWeatherService {

    Page<HistoricalWeather> get(Pageable pageable);

}
