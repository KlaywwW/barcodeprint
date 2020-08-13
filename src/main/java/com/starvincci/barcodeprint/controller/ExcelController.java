package com.starvincci.barcodeprint.controller;

import com.starvincci.barcodeprint.pojo.BmBom;
import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.read.service.BmBomServiceImpl;
import com.starvincci.barcodeprint.write.service.SizeServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private BmBomServiceImpl bmBomService;
    @Autowired
    private SizeServiceImpl sizeService;

    @RequestMapping("/excelUpload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            String filePath = "D:\\bartender\\excels\\" + fileName;
            File dest = new File(filePath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(fileName);
            Workbook wb = getExcel(filePath);
            analyzeExcel(wb, response,fileName);
        }
        return "redirect:/updateInfo";
    }

    public Workbook getExcel(String filePath) {
        Workbook wb = null;
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            wb = null;
        } else {
            String fileType = filePath.substring(filePath.lastIndexOf("."));//获得后缀名
            System.out.println(fileType);
            try {
                InputStream is = new FileInputStream(filePath);
                if (".xls".equals(fileType)) {
                    wb = new HSSFWorkbook(is);
                } else if (".xlsx".equals(fileType)) {
                    wb = new XSSFWorkbook(is);
                } else if (".XLS".equals(fileType)) {
                    wb = new HSSFWorkbook(is);
                } else if (".XLSX".equals(fileType)) {
                    wb = new XSSFWorkbook(is);
                } else {
                    System.out.println("格式不正确");
                    wb = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public void analyzeExcel(Workbook wb, HttpServletResponse response,String fileName) {
        Sheet sheet = wb.getSheetAt(0);//读取sheet(从0计数)
        List<String> cellList = new ArrayList<>();
        List<String> lists = new ArrayList<>();
        CellStyle style = wb.createCellStyle();
        int rowNum = sheet.getLastRowNum()+1;//读取行数(从0计数)
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);//获得行
//            如果Excel一整行都为空就退出本次循环进行下一次
            if (row == null) {
                continue;
            }
            int colNum = row.getLastCellNum();//获得当前行的列数
            for (int j = 0; j < colNum; j++) {
                Cell cell = row.getCell(j);
            }


            Cell cell = row.getCell(1);//获取单元格
            if (cell == null) {
                System.out.println("null");
                continue;
            } else {
//                将excel中的存货编码保存到集合中
                cellList.add(cell.toString());
                System.out.println(cell.toString() + "     ");
            }
        }
        System.out.println(cellList.size());

        int count = 0;
//        循环比对集合中的存货编码所找出的对应款的存货编码与mysql中的比较 将存在的保存在集合中
        for (int j = 0; j < cellList.size(); j++) {
            List<BmBom> bmBomList = bmBomService.findBmBySpNo(cellList.get(j));
//            if (bmBomList.size()>1){
            for (int i = 0; i < bmBomList.size(); i++) {
                Erpsp erpsp = bmBomService.findSpNo(bmBomList.get(i).getSpId());
                List<Erpsp2> erpList = sizeService.findTagsBySpNo(erpsp.getSp_No());
                if (erpList.size() > 0) {
                    System.out.println(erpsp.getSp_No() + "+" + erpsp.getSp_id() + "+" + erpsp.getSp_Name());
                    lists.add(cellList.get(j));
                    count++;
                }
            }

//            }

        }
        System.out.println("Excel中存在-- " + cellList.size() + " --条数据");
        System.out.println("数据库中已存在-- " + count + " --条数据");
        for (int k = 0; k < lists.size(); k++) {
            System.out.println(lists.get(k));
        }
        createWorkBook(wb, lists, response,fileName);

    }

    public void createWorkBook(Workbook wb, List<String> lists, HttpServletResponse response,String fileName) {
        HSSFWorkbook wbNew = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheetNew = wbNew.createSheet("sheet1");
        sheetNew.setDefaultColumnWidth(20);// 默认列宽
//        设置第一列的宽度
        sheetNew.setColumnWidth(0, 1300);

        Sheet sheet = wb.getSheetAt(0);//读取sheet(从0计数)

        int rowNum = sheet.getLastRowNum()+1;//读取行数(从0计数)返回最后一行的索引，即比行总数小1





        System.out.println("总行数----" + rowNum);
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);//获得行
            HSSFRow rowNew = sheetNew.createRow((int) i);

//            如果Excel一整行都为空就退出本次循环进行下一次
            if (row == null) {
                continue;
            }
            int colNum = row.getLastCellNum();//获得当前行的列数
            HSSFCell cellNew = null;
            HSSFCellStyle style = wbNew.createCellStyle();

            if (i == 0) {
//                设置第一行样式
                rowNew.setHeightInPoints((short) 40);//行高
                CellRangeAddress region = new CellRangeAddress(0, 0, 0, colNum);//合并单元格
                sheetNew.addMergedRegion(region);
                Font font = wbNew.createFont();
                font.setFontHeightInPoints((short) 15);
                font.setFontName("新宋体");
                font.setBold(true);
                style.setFont(font);
                style.setAlignment(HorizontalAlignment.CENTER);
            }
            if (i == 1) {
//                设置第二行字体样式
                Font font = wbNew.createFont();
                font.setFontHeightInPoints((short) 10);
                font.setFontName("新宋体");
                font.setBold(true);
                style.setFont(font);
            }

            for (int j = 0; j < colNum; j++) {
                Cell cell = row.getCell(j);
                String value = null;

                cellNew = rowNew.createCell((short) j);
                if (cell == null) {
                    cellNew.setCellValue("");
//                continue;
                } else {
//                   单元格为空直接进行下一次循环
                    if ("null".equals(cell.toString()) || cell.toString() == null || "".equals(cell.toString())) {
                        cellNew.setCellValue("");
                        continue;
                    }

//                   是公式的计算填入，不是以字符串填入
                    switch (cell.getCellType()) {
                        case FORMULA:
                            // cell.getCellFormula();
                            try {
                                value = String.valueOf(cell.getNumericCellValue());
                            } catch (IllegalStateException e) {
                                value = String.valueOf(cell.getRichStringCellValue());
                            }
                            break;
                        case NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            value = String.valueOf(cell.getRichStringCellValue().getString());
                            break;
                    }

                    cellNew.setCellValue(value);
//                    只给第二列和大于第二行不等于最后一行的单元格设置颜色样式
                    if (j == 1 && i > 1 && i!=rowNum-1) {
//                        System.out.println();
                        int count = 0;
                        for (int k = 0; k < lists.size(); k++) {
                            if (lists.get(k).equals(cell.toString())) {
//                                System.out.println("已添加");
                                count++;
                            }

                        }
                        if (count == 0) {
                            style.setFillForegroundColor(IndexedColors.RED1.getIndex());// 设置背景色
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                        } else {
                            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());// 设置背景色
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        }
                    }
//                    设置单元格样式
                    style.setBorderBottom(BorderStyle.THIN);
                    style.setBorderLeft(BorderStyle.THIN);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setBorderTop(BorderStyle.THIN);
                    cellNew.setCellStyle(style);
                }
            }

//            Cell cell = row.getCell(i);//获取单元格
        }
        String newFileName=fileName.substring(0,fileName.lastIndexOf("."))+"(比对后).xls";
        System.out.println(newFileName);
        File dest = new File("D:\\bartender\\excels\\analyze\\"+newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
//        System.out.println(dest.getAbsolutePath());

        // 第六步，将文件存到指定位置并下载
        try {
            FileOutputStream fout = new FileOutputStream(dest.getAbsolutePath());
            wbNew.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File file = new File(dest.getAbsolutePath());
            String filename = newFileName;
            InputStream inputStream = new FileInputStream(file);
            //强制下载不打开
            response.setContentType("application/force-download");
            OutputStream out = response.getOutputStream();
            //使用URLEncoder来防止文件名乱码或者读取错误
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if (b != -1) out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {

    }

}
