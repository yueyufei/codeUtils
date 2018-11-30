package yyf.common.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Unzip {

	/**
	 * 解压zip
	 *
	 * @param zipFile
	 * @param descDir
	 * @throws Exception
	 */
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

}
