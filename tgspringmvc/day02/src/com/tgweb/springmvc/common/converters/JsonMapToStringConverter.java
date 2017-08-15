package com.tgweb.springmvc.common.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgweb.springmvc.common.domain.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class JsonMapToStringConverter implements Converter<JsonMap, String> {

    private Logger logger = LoggerFactory.getLogger(JsonMapToStringConverter.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(JsonMap jsonMap) {
        if (jsonMap == null)
            throw new RuntimeException("jsonMap is null");
        String s = null;
        try {
            s = objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            logger.error("json process exception", e);
            throw new RuntimeException("json process exception", e);
        }
        return s;
    }
}
