package yyf.common.fille;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FileUtils {
	@SuppressWarnings({ "unchecked" })
	public List<String> readFileInJar(String path) {
		InputStream is = null;
		InputStreamReader isr = null;
		;
		BufferedReader br = null;

		List<String> lines = new ArrayList<>();
		Object obj;
		is = getClass().getResourceAsStream(path);
		isr = new InputStreamReader(is, Charset.forName("UTF-8"));
		br = new BufferedReader(isr);
		do {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line == null)
				break;
			lines.add(line);
		} while (true);
		obj = lines;
		try {
			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<String>) obj;
	}
	public static String readFile(String path) throws IOException {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String lineSeparator = System.getProperty("line.separator", "\n");
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			sb.append(line + lineSeparator);
		}
		fr.close();
		br.close();

		return sb.toString();
	}
	
    /**
     * 写入文件
     */
    public static void writeFile(String filename, Boolean append, String content) {
    	File file = new File(filename);
    	FileWriter fw = null;
    	BufferedWriter writer = null;
		try {
			fw = new FileWriter(file, append);
			writer = new BufferedWriter(fw);
			writer.write(content);
			writer.newLine();
			writer.flush(); //加不加此句都行，因为writer.close()时会刷新缓冲区的
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
    
	public static String readFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String lineSeparator = System.getProperty("line.separator", "\n");
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			sb.append(line + lineSeparator);
		}
		fr.close();
		br.close();

		return sb.toString();
	}

	/**
	 * 写入文件
	 */
	public static void writeFile(File file, Boolean append, String content) {
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file, append);
			writer = new BufferedWriter(fw);
			writer.write(content);
			writer.newLine();
			writer.flush(); // 加不加此句都行，因为writer.close()时会刷新缓冲区的
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return getUUID() + getSuffix(fileOriginName);
    }
    
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

	public static List<String> readFileByLines(String fileName) {
		List<String> lines = new ArrayList<String>();
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println(fileName + " not found.");
			return lines;
		}
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + ": " + tempString);
				lines.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return lines;
	}

	public static void writeFileByLines(String filename, Boolean append, List<String> lines) {
		System.out.println("write file '" + filename + "'");
		File file = new File(filename);
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file, append);
			writer = new BufferedWriter(fw);
			for (String line : lines) {
				// System.out.println(line);
				writer.write(line);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static boolean deleteFile(String filename) {
		boolean flag = false;
		File file = new File(filename);
		if (file.exists() && file.isFile()) {
			flag = file.delete();
		}
		return flag;
	}

	public static List<String> readFileByLines(File file) {
		List<String> lines = new ArrayList<String>();
		if (!file.exists()) {
			System.out.println(file.getName() + " not found.");
			return lines;
		}
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + ": " + tempString);
				lines.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return lines;
	}

	public static void writeFileByLines(File file, Boolean append, List<String> lines) {
		System.out.println("write file '" + file.getName() + "'");
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file, append);
			writer = new BufferedWriter(fw);
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public String readFileToString(File file) {
		String encoding = "UTF-8";
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

}
