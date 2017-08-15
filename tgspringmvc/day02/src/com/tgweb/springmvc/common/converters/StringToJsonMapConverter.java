package com.tgweb.springmvc.common.converters;

import com.chain.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgweb.springmvc.common.domain.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StringToJsonMapConverter implements Converter<String, JsonMap> {

    private Logger logger = LoggerFactory.getLogger(StringToJsonMapConverter.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonMap convert(String s) {
        if (StringUtils.isEmpty(s))
            throw new RuntimeException("empty string");
        JsonMap jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(s, JsonMap.class);
        } catch (IOException e) {
            logger.error("io exception", e);
            throw new RuntimeException("not a json string", e);
        }
        return jsonMap;
    }
}
