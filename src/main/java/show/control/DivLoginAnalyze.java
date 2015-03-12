package show.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivLoginAnalyze {
	
	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private LinkedBlockingQueue<Runnable> queueR = new LinkedBlockingQueue<Runnable>();
	ExecutorService executorService = new ThreadPoolExecutor(1, 4, 0l, TimeUnit.SECONDS, queueR);
	public void analyze(String path) throws IOException {
		// 读流
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str;
        while((str = reader.readLine()) != null ){
        	try {
				queue.put(str);
				executorService.submit(new doAnalyze());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        executorService.shutdown();
        reader.close();
	}
	
	public class doAnalyze implements Runnable{

		@Override
		public void run() {
			try {
				String str = queue.poll(1000, TimeUnit.SECONDS);
				System.out.println(str);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new DivLoginAnalyze().analyze("/Users/dongchao/dc_file/tmp/test.log");
	}
}
