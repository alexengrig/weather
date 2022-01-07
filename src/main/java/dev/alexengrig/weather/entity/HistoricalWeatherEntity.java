package dev.alexengrig.weather.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalWeatherEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdAt;

    private String description;

}
