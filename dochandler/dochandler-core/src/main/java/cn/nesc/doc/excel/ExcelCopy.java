package cn.nesc.doc.excel;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @ClassName ExcelCopy
 * @Description
 * @Author summer
 * @Date 2023/11/9 14:34
 **/
public class ExcelCopy
{
    public static void main(String[] args) throws Exception
    {
        ExcelCopy copy = new ExcelCopy();
        copy.doHandle();
        //        try (FileOutputStream fos = new FileOutputStream(new File("/Users/summer/Desktop/表结构变更记录/all.xlsx")))
        //        {
        //            // 写入excel
        //            XSSFWorkbook to = new XSSFWorkbook();
        //            // 读取excel
        //            File fromDirectory = new File("/Users/summer/Desktop/表结构变更记录");
        //            if (fromDirectory.isDirectory())
        //            {
        //                Arrays.stream(fromDirectory.listFiles((dir, name) -> {
        //                    if (name.endsWith(".xlsx") && !"all.xlsx".equals(name) && !name.startsWith("~"))
        //                    {
        //                        return true;
        //                    }
        //                    return false;
        //                })).forEach(innerFile -> {
        //                    System.out.println("name ------>" + innerFile);
        //                    try (FileInputStream innerFileStream = new FileInputStream(innerFile))
        //                    {
        //                        XSSFWorkbook from = new XSSFWorkbook(innerFileStream);
        //                        System.out.println(innerFile.getName());
        //                        XSSFSheet currentSheet = to.createSheet(innerFile.getName().substring(0, innerFile.getName().indexOf(".")));
        //                        XSSFSheet fromSheet = from.getSheet("sheet");
        //                        List<XSSFRow> allRows = new ArrayList<>();
        //                        int rowCount = fromSheet.getPhysicalNumberOfRows();
        //                        for (int i = 0; i < rowCount; i++)
        //                        {
        //                            allRows.add(fromSheet.getRow(i));
        //                        }
        //                        currentSheet.copyRows(allRows, 0, new CellCopyPolicy());
        //                    }
        //                    catch (IOException e)
        //                    {
        //                        throw new RuntimeException(e);
        //                    }
        //                });
        //                to.write(fos);
        //            }
        //
        //
        //        }
        //        catch (IOException e)
        //        {
        //            throw new RuntimeException(e);
        //        }
    }

    private void copyRows(XSSFSheet src, XSSFSheet desc)
    {
        try
        {
            List<XSSFRow> allRows = new ArrayList<>();
            int rowCount = src.getPhysicalNumberOfRows();
            for (int i = 0; i < rowCount; i++)
            {
                allRows.add(src.getRow(i));
            }
            desc.copyRows(allRows, 0, new CellCopyPolicy());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    private void readFiles(File directory, FilenameFilter filter, Consumer<List<? extends File>> handler) throws Exception
    {
        if (directory.isDirectory())
        {
            handler.accept(Arrays.stream(directory.listFiles(filter)).collect(Collectors.toList()));
        }
        else
        {
            throw new Exception(directory.getName() + "不是目录");
        }
    }

    private XSSFWorkbook readExcel(File excelFile) throws Exception
    {
        if (!excelFile.isFile())
        {
            throw new Exception(excelFile.getName() + "is not a file");
        }
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        return workbook;
    }

    private void copyExcel(XSSFWorkbook src, String srcSheetName, XSSFWorkbook dest, String destSheetName)
    {
        XSSFSheet currentSheet = dest.createSheet(destSheetName);
        XSSFSheet fromSheet = src.getSheet(srcSheetName);
        List<XSSFRow> allRows = new ArrayList<>();
        int rowCount = fromSheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowCount; i++)
        {
            allRows.add(fromSheet.getRow(i));
        }
        currentSheet.copyRows(allRows, 0, new CellCopyPolicy());
    }

    private void doHandle() throws Exception
    {
        XSSFWorkbook to1 = new XSSFWorkbook();
        XSSFWorkbook to2 = new XSSFWorkbook();
        XSSFWorkbook to3 = new XSSFWorkbook();
        XSSFWorkbook to4 = new XSSFWorkbook();
        readFiles(new File("/Users/summer/Desktop/表结构变更记录"), new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if (name.endsWith(".xlsx") && !"all.xlsx".equals(name) && !name.startsWith("~"))
                {
                    return true;
                }
                return false;
            }
        }, files -> {
            int fileCount = files.size();
            for (int i = 0; i < fileCount; i++)
            {
                try
                {
                    System.out.println("File :" + files.get(i).getName() + " would put in barrel " + Math.floorMod(i, 4));
                    XSSFWorkbook from = readExcel(files.get(i));
                    switch (Math.floorMod(i, 4))
                    {
                        case 0:
                            copyExcel(from, "sheet", to1, files.get(i).getName().substring(0, files.get(i).getName().indexOf(".")));
                            break;
                        case 1:
                            copyExcel(from, "sheet", to2, files.get(i).getName().substring(0, files.get(i).getName().indexOf(".")));
                            break;
                        case 2:
                            copyExcel(from, "sheet", to3, files.get(i).getName().substring(0, files.get(i).getName().indexOf(".")));
                            break;
                        case 3:
                            copyExcel(from, "sheet", to4, files.get(i).getName().substring(0, files.get(i).getName().indexOf(".")));
                            break;
                        default:
                    }

                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
            try (
                    FileOutputStream fos1 = new FileOutputStream("/Users/summer/Desktop/表结构变更记录/part-1.xlsx");
                    FileOutputStream fos2 = new FileOutputStream("/Users/summer/Desktop/表结构变更记录/part-2.xlsx");
                    FileOutputStream fos3 = new FileOutputStream("/Users/summer/Desktop/表结构变更记录/part-3.xlsx");
                    FileOutputStream fos4 = new FileOutputStream("/Users/summer/Desktop/表结构变更记录/part-4.xlsx")
            )
            {
                to1.write(fos1);
                to2.write(fos2);
                to3.write(fos3);
                to4.write(fos4);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });
    }
}
