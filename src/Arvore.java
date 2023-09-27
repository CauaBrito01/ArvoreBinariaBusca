public class Arvore<TIPO extends Comparable<TIPO>> {
    private Elemento<TIPO> raiz = null;

    public void adicionar(TIPO valor) {
        Elemento<TIPO> novoElemento = new Elemento<>(valor);
        if (this.raiz == null) {
            this.raiz = novoElemento;
        } else {
            Elemento<TIPO> atual = this.raiz;

            while (true) {
                int comparacao = valor.compareTo(atual.getValor());
                if (comparacao == 0) {
                    break;
                }

                if (comparacao < 0) {
                    if (atual.getEsquerda() == null) {
                        atual.setEsquerda(novoElemento);
                        break;
                    }

                    atual = atual.getEsquerda();
                } else {
                    if (atual.getDireita() == null) {
                        atual.setDireita(novoElemento);
                        break;
                    }

                    atual = atual.getDireita();
                }
            }
        }
    }

    public boolean buscar(TIPO valor) {
        return this.buscarRecursivo(this.raiz, valor);
    }

    private boolean buscarRecursivo(Elemento<TIPO> elemento, TIPO valor) {
        if (elemento == null) {
            return false;
        } else {
            int comparacao = valor.compareTo(elemento.getValor());
            if (comparacao == 0) {
                return true;
            } else {
                return comparacao < 0 ? this.buscarRecursivo(elemento.getEsquerda(), valor)
                        : this.buscarRecursivo(elemento.getDireita(), valor);
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
                System.out.print("   ");
            }

            System.out.println(elemento.getValor());
            this.imprimirEmOrdemRecursivo(elemento.getEsquerda(), nivel + 1);
        }
    }

    public void remover(TIPO valor) {
        this.raiz = this.removerRecursivo(this.raiz, valor);
    }

    private Elemento<TIPO> removerRecursivo(Elemento<TIPO> elemento, TIPO valor) {
        if (elemento == null) {
            return elemento;
        } else {
            int comparacao = valor.compareTo(elemento.getValor());
            if (comparacao < 0) {
                elemento.setEsquerda(this.removerRecursivo(elemento.getEsquerda(), valor));
            } else if (comparacao > 0) {
                elemento.setDireita(this.removerRecursivo(elemento.getDireita(), valor));
            } else {
                if (elemento.getEsquerda() == null && elemento.getDireita() == null) {
                    return null;
                } else if (elemento.getEsquerda() == null) {
                    return elemento.getDireita();
                } else if (elemento.getDireita() == null) {
                    return elemento.getEsquerda();
                } else {
                    Elemento<TIPO> minimoDireita = this.encontrarMinimo(elemento.getDireita());
                    elemento.setValor(minimoDireita.getValor());
                    elemento.setDireita(this.removerRecursivo(elemento.getDireita(), minimoDireita.getValor()));
                }
            }

            return elemento;
        }
    }

    private Elemento<TIPO> encontrarMinimo(Elemento<TIPO> elemento) {
        while (elemento.getEsquerda() != null) {
            elemento = elemento.getEsquerda();
        }

        return elemento;
    }
}
