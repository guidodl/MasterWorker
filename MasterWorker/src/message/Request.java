package message;

import client.Client;
import service.Service;

public class Request extends Message {
	
	public Request(Client c, Service s){
		super(c, s);
		super.done = false;
	}
}
