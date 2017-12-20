package input_output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import client.Client;
import message.Request;
import service.CountPrimes;
import service.Oracle418;
import service.Tellmenow;

public class InputHandler {

	//Fields
	private File file;
	private Scanner sc;
	public ConcurrentLinkedQueue<Request> queue;
	
	//Constructor
	public InputHandler(File file){
		this.file = file;
		try {
			inputMap(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void inputMap(File file) throws FileNotFoundException {
		sc = new Scanner(file);
		sc.nextLine();
		String[] line;
		int id, num;
		Client c;
		queue = new ConcurrentLinkedQueue<Request>();
		while(sc.hasNextLine()){
			line = sc.nextLine().split(",");
			id = Integer.parseInt(line[0]);
			num = Integer.parseInt(line[2]);
			switch(line[1]){
				case "tellmenow":
					c = new Client(id);
					Tellmenow tmn = new Tellmenow();
					queue.add(new Request(c,tmn));
					break;
				case "418Oracle":
					c = new Client(id);
					Oracle418 or = new Oracle418();
					queue.add(new Request(c,or));
					break;
				case "countPrimes":
					c = new Client(id);
					CountPrimes cp = new CountPrimes(num);
					queue.add(new Request(c,cp));
					break;
			}			
		}
	}
	
	public ConcurrentLinkedQueue<Request> getQueue(){
		return queue;
	}
}
