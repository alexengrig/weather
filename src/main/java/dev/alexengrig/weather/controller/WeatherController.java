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

package dev.alexengrig.weather.controller;

import dev.alexengrig.weather.domain.CurrentWeather;
import dev.alexengrig.weather.payload.CurrentWeatherResponse;
import dev.alexengrig.weather.service.CurrentWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final CurrentWeatherService service;
    private final Converter<CurrentWeather, CurrentWeatherResponse> converter;

    @GetMapping("/current")
    public ResponseEntity<CurrentWeatherResponse> currentByCityName(@RequestParam("cityName") String name) {
        CurrentWeather currentWeather = service.getByCityName(name);
        CurrentWeatherResponse body = converter.convert(currentWeather);
        return ResponseEntity.ok(body);
    }

}
