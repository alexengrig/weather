package dev.alexengrig.weather.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HistoricalWeatherResponse {

    private LocalDateTime createdAt;
    private String description;

}
