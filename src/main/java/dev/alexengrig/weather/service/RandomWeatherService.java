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

import dev.alexengrig.weather.payload.WeatherRequest;
import dev.alexengrig.weather.payload.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class RandomWeatherService implements WeatherService {

    private final RandomGenerator randomGenerator;
    private final List<String> descriptions;

    @Override
    public WeatherResponse getNow(WeatherRequest ignore) {
        String randomDescription = descriptions.get(randomGenerator.nextInt(descriptions.size()));
        return WeatherResponse.builder()
                .description(randomDescription)
                .build();
    }

}
