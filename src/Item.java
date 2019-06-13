import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Item {
    ArrayList <Prato> pratos;
    double lucro;
    public Item() {
        this.pratos = new ArrayList<Prato>();
        this.lucro = 0;
    }
    public void addPrato(Prato p){
        Collections.sort (pratos, new Comparator() {
            public int compare(Object o1, Object o2) {
                Prato p1 = (Prato) o1;
                Prato p2 = (Prato) o2;
                return Double.compare(p1.getLucro(0), p2.getLucro(0));
            }
        });
        if(pratos.size()>= 2){
            pratos.remove(0);
        }
        pratos.add(new Prato(p.getId(), p.getCusto(), p.getLucro()));
    }
    public int getCustoPratos(){
        int custo = 0;
        for(Prato prato : pratos){
            custo+= prato.getCusto();
        }
        return custo;
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
