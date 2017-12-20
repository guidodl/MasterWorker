package service;

public class Oracle418 implements Service {

	public String run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "oracle418 done";
	}
	
	public String toString(){
		return "oracle418";
	}

}
