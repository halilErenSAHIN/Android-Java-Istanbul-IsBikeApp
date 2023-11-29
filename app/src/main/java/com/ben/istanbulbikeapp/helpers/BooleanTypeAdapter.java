package com.ben.istanbulbikeapp.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class BooleanTypeAdapter implements JsonDeserializer<Boolean> {


    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int value = json.getAsInt();
        return value == 1;
    }
}
