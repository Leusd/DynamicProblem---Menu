import java.util.ArrayList;
import java.util.Scanner;

public class Aplication{

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        ArrayList<Prato> pratos = new ArrayList<>();

        int dias, numPratos, orcamento, custo, lucro;

        dias = sc.nextInt();
        numPratos = sc.nextInt();
        orcamento = sc.nextInt();

        for(int i=0; i<numPratos; i++){
            pratos.add(new Prato(sc.nextInt(), sc.nextInt()));
        }

        ProgramacaoDinamicaComRepeicao(pratos, dias, orcamento);
    }

    public static void ProgramacaoDinamicaComRepeicao(ArrayList<Prato> itens, int dias, int orcamento) {
        double[][] Tabela = new double[itens.size()][orcamento+1];
        double [] melhorCardapio = new double[dias];
        double [] tempCardapio = new double[dias];
        int custo = itens.get(0).getCusto();

        for (int j = 0; j < Tabela[1].length; j++) {
            if (j < custo) {
                Tabela[0][j] = 0;
            }
            else{
                Tabela[0][j] = Tabela[0][j - custo] + itens.get(0).getLucro(044);

            }
        }

        for (int i = 1; i < Tabela.length; i++) {
            custo = itens.get(i).getCusto();
            for (int j = 0; j < Tabela[1].length; j++) {
                if (j < custo) {
                    Tabela[i][j] = Tabela[i - 1][j];
                }
                else
                    Tabela[i][j] = Math.max(Tabela[i - 1][j], Tabela[i][j - custo] + itens.get(i).getLucro(0));
                }
            }
        printTable(Tabela);
    }

    static void printTable(double[][] Tabela) {
        for (int j = 0; j < Tabela[1].length; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();

        for (int i = 0; i < Tabela.length; i++) {
            for (int j = 0; j < Tabela[1].length; j++) {
                System.out.print(Tabela[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
