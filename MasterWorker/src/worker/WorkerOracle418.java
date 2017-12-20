package worker;

import message.Response;

import message.Request;
import service.Oracle418;

public class WorkerOracle418 extends Worker {
	
	private Oracle418 or;
	
	public WorkerOracle418(int id) {
		super(id);
	}

	public void worker_node_init(Request params) {
		or = (Oracle418)params.getService();
	}

	public Response worker_send_response(Response resp) {
		resp = new Response(super.getRequest().getClient(), or, this);
		resp.addJobResult(getTotalTime(),getJobResult());
		return resp;
	}

	public boolean isCompatible(Request req){
		return req.getService() instanceof Oracle418;	
	}
	
	public Oracle418 getService(){
		return or;
	}
}