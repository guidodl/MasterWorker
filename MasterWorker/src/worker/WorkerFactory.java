package worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerFactory {
	
	private ExecutorService executor;
	private long startTime;
	
	public WorkerFactory(int num){
		executor = Executors.newFixedThreadPool(num);
		startTime = System.nanoTime();
		
	}
	
	public ExecutorService getPool(){
		return executor;
	}
	
	public long getStartTime(){
		return startTime;
	}

}
