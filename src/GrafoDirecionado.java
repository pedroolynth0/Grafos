import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class GrafoDirecionado {

    //criando lista de adjacencia utilizando hash
    private Map<Integer, List<Integer>> adjacencia = new HashMap<>();
    public List<Integer> visitados = new ArrayList<>();
    private List<Aresta> arestas = new ArrayList<>();
    private Map<Integer, Integer> descoberta = new HashMap<>();
    private Map<Integer, Integer> termino = new HashMap<>();
    private Map<Integer, Integer> pai = new HashMap<>();

    //Adicionar aresta
    public void adicionarAresta(int origem, int destino) {
        if (!adjacencia.containsKey(origem)) {
            adjacencia.put(origem, new ArrayList<>());
        }
        adjacencia.get(origem).add(destino);
        arestas.add(new Aresta(origem, destino, Aresta.TipoAresta.NAO_CLASSIFICADA));
    }
    public void printArestas() {
        for (Aresta aresta : arestas) {
            if (!(aresta.getTipo() ==  Aresta.TipoAresta.NAO_CLASSIFICADA)) {
                System.out.println("Aresta: " + aresta.getOrigem() + " -> " + aresta.getDestino() + " Tipo: " + aresta.getTipo());

                
            }
            
            
        }
    }

    //Calcular grau de saida
    public int grauDeSaida(int vertice) {

        int tamanho= 0;
        //Se a lista de adjacencia possuir o vertice ele calcula o tamanho
        if (adjacencia.containsKey(vertice)) {
            tamanho =  adjacencia.get(vertice).size();
        }
        return tamanho;
    }
    
    //Grau de entrada
    public int grauDeEntrada(int vertice) {
        int grau = 0;

        // É feito um forEach para calcular o grau dos vizinhos 
        for (List<Integer> vizinhos : adjacencia.values()) {
            if (vizinhos.contains(vertice)) {
                grau++;
            }
        }
        return grau;
    }
    
    //Lista de sucessores
    public List<Integer> sucessores(int vertice) {
        List<Integer> tmp = new ArrayList<>();
        //se o vertice existir tmp recebe os sucessores do vertice
        if (adjacencia.containsKey(vertice)) {
            tmp = adjacencia.get(vertice);
        }
        //ordenar saida
        Collections.sort(tmp);
        return tmp;
    }
    
    //Lista de predecessores
    public List<Integer> predecessores(int vertice) {
        List<Integer> predecessores = new ArrayList<>();

        // É feito um forEach para encotrar todos os predecessores do vertice
        for (int i : adjacencia.keySet()) {
            if (adjacencia.get(i).contains(vertice)) {
                predecessores.add(i);
            }
        }
        //ordenar saida
        Collections.sort(predecessores);
        return predecessores;
    }


    public void buscaProfundidade(int inicio) {
        Stack<Integer> pilha = new Stack<>();
        int tempo = 1;
    
        // Adiciona o vértice inicial na pilha e marca como visitado
        pilha.push(inicio);
        visitados.add(inicio);
        descoberta.put(inicio, tempo);
        
        pai.put(inicio, 0);
        String busca= "Busca = ";
    
        while (!pilha.isEmpty()) {
            int atual = pilha.peek();
            boolean visitouFilho = false;
            busca = busca + atual + " ";
            
    
            // Verifica se existem filhos não visitados e adiciona o primeiro na pilha
            for (int filho : sucessores(atual)) {
                if (!visitados.contains(filho)) {
                    pilha.push(filho);
                    visitados.add(filho);
                    descoberta.put(filho, ++tempo);
                    if (termino.get(atual) == null) {
                        pai.put(filho, atual);
                    }
                    
                    visitouFilho = true;
                    break;
                }
            }
    
            // Se todos os filhos já foram visitados, remove o vértice atual da pilha e marca o tempo de término
            if (!visitouFilho) {
                pilha.pop();
                termino.put(atual, ++tempo);
            }
        }
    
        //Imprime os tempos de descoberta e término para cada vértice e seu pai
/*         System.out.println("Vértice Inicial: " + inicio);
        for (int vertice : descoberta.keySet()) {
           System.out.println("Vertice " + vertice + ": Descoberta = " + descoberta.get(vertice) 
              + ", Termino = " + termino.get(vertice) + ", Pai = " + pai.get(vertice));
        }
       */
    }
    
    public enum TipoAresta {
        ARVORE,
        RETORNO,
        AVANCO,
        CRUZAMENTO
    }
    
    public TipoAresta classificarAresta(int origem, int destino) {
        int descOrigem = descoberta.get(origem);
        int termOrigem = termino.get(origem);
        int descDestino = descoberta.get(destino);
        int termDestino = termino.get(destino);

        if(pai.get(destino) != null &&   pai.get(destino) == origem){
            return TipoAresta.ARVORE;
        }
        //tempo de descoberta do pai é menor que o do destino e termino do pai é maior que do destino
        else if (descOrigem > descDestino && termOrigem < termDestino) {
            return TipoAresta.RETORNO;
        } else if (descOrigem < descDestino  && termOrigem > termDestino) {
            return TipoAresta.AVANCO;
        } else if (descOrigem > termDestino) {
            return TipoAresta.CRUZAMENTO;
        } else {
            return TipoAresta.ARVORE;
        }
    }

}