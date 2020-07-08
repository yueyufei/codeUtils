package yyf.common.fille;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadResources {
	@SuppressWarnings("unchecked")
	public List<String> readResources(String path) {
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

	
	public List<String> getResourceField(String path) throws IOException {
		LinkedList<String> result = new LinkedList<>();
		InputStream in = getClass().getResourceAsStream(path);
		DataInputStream isr = new DataInputStream(in);
		BufferedReader d = new BufferedReader(new InputStreamReader(isr));
		String line;
		while ((line = d.readLine()) != null) {
			result.addFirst(line);
		}
		return result;
	}
	public static void main(String[] args) {
		ReadResources re = new ReadResources();
		List<String> lines = re.readResources("/123.txt");
		System.out.println(lines.toString());
	}

}
