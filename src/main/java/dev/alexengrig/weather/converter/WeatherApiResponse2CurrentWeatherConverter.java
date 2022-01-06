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
import dev.alexengrig.weather.payload.WeatherApiResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiResponse2CurrentWeatherConverter
        extends NotNullConverter<WeatherApiResponse, CurrentWeather>
        implements Converter<WeatherApiResponse, CurrentWeather> {

    @Override
    CurrentWeather doConvert(WeatherApiResponse source) {
        String description = source.getCurrent().getCondition().getText();
        return CurrentWeather.builder()
                .description(description)
                .build();
    }

}
