package pratica1;

public class SOThread extends Thread {

	public SOThread (String nome) {
		super (nome);
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
	
	public static void main(String[] args) {
		SOThread t1 = new SOThread ("Ceará");
		SOThread t2 = new SOThread ("Fortaleza");
		t1.start ();
		t2.start ();
		t1.setPriority(1);
		t2.setPriority(5);
	}
}