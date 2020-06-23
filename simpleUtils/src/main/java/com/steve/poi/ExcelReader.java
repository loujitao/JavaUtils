package com.steve.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {

    // 获取合并单元格的值
    public static String getMergedRegionValue(Sheet sheet , int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){

                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);

                    return getCellValue(fCell) ;
                }
            }
        }
        return null ;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     */
    public static boolean isMergedRegion(Sheet sheet , int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++ ){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){

                    return true ;
                }
            }
        }
        return false ;
    }


    /**
     * 判断合并单元格单元格的范围
     */
    public static void getCellInfo(Sheet sourceSheet){
//        得到所有的合并单元格
        int numMergedRegions = sourceSheet.getNumMergedRegions();
        System.out.println("合并单元格个数："+numMergedRegions);
//        得到某一个合并单元格
        CellRangeAddress oldRange=sourceSheet.getMergedRegion(0);
//        起始行
        int startRow = oldRange.getFirstRow();
        System.out.println("起始行: "+startRow);
//        结束行
        int endRow = oldRange.getLastRow();
        System.out.println("结束行: "+endRow);
//        起始列
        int startColumn = oldRange.getFirstColumn();
        System.out.println("起始列: "+startColumn);
//        结束列
        int endColumn =  oldRange.getLastColumn();
        System.out.println("结束列: "+endColumn);

    }
    /**
     * 获取单元格的值
     */
    public static String getCellValue(Cell cell){

        if(cell == null) return "";

        if(cell.getCellType() == Cell.CELL_TYPE_STRING){

            return cell.getStringCellValue();

        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

            return String.valueOf(cell.getBooleanCellValue());

        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

            return cell.getCellFormula() ;

        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }


    /**
     * 判断sheet页中是否含有合并单元格
     * @param sheet
     * @return
     */
    private static boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0 ? true : false;
    }

    /**
     * 合并单元格
     * @param sheet
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     */
    private static void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }


    public static void main(String[] args)  throws Exception{

        FileInputStream input = new FileInputStream(new File("D:\\tmp\\事件配置在线导入模板.xlsx"));
        String fileName="事件配置在线导入模板.xlsx";
        Workbook wb = null;
        if(fileName.endsWith("xlsx")){
            wb = new XSSFWorkbook(input);
        }else{
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(1);    //获得第二个表单
//        System.out.println(hasMerged(sheet));

        getCellInfo(sheet);

//        String value = getMergedRegionValue(sheet, 3, 0);
//        System.out.println(value);
    }


}
