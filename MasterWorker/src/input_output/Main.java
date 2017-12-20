package input_output;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import master.Master;
import message.Request;
import message.Response;
import worker.Worker;
import worker.WorkerCountprimes;
import worker.WorkerFactory;
import worker.WorkerOracle418;
import worker.WorkerTellmenow;

public class Main {
	public static void main(String[] args){
		
		String path = System.getProperty("user.dir") + "/src/input_output/input_requests.txt";
		InputHandler input = new InputHandler(new File(path));
		
		ConcurrentLinkedQueue<Request> requests = input.getQueue();
		WorkerFactory workers = new WorkerFactory(50);
		long startTime = workers.getStartTime();
		Master master = new Master(requests, workers);
		
		Response resp = null;
		String service;
		int i = 0;
		for(Request req : requests){
			
			service = req.getService().toString();
			switch(service){
				case "countprimes":
					
					Worker cp = new WorkerCountprimes(i);
					master.send_request_to_worker(cp, req);
					resp = master.handle_worker_response(cp, resp);
					master.send_client_response(req.getClient(), resp);
					break;
				case "tellmenow":
					
					WorkerTellmenow tn = new WorkerTellmenow(i);
					master.send_request_to_worker(tn, req);
					resp = master.handle_worker_response(tn, resp);
					master.send_client_response(req.getClient(), resp);
					break;
				case "oracle418":	
					
					WorkerOracle418 or = new WorkerOracle418(i);
					master.send_request_to_worker(or, req);
					resp = master.handle_worker_response(or, resp);
					master.send_client_response(req.getClient(), resp);
					break;
			}
			i++;	
		}
		master.terminate();
		long totalTime = System.nanoTime() - startTime;
		
		for(Response res : master.getResponses()){
			res.setLoad((((float)res.getTime()*100)/(float)totalTime));
			System.out.println(res);

		}
	}
}
