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

package dev.alexengrig.weather.controller;

import dev.alexengrig.weather.payload.WeatherRequest;
import dev.alexengrig.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService service;

    @GetMapping("/now")
    public ResponseEntity<Object> now(
            @RequestParam(name = "cityId", required = false) String cityId,
            @RequestParam(name = "cityName", required = false) String cityName) {
        if (Objects.isNull(cityId) && Objects.isNull(cityName)) {
            return ResponseEntity.badRequest().body("pass cityId or cityName");
        }
        WeatherRequest request = WeatherRequest.builder()
                .cityName(cityId)
                .cityName(cityName)
                .build();
        return ResponseEntity.ok(service.getNow(request));
    }

}
