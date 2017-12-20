package message;

import client.Client;
import service.Service;
import worker.Worker;

public class Response extends Message {
	
	private Worker w;
	private long time;
	private String result;
	private float load;
	
	public Response(Client c, Service s, Worker w){
		super(c, s);
		this.w = w;
		super.done = true;
	}
	
	public void addJobResult(long time, String result){
		this.time = time;
		this.result = result;	
	}
	
	public String toString(){
		return super.toString() + " -> (worker: " + w.getId() + ",time: " + time + ",workload: " + load +"%)";
	}
	
	public long getTime(){
		return time;
	}
	
	public void setLoad(float f){
		this.load = f;
	}
	
	public String getResult(){
		return result;
	}
}
