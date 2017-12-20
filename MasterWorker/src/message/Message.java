package message;

import java.util.Random;

import client.Client;
import service.Service;

public class Message {

	private Client c;
	private Service s;
	private int id;
	protected boolean done;
	
	public Message(Client c, Service s){
		this.c = c;
		this.s = s;
		id = new Random().nextInt();
	}
	
	public Client getClient(){
		return c;
	}
	
	public Service getService(){
		return s;
	}
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		return "(" + c.getId() + "," + s.toString() + ")";	
	}
}
