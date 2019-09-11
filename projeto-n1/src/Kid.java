public class Kid extends Thread {

	boolean haveBall;
	String name;
	long playTime;
	long restTime;
	
	public Kid (String name, boolean haveBall, long playTime, long restTime) {
		super(name);
		this.haveBall = haveBall;
		this.playTime = playTime;
		this.restTime = restTime;
	}
	
	public Kid(String name) {
		super(name);
	}

	public void toPlay(long playTime){
		int time;
	}

	public void toRest(long restTime){

	}
	
	public void run() {
		for (int i=0; i<4; i++) {
			int x=0;
			for (int j=0; j<100000; j++) {
				for (int k=0; k<50000; k++) {
					x = x + 2;  
				}
			}
			System.out.println (getName() + " " + i);
		}
	}
	
	
}