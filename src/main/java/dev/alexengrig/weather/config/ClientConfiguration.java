/*
 * Copyright 2022 Alexengrig Dev.
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

import dev.alexengrig.weather.client.OpenWeatherMapClient;
import dev.alexengrig.weather.client.WeatherApiClient;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }

    @Bean
    public OpenWeatherMapClient openWeatherMapClient(
            Encoder encoder,
            Decoder decoder,
            @Value("${application.client.openweathermap.api.url}") String url,
            @Value("${application.client.openweathermap.api.key}") String key) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(template -> template.query("appid", key))
                .target(OpenWeatherMapClient.class, url);
    }

    @Bean
    public WeatherApiClient weatherApiClient(
            Encoder encoder,
            Decoder decoder,
            @Value("${application.client.weatherapi.api.url}") String url,
            @Value("${application.client.weatherapi.api.key}") String key) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(template -> template.query("key", key))
                .target(WeatherApiClient.class, url);
    }

}
