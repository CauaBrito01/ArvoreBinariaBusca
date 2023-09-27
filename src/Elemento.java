public class Elemento<TIPO> {
    private TIPO valor; // Variável para armazenar o valor do elemento
    private Elemento<TIPO> esquerda; // Referência ao elemento à esquerda
    private Elemento<TIPO> direita; // Referência ao elemento à direita

    public Elemento(TIPO novoValor) { // Construtor da classe Elemento
        this.valor = novoValor; // Inicializa o valor do elemento com o valor especificado
        this.esquerda = null; // Inicializa a referência à esquerda como nula
        this.direita = null; // Inicializa a referência à direita como nula
    }

    public TIPO getValor() { // Método para obter o valor do elemento
        return this.valor;
    }

    public void setValor() { // Método para definir o valor do elemento (parece estar incorreto, pois não recebe um novo valor)
        this.valor = this.valor;
    }

    public Elemento<TIPO> getEsquerda() { // Método para obter a referência ao elemento à esquerda
        return this.esquerda;
    }

    public void setEsquerda(Elemento<TIPO> esquerda) { // Método para definir a referência ao elemento à esquerda
        this.esquerda = esquerda;
    }

    public Elemento<TIPO> getDireita() { // Método para obter a referência ao elemento à direita
        return this.direita;
    }

    public void setDireita(Elemento<TIPO> direita) { // Método para definir a referência ao elemento à direita
        this.direita = direita;
    }
}
