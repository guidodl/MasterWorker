package worker;

import message.Request;
import message.Response;

public class Worker implements WorkerInterface, Runnable{

	private int id;
	private Request req;
	
	static long startTime, totalTime;
	static String result;
	
	public Worker(int id){
		this.id = id;
	}

	public void worker_handle_request(Request req) {
		this.req = req;
		
	}
	
	public void run() {
		if(isCompatible(req)){
			
			System.out.println("Starting Worker " + req.getService().toString() + "(" + id + ")");
			startTime = System.nanoTime();
			result = req.getService().run();
			totalTime = System.nanoTime()-startTime;
			System.out.println("Completed Worker " + req.getService().toString() + "("  + id + ")");
			
		}else throw new IllegalArgumentException("This worker is not compatible with the job");
	}
	
	public int getId(){
		return id;
	}
	
	public Response worker_send_response(Response resp){
		return null;
	}
		
	public long getTotalTime(){
		return totalTime;
	}
	
	public String getJobResult(){
		return result;
	}
	
	public Request getRequest(){
		return req;
	}
	
	public boolean isCompatible(Request req){
		return false;
	}

	public void worker_node_init(Request params) {}
}
