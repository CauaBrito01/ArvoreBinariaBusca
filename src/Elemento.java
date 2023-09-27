public class Elemento<TIPO> {
    private TIPO valor;
    private Elemento<TIPO> esquerda;
    private Elemento<TIPO> direita;

    public Elemento(TIPO novoValor) {
        this.valor = novoValor;
        this.esquerda = null;
        this.direita = null;
    }

    public TIPO getValor() {
        return this.valor;
    }

    public void setValor(TIPO novoValor) {
        this.valor = novoValor;
    }

    public Elemento<TIPO> getEsquerda() {
        return this.esquerda;
    }

    public void setEsquerda(Elemento<TIPO> esquerda) {
        this.esquerda = esquerda;
    }

    public Elemento<TIPO> getDireita() {
        return this.direita;
    }

    public void setDireita(Elemento<TIPO> direita) {
        this.direita = direita;
    }
}
