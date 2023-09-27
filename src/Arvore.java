public class Arvore<TIPO extends Comparable> {
    private Elemento<TIPO> raiz = null; // Cria um elemento raiz que inicialmente é nulo

    public void adicionar(TIPO valor) {
        Elemento<TIPO> novoElemento = new Elemento(valor); // Método para adicionar um valor à árvore
        if (this.raiz == null) { //verifica se a arvore esta vazia
            this.raiz = novoElemento; // Se estiver vazia, o novo elemento se torna a raiz
        } else {
            Elemento<TIPO> atual = this.raiz; // Inicia a busca a partir da raiz

            while(true) {  // Inicia um loop para encontrar a posição correta para inserir o novo elemento
                int comparacao = valor.compareTo(atual.getValor()); // Compara o valor com o valor atual do elemento
                if (comparacao == 0) {  // Se forem iguais, o valor já existe na árvore, então saímos do loop
                    break;
                }

                if (comparacao < 0) { // Se o valor for menor que o valor atual
                    if (atual.getEsquerda() == null) { // Se não houver elemento à esquerda, insira o novo elemento aqui
                        atual.setEsquerda(novoElemento);
                        break;
                    }

                    atual = atual.getEsquerda();  // Caso contrário, continue a busca na subárvore esquerda
                } else { // Se o valor for maior que o valor atual
                    if (atual.getDireita() == null) { // Se não houver elemento à direita, insira o novo elemento aqui
                        atual.setDireita(novoElemento);
                        break;
                    }

                    atual = atual.getDireita(); // Caso contrário, continue a busca na subárvore direita
                }
            }
        }

    }

    public boolean buscar(TIPO valor) {
        return this.buscarRecursivo(this.raiz, valor); // Chama o método de busca recursiva a partir da raiz
    }

    private boolean buscarRecursivo(Elemento<TIPO> elemento, TIPO valor) { // Método de busca recursiva
        if (elemento == null) { // Se o elemento atual for nulo, o valor não foi encontrado
            return false;
        } else {
            int comparacao = valor.compareTo(elemento.getValor()); // Compara o valor com o valor atual do elemento
            if (comparacao == 0) { // Se forem iguais, o valor foi encontrado
                return true;
            } else {
                return comparacao < 0 ? this.buscarRecursivo(elemento.getEsquerda(), valor) : this.buscarRecursivo(elemento.getDireita(), valor);
                // Se o valor for menor, continue a busca na subárvore esquerda, caso contrário, na subárvore direita
            }
        }
    }

    public void imprimirEmOrdem() {
        this.imprimirEmOrdemRecursivo(this.raiz, 0);
    }

    private void imprimirEmOrdemRecursivo(Elemento<TIPO> elemento, int nivel) {
        if (elemento != null) { // Se o elemento atual não for nulo
            this.imprimirEmOrdemRecursivo(elemento.getDireita(), nivel + 1);

            for(int i = 0; i < nivel; ++i) {  // Imprime espaços para formatar a saída
                System.out.print("   ");
            }

            System.out.println(elemento.getValor()); // Imprime o valor do elemento atual
            this.imprimirEmOrdemRecursivo(elemento.getEsquerda(), nivel + 1); // Imprime a subárvore esquerda
        }

    }

    public void remover(TIPO valor) {
        this.raiz = this.removerRecursivo(this.raiz, valor);
    }

    private Elemento<TIPO> removerRecursivo(Elemento<TIPO> elemento, TIPO valor) {
        if (elemento == null) { // Se o elemento atual for nulo, o valor não foi encontrado
            return elemento;
        } else {
            int comparacao = valor.compareTo(elemento.getValor()); // Compara o valor com o valor atual do elemento
            if (comparacao < 0) { // Se o valor for menor, continue a busca na subárvore esquerda
                elemento.setEsquerda(this.removerRecursivo(elemento.getEsquerda(), valor));
            } else if (comparacao > 0) { // Se o valor for maior, continue a busca na subárvore direita
                elemento.setDireita(this.removerRecursivo(elemento.getDireita(), valor));
            } else {
                if (elemento.getEsquerda() == null) {  // Se não houver subárvore esquerda, retorne a subárvore direita
                    return elemento.getDireita();
                }

                if (elemento.getDireita() == null) { // Se não houver subárvore direita, retorne a subárvore esquerd
                    return elemento.getEsquerda();
                }

                Elemento<TIPO> minimoDireita = this.encontrarMinimo(elemento.getDireita());
                elemento.setDireita(this.removerRecursivo(elemento.getDireita(), (TIPO) minimoDireita.getValor()));
                // Substitui o elemento atual pelo menor elemento da subárvore direita
            }

            return elemento;
        }
    }

    private Elemento<TIPO> encontrarMinimo(Elemento<TIPO> elemento) {
        while(elemento.getEsquerda() != null) {  // Percorre a subárvore até encontrar o elemento mais à esquerda
            elemento = elemento.getEsquerda();
        }

        return elemento;
    }
}
