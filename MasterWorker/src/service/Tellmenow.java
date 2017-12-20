package service;

import java.sql.Timestamp;

public class Tellmenow implements Service{

	public String run() {
		return new Timestamp(System.currentTimeMillis()).toString();		
	}
	
	public String toString(){
		return "tellmenow";
	}
}
