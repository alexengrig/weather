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

package dev.alexengrig.weather.converter;

import dev.alexengrig.weather.domain.CurrentWeather;
import dev.alexengrig.weather.payload.OpenWeatherMapResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OpenWeatherMapResponse2CurrentWeatherConverter
        extends NotNullConverter<OpenWeatherMapResponse, CurrentWeather>
        implements Converter<OpenWeatherMapResponse, CurrentWeather> {

    @Override
    CurrentWeather doConvert(OpenWeatherMapResponse source) {
        String description = source.getWeather().stream()
                .map(OpenWeatherMapResponse.Weather::getDescription)
                .collect(Collectors.joining(", "));
        return CurrentWeather.builder()
                .description(description)
                .build();
    }

}
