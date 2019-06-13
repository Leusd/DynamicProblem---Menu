import java.lang.reflect.Array;
import java.util.ArrayList;

public class Item {
    ArrayList <Prato> pratos;
    double lucro;
    public Item() {
        this.pratos = new ArrayList<Prato>();
        this.lucro = 0;
    }
    public int getRepeat(Prato p){
        int r = 0;
        for(Prato prato : pratos){
            if(p.getId() == prato.getId()) r++;
        }
        return r;
    }
    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }




}
