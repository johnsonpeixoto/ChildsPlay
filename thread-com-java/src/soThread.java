public class soThread extends Thread {
    public soThread (String nome){
        super (nome);
    }

    public void run(){
        // Questão 1
        for (int i=0; i<4; i++) {
            int x=0;
            for (int j=0; j<100000; j++) {
                for (int k=0; k<50000; k++) {
                    x = x + 2;
                }
            }
            System.out.println (getName() + " " + i);
        }
        /*
        Questão 2
        try {
            sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println (getName() + " " + i);
         */

    }

    public static void main(String[] args) {
        soThread t1 = new soThread ("Ceará");
        soThread t2 = new soThread ("Fortaleza");

        t1.start();
        t2.start();

        t1.setPriority(5);
        t2.setPriority(1);

    }
}