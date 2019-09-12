
public class Cesta {
    private int bolas = 0;

    public void insere(){
        this.bolas++;
    }

    public void retira(){
        this.bolas--;
    }

    public boolean vazia(){
        return this.bolas == 0;
    }
}
