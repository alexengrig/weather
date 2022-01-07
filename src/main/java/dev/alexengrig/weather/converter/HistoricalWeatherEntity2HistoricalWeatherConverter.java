package dev.alexengrig.weather.converter;

import dev.alexengrig.weather.domain.HistoricalWeather;
import dev.alexengrig.weather.entity.HistoricalWeatherEntity;
import org.springframework.stereotype.Component;

@Component
public class HistoricalWeatherEntity2HistoricalWeatherConverter
        extends NotNullConverter<HistoricalWeatherEntity, HistoricalWeather> {

    @Override
    HistoricalWeather doConvert(HistoricalWeatherEntity source) {
        return HistoricalWeather.builder()
                .createdAt(source.getCreatedAt())
                .description(source.getDescription())
                .build();
    }

}
