package com.thebest.thebestpc.service.json;

import java.util.List;

public interface JsonService {
    String toJson(Object object);

    <T> T fromJson(String json, Class<T> clazz);

    <T> List<T> parseJsonArray(String json, Class<T> clazz);
}
