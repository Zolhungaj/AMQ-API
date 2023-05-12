/*
 * Copyright (C) 2018 Square, Inc., modified by Zolhungaj 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.zolhungaj.amqapi.adapters;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.io.IOException;
import java.util.Arrays;

import static com.squareup.moshi.internal.Util.jsonName;

/**adapted from com.squareup.moshi.adapters.EnumJsonAdapter;*/
public final class IntegerEnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
    final Class<T> enumType;
    final String[] nameStrings;
    final T[] constants;
    final JsonReader.Options options;
    final boolean useFallbackValue;
    final T fallbackValue;

    public static <T extends Enum<T>> IntegerEnumJsonAdapter<T> create(Class<T> enumType) {
        return new IntegerEnumJsonAdapter<>(enumType, null, false);
    }

    /**
     * Create a new adapter for this enum with a fallback value to use when the JSON string does not
     * match any of the enum's constants. Note that this value will not be used when the JSON value is
     * null, absent, or not a string. Also, the string values are case-sensitive, and this fallback
     * value will be used even on case mismatches.
     */
    public IntegerEnumJsonAdapter<T> withUnknownFallback(T fallbackValue) {
        return new IntegerEnumJsonAdapter<>(enumType, fallbackValue, true);
    }

    private IntegerEnumJsonAdapter(Class<T> enumType, T fallbackValue, boolean useFallbackValue) {
        this.enumType = enumType;
        this.fallbackValue = fallbackValue;
        this.useFallbackValue = useFallbackValue;
        try {
            constants = enumType.getEnumConstants();
            nameStrings = new String[constants.length];
            for (int i = 0; i < constants.length; i++) {
                String constantName = constants[i].name();
                nameStrings[i] = jsonName(constantName, enumType.getField(constantName));
            }
            options = JsonReader.Options.of(nameStrings);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Missing field in " + enumType.getName(), e);
        }
    }

    @Override
    public T fromJson(JsonReader reader) throws IOException {
        if(reader.peek() == JsonReader.Token.NUMBER){
            int value = reader.nextInt();
            String valueAsString = String.valueOf(value);
            int index = options.strings().indexOf(valueAsString);
            if (index != -1) return constants[index];
        }

        String path = reader.getPath();
        if (!useFallbackValue) {
            String name = reader.nextString();
            throw new JsonDataException(
                    "Expected one of "
                            + Arrays.asList(nameStrings)
                            + " but was "
                            + name
                            + " at path "
                            + path);
        }
        if (reader.peek() != JsonReader.Token.NUMBER) {
            throw new JsonDataException(
                    "Expected a number but was " + reader.peek() + " at path " + path);
        }
        reader.skipValue();
        return fallbackValue;
    }

    @Override
    public void toJson(JsonWriter writer, T value) throws IOException {
        if (value == null) {
            throw new NullPointerException(
                    "value was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.value(Integer.valueOf(nameStrings[value.ordinal()]));
    }

    @Override
    public String toString() {
        return "IntegerEnumJsonAdapter(" + enumType.getName() + ")";
    }
}

