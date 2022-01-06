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

package dev.alexengrig.weather.config;

import dev.alexengrig.weather.client.OpenWeatherMapCurrentWeatherClient;
import dev.alexengrig.weather.client.WeatherApiCurrentWeatherClient;
import dev.alexengrig.weather.client.WeatherbitCurrentWeatherClient;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrentWeatherClientConfiguration {

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }

    @Bean
    public OpenWeatherMapCurrentWeatherClient openWeatherMapCurrentWeatherClient(
            Encoder encoder,
            Decoder decoder,
            @Value("${application.client.openweathermap.api.url}") String url,
            @Value("${application.client.openweathermap.api.key}") String key) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(template -> template.query("appid", key))
                .target(OpenWeatherMapCurrentWeatherClient.class, url);
    }

    @Bean
    public WeatherApiCurrentWeatherClient weatherApiCurrentWeatherClient(
            Encoder encoder,
            Decoder decoder,
            @Value("${application.client.weatherapi.api.url}") String url,
            @Value("${application.client.weatherapi.api.key}") String key) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(template -> template.query("key", key))
                .target(WeatherApiCurrentWeatherClient.class, url);
    }

    @Bean
    public WeatherbitCurrentWeatherClient weatherbitCurrentWeatherClient(
            Encoder encoder,
            Decoder decoder,
            @Value("${application.client.weatherbit.api.url}") String url,
            @Value("${application.client.weatherbit.api.key}") String key) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(template -> template.query("key", key))
                .target(WeatherbitCurrentWeatherClient.class, url);
    }

}
