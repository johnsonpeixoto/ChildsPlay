
public class Main {

	public static void main(String[] args) {
		Kid t1 = new Kid ("Ceara");
		Kid t2 = new Kid ("Fortaleza");
		//t1.start ();
		//t2.start ();
		long t = System.currentTimeMillis();
		long elapsed;
		while (true) {
			elapsed = System.currentTimeMillis() - t;
			System.out.println(elapsed/1000);
		}
		
		/*
		t1.setPriority(1);
		t2.setPriority(5);
		*/
	}
}
