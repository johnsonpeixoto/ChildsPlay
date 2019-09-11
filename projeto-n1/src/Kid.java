import java.util.concurrent.Semaphore;

public class Kid extends Thread {

	private boolean haveBall;
    private String nome;
    private long playTime;
    private long restTime;

	public Kid (String nome, boolean haveBall, long playTime, long restTime) {
		super(nome);
		this.haveBall = haveBall;
		this.playTime = playTime;
		this.restTime = restTime;

	public Kid(String nome) {
		super(nome);
	}

	public void toPlay(long playTime){

	    int time;
	}

	public void toRest(long restTime){

	}
	public void guardaBola() {

    }

    public void ficaQuieta() {

	}

	public void comBola() {
	    toPlay(); //brinca
        //down(slots);
        //down(mutex);
        guardaBola();
        //up(mutex);
        //up(bolasCesto);
        ficaQuieta();
    }

    public void semBola() {
       // down(bolasCesto);
        //down(mutex);
        //cesta.retiraBola();
        //up(mutex);
        //up(vagas);
	}



	public void run() {
        while(true) {
            if(haveBall)  {
                comBola();
            }
            else {
                semBola();
            }
        }

	}
	
	
}