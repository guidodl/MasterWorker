package client;

import message.Response;

public class Client {

	private int id;
	private Response resp;

	public Client(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void addResponse(Response resp){
		this.resp = resp;
	}
}
