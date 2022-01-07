package dev.alexengrig.weather.converter;

import dev.alexengrig.weather.domain.HistoricalWeather;
import dev.alexengrig.weather.payload.HistoricalWeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class HistoricalWeather2HistoricalWeatherResponseConverter
        extends NotNullConverter<HistoricalWeather, HistoricalWeatherResponse> {

    @Override
    HistoricalWeatherResponse doConvert(HistoricalWeather source) {
        return HistoricalWeatherResponse.builder()
                .createdAt(source.getCreatedAt())
                .description(source.getDescription())
                .build();
    }

}
