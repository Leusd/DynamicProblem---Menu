import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Aplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        ArrayList<Prato> pratos = new ArrayList<>();

        int dias, numPratos, orcamento, custo, lucro;

        dias = sc.nextInt();
        numPratos = sc.nextInt();
        orcamento = sc.nextInt();

        for (int i = 0; i < numPratos; i++) {
            pratos.add(new Prato(i + 1, sc.nextInt(), sc.nextInt()));
        }

        ProgramacaoDinamicaComRepeicao(pratos, dias, orcamento);
    }

    public static void ProgramacaoDinamicaComRepeicao(ArrayList<Prato> itens, int dias, int orcamento) {
        Item[][] Tabela = new Item[itens.size()][orcamento + 1];
        iniciarTabela(Tabela);

        Collections.sort (itens, new Comparator() {
            public int compare(Object o1, Object o2) {
                Prato p1 = (Prato) o1;
                Prato p2 = (Prato) o2;
                return Integer.compare(p1.getCusto(), p2.getCusto());
            }
        });

        int custo = itens.get(0).getCusto();
        for (int j = 0; j < Tabela[1].length; j++) {
            if (j < custo) {
                Tabela[0][j].lucro = 0;

            } else {
                Tabela[0][j].lucro = Tabela[0][j - custo].lucro + itens.get(0).getLucro(Tabela[0][j - custo].getRepeat(itens.get(0)));
                Tabela[0][j].pratos = new ArrayList<Prato>(Tabela[0][j - custo].pratos);

                Tabela[0][j].addPrato(itens.get(0));
            }
        }

        for (int i = 1; i < Tabela.length; i++) {
            custo = itens.get(i).getCusto();
            for (int j = 0; j < Tabela[1].length; j++) {
                if (j < custo) {
                    Tabela[i][j] = Tabela[i - 1][j];
                } else{
                    Item itemTabela = Tabela[i][j - custo];
                    int numeroRepPrato = Tabela[i][j - custo].getRepeat(itens.get(i));
                    double lucroPrato = itens.get(i).getLucro(numeroRepPrato);
                    double custoTotal = Tabela[i][j - custo].getCustoPratos() + itens.get(i).getCusto();
                    double x = 0;

                    if(custoTotal <= orcamento ){
                        x =  Math.max(Tabela[i - 1][j].lucro, Tabela[i][j - custo].lucro + lucroPrato);

                    }else{
                        x =  Math.max(Tabela[i - 1][j].lucro, Tabela[i][j - custo].lucro);
                    }

                    Tabela[i][j].lucro = x;
                    Tabela[i][j].pratos = new ArrayList<Prato>( Tabela[i][j - custo].pratos);

                    if(x != Tabela[i][j - custo].lucro){

                        Tabela[i][j].addPrato(itens.get(i));
                    }
                   }

            }
        }

        printTable(Tabela, itens);
        ArrayList <Prato> cardapio = Tabela[itens.size() -1][orcamento -1].pratos;
        for(Prato p : cardapio){
            System.out.print(p.getId()+ "\t");
        }


    }

    static void iniciarTabela(Item[][] Tabela){
        for (int i = 0; i < Tabela.length; i++) {
            for (int j = 0; j < Tabela[1].length; j++) {
                Tabela[i][j] = new Item();
            }
        }
    }
    static void printTable(Item[][] Tabela, ArrayList<Prato> pratos) {
        System.out.print( "ID\tC\tL\t\t");
        for (int j = 0; j < Tabela[1].length; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();

        for (int i = 0; i < Tabela.length; i++) {

            System.out.print(pratos.get(i).getId()+ "\t" + pratos.get(i).getCusto() + "\t"+ pratos.get(i).getLucro(0) + "\t\t");
            for (int j = 0; j < Tabela[1].length; j++) {
                System.out.print(Tabela[i][j].lucro + "\t");
            }
            System.out.println();
        }
    }
}
