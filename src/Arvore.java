public class Arvore<TIPO extends Comparable<TIPO>> {
    private Elemento<TIPO> raiz = null;

    public void adicionar(TIPO valor) {
        Elemento<TIPO> novoElemento = new Elemento<>(valor);
        if (this.raiz == null) {
            this.raiz = novoElemento; // Se a árvore estiver vazia, o novo elemento se torna a raiz
        } else {
            Elemento<TIPO> atual = this.raiz;

            while (true) {
                int comparacao = valor.compareTo(atual.getValor());
                if (comparacao == 0) {
                    break; // Valor já existe na árvore, saia do loop
                }

                if (comparacao < 0) {
                    if (atual.getEsquerda() == null) {
                        atual.setEsquerda(novoElemento); // Inserir à esquerda se o valor for menor
                        break;
                    }

                    atual = atual.getEsquerda();
                } else {
                    if (atual.getDireita() == null) {
                        atual.setDireita(novoElemento); // Inserir à direita se o valor for maior
                        break;
                    }

                    atual = atual.getDireita();
                }
            }
        }
    }

    public boolean buscar(TIPO valor) {
        return this.buscarRecursivo(this.raiz, valor); // Inicia a busca recursiva a partir da raiz
    }

    private boolean buscarRecursivo(Elemento<TIPO> elemento, TIPO valor) {
        if (elemento == null) {
            return false; // Valor não encontrado na árvore
        } else {
            int comparacao = valor.compareTo(elemento.getValor());
            if (comparacao == 0) {
                return true; // Valor encontrado na árvore
            } else {
                return comparacao < 0 ? this.buscarRecursivo(elemento.getEsquerda(), valor)
                        : this.buscarRecursivo(elemento.getDireita(), valor);
                // Continua a busca na subárvore esquerda se for menor, senão, na subárvore direita
            }
        }
    }

    public void imprimirEmOrdem() {
        this.imprimirEmOrdemRecursivo(this.raiz, 0);
    }

    private void imprimirEmOrdemRecursivo(Elemento<TIPO> elemento, int nivel) {
        if (elemento != null) {
            this.imprimirEmOrdemRecursivo(elemento.getDireita(), nivel + 1);

            for (int i = 0; i < nivel; ++i) {
                System.out.print("   "); // Espaços para formatação
            }

            System.out.println(elemento.getValor()); // Imprime o valor do elemento
            this.imprimirEmOrdemRecursivo(elemento.getEsquerda(), nivel + 1);
            // Continua a impressão na subárvore esquerda
        }
    }

    public void remover(TIPO valor) {
        this.raiz = this.removerRecursivo(this.raiz, valor); // Inicia a remoção recursiva a partir da raiz
    }

    private Elemento<TIPO> removerRecursivo(Elemento<TIPO> elemento, TIPO valor) {
        if (elemento == null) {
            return elemento; // Valor não encontrado, retorne o elemento atual
        } else {
            int comparacao = valor.compareTo(elemento.getValor());
            if (comparacao < 0) {
                elemento.setEsquerda(this.removerRecursivo(elemento.getEsquerda(), valor));
                // Continue a remoção na subárvore esquerda se for menor
            } else if (comparacao > 0) {
                elemento.setDireita(this.removerRecursivo(elemento.getDireita(), valor));
                // Continue a remoção na subárvore direita se for maior
            } else {
                if (elemento.getEsquerda() == null && elemento.getDireita() == null) {
                    return null; // Nó folha, simplesmente remova-o
                } else if (elemento.getEsquerda() == null) {
                    return elemento.getDireita(); // Nó com apenas um filho à direita
                } else if (elemento.getDireita() == null) {
                    return elemento.getEsquerda(); // Nó com apenas um filho à esquerda
                } else {
                    Elemento<TIPO> minimoDireita = this.encontrarMinimo(elemento.getDireita());
                    elemento.setValor(minimoDireita.getValor());
                    elemento.setDireita(this.removerRecursivo(elemento.getDireita(), minimoDireita.getValor()));
                    // Substitui o valor pelo mínimo da subárvore direita e continua a remoção nessa subárvore
                }
            }

            return elemento;
        }
    }

    private Elemento<TIPO> encontrarMinimo(Elemento<TIPO> elemento) {
        while (elemento.getEsquerda() != null) {
            elemento = elemento.getEsquerda();
        }

        return elemento; // Encontra e retorna o elemento mínimo da subárvore esquerda
    }
}
