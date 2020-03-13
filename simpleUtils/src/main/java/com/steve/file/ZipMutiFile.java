package com.steve.file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description: 多个文件打进一个zip压缩包
 * @Author: SteveTao
 * @Date: 2020/3/1310:31
 **/
public class ZipMutiFile {

    public static void main(String[] args) throws Exception {
        File[] files = new File[3];
        files[0] = new File("F:\\myTestWorkspace\\1.txt");
        files[1] = new File("F:\\myTestWorkspace\\2.txt");
        files[2] = new File("F:\\myTestWorkspace\\3.txt");

        File zip = new File("F:\\myTestWorkspace\\count.zip");
        ZipMutiFile.zipFiles(files, zip);
    }


    /**
     * .设置response的header   zip文件在controller写出时
     */
//            response.setContentType("application/zip");
//            response.setHeader("Content-Disposition", "attachment; filename=excel.zip");
    public static void zipFiles(File[] srcFiles, File zipFile) throws Exception {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            zipFile.createNewFile();
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;
        // 创建 ZipEntry 对象
        ZipEntry zipEntry = null;
        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.length; i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles[i]);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
        } finally {
            try {
                if(zipOutputStream!=null) {
                    zipOutputStream.closeEntry();
                    zipOutputStream.close();
                }
                if(fileInputStream!=null) {
                    fileInputStream.close();
                }
                if(fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
