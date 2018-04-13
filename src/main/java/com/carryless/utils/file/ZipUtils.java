package com.carryless.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩类
 * @author  carryLess
 * @date    2018-04-13
 * @version 1.0
 */
public class ZipUtils {
    private ZipUtils(){}


    /**
     * 压缩文件(夹)
     * @param srcfile   需要被压缩的文件(夹)绝对路径
     * @param targetFile    被压缩后的zip文件,eg:"D:\\zip\\yaSuo.zip"
     */
    public static void zipFiles(File srcfile,File targetFile) {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(targetFile));

            if(srcfile.isFile()){
                zipFile(srcfile, out, "");
            } else{
                File[] list = srcfile.listFiles();
                for (int i = 0; i < list.length; i++) {
                    compress(list[i], out, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 压单个文件
     * @param srcfile 被压缩的源文件
     * @param out 输出的zip文件
     * @param basedir 需要加在Zip文件夹中单个文件的前缀
     */
    private static void zipFile(File srcfile, ZipOutputStream out,String basedir) {
        if (!srcfile.exists())
            return;
        byte[] buf = new byte[1024];
        FileInputStream in = null;
        try {
            int len;
            in = new FileInputStream(srcfile);
            out.putNextEntry(new ZipEntry(basedir + srcfile.getName()));
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.flush();
                    out.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件夹
     * @param dir 需要被压缩的文件夹
     * @param out 输出zip的流
     * @param basedir
     */
    private static void zipDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 压缩文件夹里的文件
     * 起初不知道是文件还是文件夹--- 统一调用该方法
     * @param file 需要被压缩的文件
     * @param out   输出zip的流
     * @param basedir   需要加在zip中单个文件的前缀，不加可为""
     */
    private static void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            zipDirectory(file, out, basedir);
        } else {
            zipFile(file, out, basedir);
        }
    }

}
