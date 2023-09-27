

import java.util.Scanner;

public class Main {
    public Main() {
    }
//10, 20, 21 , 8, 9, 7
    public static void main(String[] args) {
        Arvore<Integer> arvore = new Arvore(); // Cria uma instância da classe Arvore para armazenar números inteiros
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite números para adicionar à árvore (digite 'para' para encerrar):");

        while(true) { // Inicia um loop infinito
            String input = scanner.nextLine(); // Lê uma linha de entrada do usuário
            System.out.println("numero adicionado, digite para pra parar");
            int numero;
            if (input.equalsIgnoreCase("para")) {
                System.out.println("Árvore binária em ordem:");
                arvore.imprimirEmOrdem(); // Chama o método para imprimir a árvore em ordem


                while(true) {
                    while(true) {
                        System.out.println("\nOpções: ");
                        System.out.println("1 - Procurar um número");
                        System.out.println("2 - Excluir um número");
                        System.out.println("3 - Encerrar");
                        System.out.print("Escolha uma opção: ");
                        input = scanner.nextLine();
                        if (input.equals("1")) {
                            System.out.println("");
                            System.out.print("Digite o número que deseja procurar: ");
                            numero = Integer.parseInt(scanner.nextLine());
                            if (arvore.buscar(numero)) {  // Chama o método para procurar o número na árvore
                                System.out.println("");
                                System.out.println("True");
                            } else {
                                System.out.println("");
                                System.out.println("False");
                            }
                        } else if (input.equals("2")) {
                            System.out.println("");
                            System.out.print("Digite o número que deseja excluir: ");
                            numero = Integer.parseInt(scanner.nextLine());
                            arvore.remover(numero); // Chama o método para remover o número da árvor
                            System.out.println("");
                            System.out.println("Árvore após a remoção do numero: " + numero);
                            arvore.imprimirEmOrdem();
                            System.out.println("");
                        } else {
                            if (input.equals("3")) {
                                return; // Encerra o programa
                            }

                            System.out.println("");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                }
            }
            // verifica se o passado é um numero se n for pede dnv
            // tenta converter ele para inteiro
            try {
                numero = Integer.parseInt(input);
                arvore.adicionar(numero);
            } catch (NumberFormatException var5) {
                System.out.println("Entrada inválida. Digite um número válido ou 'para' para encerrar.");
            }
        }
    }
}
