package dev.alexengrig.weather.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HistoricalWeather {

    private LocalDateTime createdAt;
    private String description;

}
