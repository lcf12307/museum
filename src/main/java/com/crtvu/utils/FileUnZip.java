package com.crtvu.utils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import javax.servlet.ServletException;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class FileUnZip {
    /**
     * 解压zip文件
     *
     * @param sourceFile,待解压的zip文件;
     *            toFolder,解压后的存放路径
     * @throws Exception
     **/

    public static void zipToFile(String sourceFile, String toFolder) throws Exception {
        String toDisk = toFolder;// 接收解压后的存放路径
        ZipFile zfile = new ZipFile(sourceFile, "utf-8");// 连接待解压文件
        Enumeration zList = zfile.getEntries();// 得到zip包里的所有元素
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                // log.info("打开zip文件里的文件夹:"+ ze.getName() +"skipped...");
                continue;
            }
            OutputStream outputStream = null;
            InputStream inputStream = null;
            try {
                // 以ZipEntry为参数得到一个InputStream，并写到OutputStream中
                outputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(toDisk, ze.getName())));
                inputStream = new BufferedInputStream(zfile.getInputStream(ze));
                int readLen = 0;
                while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
                    outputStream.write(buf, 0, readLen);
                }
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                // log.info("解压失败："+e.toString());
                throw new IOException("解压失败：" + e.toString());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {

                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                inputStream = null;
                outputStream = null;
            }

        }
        zfile.close();
    }

    /**
     *
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     *
     * @param zippath
     *            指定根目录
     *
     * @param absFileName
     *            相对路径名，来自于ZipEntry中的name
     *
     * @return java.io.File 实际的文件
     *
     */

    private static File getRealFileName(String zippath, String absFileName) {
        // log.info("文件名："+absFileName);
        String[] dirs = absFileName.split("/", absFileName.length());
        File ret = new File(zippath);// 创建文件对象
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                ret = new File(ret, dirs[i]);
            }
        }
        if (!ret.exists()) {// 检测文件是否存在
            ret.mkdirs();// 创建此抽象路径名指定的目录
        }
        ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child
        // 路径名字符串创建一个新 File 实例
        return ret;
    }

    public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            int size = files.size();
// 压缩列表中的文件
            for (int i = 0; i < size; i++) {
                File file = (File) files.get(i);
                zipFile(file, outputStream);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    public static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    outputstream.putNextEntry(entry);
                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] inOutbyte; // byte数组接受文件的数据
                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
// 读入流,保存在byte数组
                            bInStream.read(inOutbyte, 0, MAX_BYTE);
                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                        }
                    }
// 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry(); // Closes the current ZIP entry
// and positions the stream for
// writing the next entry
                    bInStream.close(); // 关闭
                    inStream.close();
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}

