package com.carryless.utils.file;

import java.io.File;

/**
 * 此类为文件(夹)工具类
 * @author  carryLess
 * @version 1.0
 * @date    2018-04-12
 */
public class FileUtils {
    private FileUtils(){}

    /**
     * 删除单个文件
     * @param filePath 文件路径(String)
     * @return  是否删除成功
     */
    public static boolean delFile(String filePath){
        boolean flag = false;
        File file = new File(filePath);
        /**
         * 路径为文件且不为空则进行删除
          */
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除文件夹下的所有文件
     * @param folderPath 文件夹路径(String)
     * @return
     */
    public static boolean delFilesInFolder(String folderPath) throws Exception{
        boolean flag = true;
        folderPath = addFileSeparator(folderPath);
        File dirFile = new File(folderPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            throw new Exception("该路径不存在或者不是一个文件夹！");
        }
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = delFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = delFilesInFolder(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     * @param folderPath 文件夹路径(String)
     * @return
     * @throws Exception
     */
    public static boolean delFolder(String folderPath) throws Exception{
        delFilesInFolder(folderPath);
        File dirFile = new File(folderPath);
        return dirFile.delete();
    }

    /**
     * 给不以文件分隔符结尾的路径添上文件分隔符
     * @param srcPath 原始路径
     * @return 处理过的路径
     */
    public static String addFileSeparator(String srcPath){
        return srcPath.endsWith(File.separator) ? srcPath : (srcPath+=File.separator);
    }
}
