/*
 * Copyright 2021 Alexengrig Dev.
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

import dev.alexengrig.weather.client.OpenWeatherMapClient;
import dev.alexengrig.weather.payload.OpenWeatherMapResponse;
import dev.alexengrig.weather.payload.WeatherRequest;
import dev.alexengrig.weather.payload.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class ClientWeatherService implements WeatherService {

    private final OpenWeatherMapClient client;

    @Override
    public WeatherResponse getNow(WeatherRequest request) {
        OpenWeatherMapResponse response;
        if (request.getCityId() != null) {
            response = client.weatherByCityId(request.getCityId());
        } else if (request.getCityName() != null) {
            response = client.weatherByCityName(request.getCityName());
        } else {
            throw new IllegalArgumentException("Invalid request");
        }
        String description = response.getWeather().stream()
                .map(OpenWeatherMapResponse.Weather::getDescription)
                .collect(Collectors.joining(", "));
        return WeatherResponse.builder()
                .description(description)
                .build();
    }

}
