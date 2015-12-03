package com.export.common;

import com.export.model.User;
import com.export.movie.common.ExtractFileHeader;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

/**
 * @author liyebing created on 15/10/29.
 * @version $Id$
 */
public class ExtractFileHeaderTest {

    @Test
    public void extractHeader() {
        List<String> headers = ExtractFileHeader.<User> generator(User.class);
        assertTrue(headers.size() == 2);
    }

}
