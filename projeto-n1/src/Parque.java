import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Parque {
    public Semaphore mutex;
    public Semaphore bolasCesto ;
    public Semaphore vagas;

    List<Kid> kids;

    public Parque(int capacidadeCesta){
        //instanciando os semaforos
        mutex = new Semaphore(1);
        bolasCesto = new Semaphore(0) ;
        vagas = new Semaphore(capacidadeCesta);

        //instanciando a lista de criancas
        kids = new ArrayList<>();

    }

    public void addKid(boolean temBola, String nome, long tempoBrinca, long tempoDorme){
        Kid novaKid = new Kid(mutex,bolasCesto,vagas,temBola,nome,tempoBrinca,tempoDorme);
        kids.add(novaKid);
        novaKid.start();
    }

    public void killKid(String nome){
        //funcao anonima
        kids.forEach((kid) -> {
            if(nome == kid.getName()) {
                kid.stop();
                kids.remove(kid);
            }
        });
    }
}
