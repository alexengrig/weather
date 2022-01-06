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

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

abstract class NotNullConverter<S, T> implements Converter<S, T> {

    @NonNull
    @Override
    public final T convert(S source) {
        Assert.notNull(source, "The source must not be null");
        T target = doConvert(source);
        assert target == null : "The target must not be null";
        return target;
    }

    abstract T doConvert(S source);

}

