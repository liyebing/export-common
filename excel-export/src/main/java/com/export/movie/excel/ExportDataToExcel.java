package com.export.movie.excel;

import com.export.movie.common.ExtractFileHeader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author liyebing created on 15/10/30.
 * @version $Id$
 */
public class ExportDataToExcel {

    public static <T> File export(List<T> models, String fileName) throws Exception {

        if (CollectionUtils.isEmpty(models) || StringUtils.isBlank(fileName)) {
            return null;
        }

        List<String> header = ExtractFileHeader.generator(models.get(0).getClass());

        Field[] fields = models.get(0).getClass().getDeclaredFields();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(15);
        //HSSFCellStyle style = buildHssfCellStyle(workbook);
        //HSSFCellStyle style2 = buildHssfCellStyle2(workbook);

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < header.size(); i++) {
            HSSFCell cell = row.createCell(i);
            //cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(header.get(i));
            cell.setCellValue(text);
        }

        Iterator<T> it = models.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            int cellIndex = 0;

            for (Field f : fields) {
                HSSFCell cell = row.createCell(cellIndex);
                //cell.setCellStyle(style2);
                f.setAccessible(true);
                String value = Objects.toString(f.get(t));

                HSSFRichTextString richString = new HSSFRichTextString(value);
                HSSFFont font3 = workbook.createFont();
                font3.setColor(HSSFColor.BLUE.index);
                richString.applyFont(font3);
                cell.setCellValue(richString);
                cellIndex++;
            }
        }

        File file = new File(fileName);
        OutputStream out = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            workbook.write(out);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return file;
    }

    private static HSSFCellStyle buildHssfCellStyle2(HSSFWorkbook workbook) {
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        return style2;
    }

    private static HSSFCellStyle buildHssfCellStyle(HSSFWorkbook workbook) {
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        return style;
    }
}
