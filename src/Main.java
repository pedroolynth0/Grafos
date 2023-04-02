import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Definindo variaveis
        Scanner inputUser = new Scanner(System.in);
        String nomeArq;
        File arquivo;
        Scanner sc;
        int numVertice;
  
        int vertices = 0;
        int arestas = 0;
        int origem = 0;
        int saida = 0;

        GrafoDirecionado grafo = new GrafoDirecionado();
        // ------------------------------------------------

        // Recebendo o input do usuario
        System.out.println("Digite o nome do arquivo: ");
        nomeArq = inputUser.nextLine();

        System.out.println("Digite o número de um dos vértices do grafo descrito no arquivo.");
        numVertice = Integer.parseInt(inputUser.nextLine());


        // ------------------------------------------------

        // abrindo o arquivo
        arquivo = new File(nomeArq);

        try {
            sc = new Scanner(arquivo);

            // ler quantidade de vertices e arestas 
            if (sc.hasNextLine()) {
                vertices = sc.nextInt();
                arestas = sc.nextInt();
                sc.nextLine();
            }
            //---------------------------------------
            //Adicionando arestas ao grafo
            while (sc.hasNextLine()) {
                origem = sc.nextInt();
                saida = sc.nextInt();

                grafo.adicionarAresta(origem, saida);
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            //---------------------------------------

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

        //Exibir informações do grafo
        System.out.println("(i) Grau de saída: " + grafo.grauDeEntrada(numVertice) + "\n");
        System.out.println("(ii) Grau de entrada: " + grafo.grauDeSaida(numVertice) + "\n");
        System.out.println("(iii) Conjunto de sucessores: " + grafo.predecessores(numVertice) + "\n");
        System.out.println("(iv) Conjunto de predecessores: " + grafo.sucessores(numVertice) + "\n");
        System.out.println("");
        grafo.buscaProfundidade(1); 
        grafo.printArestas();
        boolean flag = true;


        
        while(flag){

            
            try {
                int num1 = Integer.parseInt(inputUser.nextLine()) ;
                int num2 = Integer.parseInt(inputUser.nextLine()) ;


                System.out.println(grafo.classificarAresta(num1, num2)); 
                

            } catch (Exception e) {
                System.out.println("Deu erro");
                flag = false;
            }

        }
        //avanço
        System.out.println(grafo.classificarAresta(1,4));
        //avanço
        System.out.println(grafo.classificarAresta(1,3));
        //avore
        System.out.println(grafo.classificarAresta(1,2));
        //retorno
        System.out.println(grafo.classificarAresta(2,1));
        //retorno
        System.out.println(grafo.classificarAresta(3,1));
    }
}
