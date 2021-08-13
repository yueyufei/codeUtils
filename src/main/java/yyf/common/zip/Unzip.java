package yyf.common.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class Unzip {

	/**
	 * 解压zip
	 *
	 * @param zipFile
	 * @param descDir
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "resource" })
	public static void unZipFiles(File zipFile, String descDir) throws Exception {
		System.out.println("******************解压开始********************");
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			if ((zipEntryName.trim().lastIndexOf("/")) == -1) {

			}
			in.close();
			out.close();
		}

		System.out.println("******************解压完毕********************");
		System.out.println("******************遍历文件夹********************");
	}

	/**
	 * 解析cfg文件
	 *
	 * @param cfgFile
	 * @return
	 */
	public static Map<String, Object> readCfg(FileInputStream cfgFile) {
		Properties prop = new Properties();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 读取cfg属性文件
			InputStream in = new BufferedInputStream(cfgFile);
			prop.load(new InputStreamReader(in, "GBK")); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				map.put(key, prop.getProperty(key).replaceAll("\"", "").replaceAll(";", ""));
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	   private static final int BUFFER_SIZE = 2 * 1024;

	    /**
	     * 压缩成ZIP 方法2
	     *
	     * @param srcFiles 需要压缩的文件列表
	     * @param out      压缩文件输出流
	     * @throws RuntimeException 压缩失败会抛出运行时异常
	     */
	    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
	        long start = System.currentTimeMillis();
	        ZipOutputStream zos = null;
	        try {
	            zos = new ZipOutputStream(out);
	            for (File srcFile : srcFiles) {
	                byte[] buf = new byte[BUFFER_SIZE];
	                zos.putNextEntry(new ZipEntry(srcFile.getName()));
	                int len;
	                FileInputStream in = new FileInputStream(srcFile);
	                while ((len = in.read(buf)) != -1) {
	                    zos.write(buf, 0, len);
	                }
	                zos.closeEntry();
	                in.close();
	            }
	            long end = System.currentTimeMillis();
	            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
	        } catch (Exception e) {
	            throw new RuntimeException("zip error from ZipUtils", e);
	        } finally {
	            if (zos != null) {
	                try {
	                    zos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

}
