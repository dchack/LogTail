package show.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleLogAnalyze{

	public void analyze(String path) throws IOException {
		// 读流
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str;
        while((str = reader.readLine()) != null ){
        	System.out.println(str);
        }
	}

	public static void main(String[] args) throws IOException {
		new SimpleLogAnalyze().analyze("/Users/dongchao/dc_file/tmp/test.log");
	}
}
