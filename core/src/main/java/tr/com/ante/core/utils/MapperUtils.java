package tr.com.ante.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {

    private static ObjectMapper mapper;

    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper;
    }
}