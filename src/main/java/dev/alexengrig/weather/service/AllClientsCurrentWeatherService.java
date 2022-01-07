/*
 * Copyright 2021-2022 Alexengrig Dev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.alexengrig.weather.service;

import dev.alexengrig.weather.client.CurrentWeatherClient;
import dev.alexengrig.weather.domain.CurrentWeather;
import dev.alexengrig.weather.entity.HistoricalWeatherEntity;
import dev.alexengrig.weather.repository.HistoricalWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class AllClientsCurrentWeatherService implements CurrentWeatherService {

    private final List<CurrentWeatherClient<?>> clients;
    private final ConversionService conversionService;
    private final HistoricalWeatherRepository repository;

    @Override
    public CurrentWeather getByCityName(String name) {
        String description = clients.stream()
                .map(client -> client.getByCityName(name))
                .map(o -> conversionService.convert(o, CurrentWeather.class))
                .map(CurrentWeather::getDescription)
                .collect(Collectors.joining(", "));
        CurrentWeather currentWeather = CurrentWeather.builder()
                .description(description)
                .build();
        HistoricalWeatherEntity entity = conversionService.convert(currentWeather, HistoricalWeatherEntity.class);
        repository.save(entity);
        return currentWeather;
    }

}
