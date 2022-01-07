package dev.alexengrig.weather.service;

import dev.alexengrig.weather.domain.HistoricalWeather;
import dev.alexengrig.weather.entity.HistoricalWeatherEntity;
import dev.alexengrig.weather.repository.HistoricalWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleHistoricalWeatherService implements HistoricalWeatherService {

    private final HistoricalWeatherRepository repository;
    private final ConversionService conversionService;

    @Override
    public Page<HistoricalWeather> get(Pageable pageable) {
        Page<HistoricalWeatherEntity> page = repository.findAll(pageable);
        List<HistoricalWeather> content = page.getContent().stream()
                .map(o -> conversionService.convert(o, HistoricalWeather.class))
                .toList();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

}
