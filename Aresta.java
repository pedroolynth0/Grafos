public class Aresta {
    private int origem;
    private int destino;
    private   TipoAresta tipo;

    
    public Aresta(int origem, int destino, TipoAresta tipo) {
        this.origem = origem;
        this.destino = destino;
        this.tipo = tipo;
    }

    public int getOrigem() {
        return origem;
    }

    public int getDestino() {
        return destino;
    }

    public TipoAresta getTipo() {
        return tipo;
    }

    public void setTipo(TipoAresta tipo) {
        this.tipo = tipo;
    }
    public static enum TipoAresta {
        NAO_CLASSIFICADA,
        CRUZAMENTO,
        RETORNO,
        ARVORE
    }

}

