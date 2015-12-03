package com.export.movie.common;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyebing created on 15/10/29.
 * @version $Id$
 */
public class ExtractFileHeader {

    public static <T> List<String> generator(Class<T> modelClazz) {
        Field[] fields = modelClazz.getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return new ArrayList<String>(0);
        }

        List<String> headers = new ArrayList<String>(ArrayUtils.getLength(fields));
        for (Field field : fields) {
            boolean isPresent = field.isAnnotationPresent(Comment.class);
            if (!isPresent) {
                continue;
            }
            String headerInfo = field.getAnnotation(Comment.class).value();
            headers.add(headerInfo);
        }
        return headers;
    }




}
