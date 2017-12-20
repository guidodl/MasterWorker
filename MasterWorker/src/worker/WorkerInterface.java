package worker;


import message.Request;

public interface WorkerInterface{
	
	public void worker_handle_request(Request req);
	
	public void run();
	
	public int getId();
	
	public long getTotalTime();
	
	public String getJobResult();
	
	public Request getRequest();
}
