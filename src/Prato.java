public class Prato {
    private double lucro;
    private int custo;

    public Prato(int custo, int lucro) {
        this.custo = custo;
        this.lucro = lucro;
    }

    public int getCusto() {
        return custo;
    }

    public double getLucro(int numVezesRepitido) {
        if(numVezesRepitido == 0){
            return lucro;
        }
        else if(numVezesRepitido == 1) {
            return this.lucro/2;
        }else
            return 0;
    }
}
