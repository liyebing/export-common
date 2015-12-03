java应用中数据导出工具，目前只支持excel,后续会支持其他格式

附上使用例子：


     @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        File file = null;
        FileInputStream inputStream = null;
        try {
            String fileName = buildFileName();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = response.getOutputStream();

            List<ExportDataToExcelModel> dataToExcelList = cinemaFeeRuleBiz.queryExportDataToExcelData();
            file = ExportDataToExcel.<ExportDataToExcelModel>export(dataToExcelList, fileName);
            inputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int index = 0;
            while ((index = inputStream.read(buff, 0, 1024)) > 0) {
                outputStream.write(buff, 0, index);
            }
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            logger.warn("exportExcel error.",e);
        } finally {
            FileUtils.deleteQuietly(file);
            IOUtils.closeQuietly(inputStream);
        }
    }





