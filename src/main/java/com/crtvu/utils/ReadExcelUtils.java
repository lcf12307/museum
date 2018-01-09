package com.crtvu.utils;

/**
 * Created by Jixw on 2018/1/8.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelUtils {

    private static POIFSFileSystem fs;
    private static HSSFWorkbook wb;
    private static HSSFSheet sheet;
    private static HSSFRow row;


    public static String[] readExcelTitle(HSSFSheet sheet) {
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    public static Map<Integer, String> readExcelContent(HSSFSheet sheet) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // ",";
                str += getCellFormatValue(row.getCell((short) j)).trim() + ",";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    public static Map<String, Integer> readMuseumScore(HSSFSheet sheet,int needColNum) throws IOException {
        try{
            Map<String, Integer> content = new HashMap<String, Integer>();
            String str = "";
            int score=0;
            sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();

            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                colNum = row.getPhysicalNumberOfCells();
                if(colNum!=2)
                    throw new IOException(sheet.getClass().toString()+",第"+i+"行列数错误，应该为"+needColNum+"列");
                str=getCellFormatValue(row.getCell(0));
                score=(int)Double.parseDouble(getCellFormatValue(row.getCell(1)));
                content.put(str, score);
                str = "";
                score=0;
            }
            return content;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private static String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static HSSFSheet getSheet(File file,int sheetNum){
        InputStream stream=null;
        try {
            stream = new FileInputStream(file);
            try {
                fs = new POIFSFileSystem(stream);
                wb = new HSSFWorkbook(fs);
                sheet=wb.getSheetAt(sheetNum);
                return sheet;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
                fs.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        File file = new File("C:\\Users\\Jixw\\Desktop\\museum-1\\target\\museum\\WEB-INF\\upload\\专家打分表_专家1_2017\\定性打分表.xls");
        InputStream stream=null;
        try {
            stream = new FileInputStream(file);
            try {
                fs = new POIFSFileSystem(stream);
                wb = new HSSFWorkbook(fs);
                sheet = wb.getSheetAt(0);

                String []title = readExcelTitle(sheet);
                for(String e:title){
                    System.out.print(e+"  ");
                }
                System.out.println();


                Map<String, Integer> map = readMuseumScore(sheet, 2);
                Set<Map.Entry<String, Integer>> allSet=map.entrySet();
                Iterator<Map.Entry<String, Integer>> iterator = allSet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Integer> em = iterator.next();
                    System.out.println(em.getKey()+","+em.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }finally{
            try {
                stream.close();
            } catch (IOException e) {
                // TODO 自动生成 catch 块
                e.printStackTrace();
            }
        }
    }

}
