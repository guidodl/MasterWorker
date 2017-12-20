package service;

public class CountPrimes implements Service{
	
	private int num;

	public CountPrimes(int num) {
		this.num = num;
	}

	public String run() {
		int result = 0;
		if(num == 0 || num == 1)
			return "0";
	    boolean[] table = new boolean[num];
	    for(int i = 2; i < num; i++) {
	        int j = 1;
	        if(!table[i])
	        	result++;
	        while(i * j < num) {
	        	table[i*j] = true;
	        	j++;
	        }
	    }
	    return String.valueOf(result);
	}
	
	public String toString(){
		return "countprimes";
	}
}
