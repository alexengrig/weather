package dev.alexengrig.weather.converter;

import dev.alexengrig.weather.domain.CurrentWeather;
import dev.alexengrig.weather.entity.HistoricalWeatherEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CurrentWeather2HistoricalWeatherEntityConverter
        extends NotNullConverter<CurrentWeather, HistoricalWeatherEntity> {

    @Override
    HistoricalWeatherEntity doConvert(CurrentWeather source) {
        return HistoricalWeatherEntity.builder()
                .createdAt(LocalDateTime.now())
                .description(source.getDescription())
                .build();
    }

}
