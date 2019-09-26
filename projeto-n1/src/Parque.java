import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Parque {
    public Semaphore mutex;
    public Semaphore bolasCesto ;
    public Semaphore vagas;
    public List<Kid> kids;

    public Parque(int capacidadeCesta){
        //instanciando os semaforos
        mutex = new Semaphore(1);
        bolasCesto = new Semaphore(0) ;
        vagas = new Semaphore(capacidadeCesta);

        //instanciando a lista de criancas
        kids = new ArrayList<>();

    }

    public Kid addKid(boolean temBola, String nome, long tempoBrinca, long tempoDorme){
        Kid novaKid = new Kid(mutex,bolasCesto,vagas,temBola,nome,tempoBrinca,tempoDorme);
        kids.add(novaKid);
        novaKid.start();
        return novaKid;
    }


    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Semaphore getBolasCesto() {
        return bolasCesto;
    }

    public void setBolasCesto(Semaphore bolasCesto) {
        this.bolasCesto = bolasCesto;
    }

    public Semaphore getVagas() {
        return vagas;
    }

    public void setVagas(Semaphore vagas) {
        this.vagas = vagas;
    }

    public List<Kid> getKids() {
        return kids;
    }

    public void setKids(List<Kid> kids) {
        this.kids = kids;
    }
}
