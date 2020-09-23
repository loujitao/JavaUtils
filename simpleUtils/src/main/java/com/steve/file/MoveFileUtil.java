package com.steve.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: SteveTao
 * @Date: 2020/3/1310:40
 **/
public class MoveFileUtil {


    public static void main(String[] args) throws IOException {
        File f = new File("F:\\myTestWorkspace\\1.txt");
        File f1 = new File("F:\\myTestWorkspace\\zip");
        MoveFileUtil.copy(f,f1);
    }

    public static void copy(String filePath, String dirPath) throws IOException {
        if(filePath.isEmpty() || dirPath.isEmpty()){
            throw new RuntimeException("源文件和目标目录必须输入！");
        }
        File f = new File(filePath);
        File f1 = new File(dirPath);
        MoveFileUtil.copy(f,f1);
    }

    public static void copy(File f, File dir) throws IOException {
        if(!f.exists()){
            throw new RuntimeException("源文件不存在！");
        }
        if(!dir.exists()){
            dir.mkdir();
        }
        FileInputStream  fis=null;
        FileOutputStream fos=null;
        try {
            fis = new FileInputStream(f);
            fos= new FileOutputStream(dir + "\\" +f.getName());
            byte[] b = new byte[1024];
            int i = fis.read(b);
            while(i != -1){
                fos.write(b, 0, i);
                i = fis.read(b);
            }
        } finally {
            try {
                if(fis!=null) {
                    fis.close();
                }
                if(fis!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private MoveFileUtil() {
    }
}
