package worker;

import message.Response;

import message.Request;
import service.Tellmenow;

public class WorkerTellmenow extends Worker {

	private Tellmenow tmn;
	
	public WorkerTellmenow(int id) {
		super(id);
	}

	@Override
	public void worker_node_init(Request params) {
		tmn = (Tellmenow)params.getService();
	}

	@Override
	public Response worker_send_response(Response resp) {
		resp = new Response(super.getRequest().getClient(), tmn, this);
		resp.addJobResult(getTotalTime(),getJobResult());
		return resp;
	}

	public boolean isCompatible(Request req){
		return req.getService() instanceof Tellmenow;	
	}
	
	public Tellmenow getService(){
		return tmn;
	}
}
