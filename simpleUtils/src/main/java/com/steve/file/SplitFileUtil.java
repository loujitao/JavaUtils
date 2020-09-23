package com.steve.file;

import java.io.*;

/**
 * @Author  SteveTao
 * @Description  大文件分片保存
 * @Date 2020/3/13 11:04
 **/
public class SplitFileUtil {

    public static void main(String[] args) throws Exception {
        //1、切分
//      String fileSource=  "F:\\myTestWorkspace\\PanDownload.zip";
//      String saveDir="F:\\myTestWorkspace\\PanDownload";
//      //1000KB= 1024 *1000
//      int splitSize=1024000;
//        SplitFileUtil.cutFile(fileSource,saveDir,splitSize);

        //2、合并
        String[] list = new File("F:\\myTestWorkspace\\split").list();
        String[] fileList=new String[list.length];
        for (int i = 0; i < list.length; i++) {
            fileList[i]="F:\\myTestWorkspace\\split\\"+list[i];
        }
        SplitFileUtil.mergeFile(fileList,"F:\\myTestWorkspace\\split","big.zip");
    }
    /**
     * @Author  SteveTao
     * @Description   大文件分片
     * @Date 2020/3/13 11:11
     **/
    public static void cutFile(String src, String dir, int num) throws  Exception {
        FileInputStream fis = null;
        File file = null;
        try {
            File dirFile=new File(dir);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            fis = new FileInputStream(src);
            file = new File(src);
            //分别找到原大文件的文件名和文件类型，为下面的小文件命名做准备
            String fileName = file.getName();
            int lastIndexOf = fileName.lastIndexOf(".");
            String name = fileName.substring(0, lastIndexOf);
            String type = fileName.substring(lastIndexOf, fileName.length());
            //创建规定大小的byte数组
            byte[] b = new byte[num];
            int len = 0;
            //partition为以后的小文件命名做准备     xx_1, xx_2
            int partition = 1;
            //遍历将大文件读入byte数组中，当byte数组读满后写入对应的小文件中
            while ((len = fis.read(b)) != -1) {
                FileOutputStream fos = new FileOutputStream(dir + "\\\\"+ name + "_" + partition + type);
                //将byte数组写入对应的小文件中
                fos.write(b, 0, len);
                //结束资源
                fos.close();
                partition++;
            }
        } finally {
            try {
                if (fis != null) {
                    //结束资源
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author  SteveTao
     * @Description  切片后小文件合并成大文件
     * @Date 2020/3/13 11:33
     **/
    public static void mergeFile(String[] partSrc, String dirSrc, String bigFileName) throws  Exception {
        FileOutputStream fos=null;
        BufferedOutputStream bos=null;
        try {
            fos= new FileOutputStream(dirSrc + "\\\\"+ bigFileName);
            bos=new BufferedOutputStream(fos);
            for (String fileSrc : partSrc) {
               FileInputStream fis = new FileInputStream(new File(fileSrc));
                //创建规定大小的byte数组
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = fis.read(b)) != -1) {
                    //将byte数组写入对应的小文件中
                    bos.write(b);
                }
                //结束资源
                fis.close();
            }
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private SplitFileUtil() {
    }
}
