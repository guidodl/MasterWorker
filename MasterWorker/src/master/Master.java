package master;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import client.Client;
import message.Request;
import message.Response;
import worker.Worker;
import worker.WorkerFactory;

public class Master {

	private ConcurrentLinkedQueue<Request> requests;
	private ConcurrentLinkedQueue<Response> responses;
	private WorkerFactory wf;
	private ExecutorService executor;

	public Master(ConcurrentLinkedQueue<Request> requests, WorkerFactory wf){
		this.requests = requests;
		responses =  new ConcurrentLinkedQueue<Response>();
		this.wf = wf;
		executor = wf.getPool();
    }
	
	public Response handle_worker_response(Worker worker, Response resp){
		return worker.worker_send_response(resp);
	}
	
	public void send_client_response(Client client, Response resp){
		client.addResponse(resp);
		responses.add(resp);
	}
	
	public void send_request_to_worker(Worker worker, Request req){
		worker.worker_node_init(req);
		worker.worker_handle_request(req);
		wf.getPool().execute(worker);
		
	}
	
	public void terminate(){
		executor.shutdown();
		System.out.println("All tasks submitted.");
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException ignored) {}
		System.out.println("All tasks completed.");
	}
	
	public ConcurrentLinkedQueue<Response> getResponses(){
		return responses;
	}
}
