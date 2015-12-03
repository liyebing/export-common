package com.export.excel;

import com.export.model.User;
import com.export.movie.excel.ExportDataToExcel;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyebing created on 15/10/30.
 * @version $Id$
 */
public class ExportDataToExcelTest {

    @Test
    public void export() throws Exception {
        User u1 = new User("liyebing", "liyebing@163.com");
        User u2 = new User("kongxuan", "kongxuan@163.com");

        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        userList.add(u2);

        File file = ExportDataToExcel.<User> export(userList, "test.xls");
        assertTrue(file != null);
    }

}
