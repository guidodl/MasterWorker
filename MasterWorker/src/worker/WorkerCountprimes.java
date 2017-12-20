package worker;

import message.Response;

import message.Request;
import service.CountPrimes;

public class WorkerCountprimes extends Worker {
	
	private CountPrimes cp;
	
	public WorkerCountprimes(int id) {
		super(id);
	}

	@Override
	public void worker_node_init(Request params) {
		if(isCompatible(params))
			cp = (CountPrimes)params.getService();
	}

	@Override
	public Response worker_send_response(Response resp) {
		resp = new Response(super.getRequest().getClient(), cp, this);
		resp.addJobResult(getTotalTime(),getJobResult());
		return resp;
	}

	public boolean isCompatible(Request req){
		return req.getService() instanceof CountPrimes;	
	}
	
	public CountPrimes getService(){
		return cp;
	}
}