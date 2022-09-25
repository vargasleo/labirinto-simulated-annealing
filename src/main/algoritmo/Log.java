package main.algoritmo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static main.algoritmo.Algoritmo.DIRECOES;

public class Log {

    // Tradução das direções para os seus devidos nomes.
    public static final Map<String, String> DIRECOES_NOME = Map.of(
            Arrays.toString(DIRECOES[0]),"NOROESTE",
            Arrays.toString(DIRECOES[1]),"NORTE",
            Arrays.toString(DIRECOES[2]),"NORDESTE",
            Arrays.toString(DIRECOES[3]),"OESTE",
            Arrays.toString(DIRECOES[4]),"LESTE",
            Arrays.toString(DIRECOES[5]),"SUDOESTE",
            Arrays.toString(DIRECOES[6]),"SUL",
            Arrays.toString(DIRECOES[7]),"SUDESTE"
        );

    private Log() {
    }

    public static void imprimirSolucao(List<int[]> solucao, String mensagem) {
        System.out.println(mensagem);
        System.out.print("[");
        for (int[] ints : solucao) {
            if (DIRECOES_NOME.get(Arrays.toString(ints)) != null) {
                System.out.print(DIRECOES_NOME.get(Arrays.toString(ints)) + " ");
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public static void aplicarSolucaoEm(List<int[]> solucao, char[][] labirinto) {
        var linha = 0;
        var coluna = 0;
        labirinto[linha][coluna] = 'X';

        for (var i = 0; i < solucao.size(); i++) {
            linha += solucao.get(i)[0];
            coluna += solucao.get(i)[1];
            labirinto[linha][coluna] = 'X';
        }
    }

    public static void imprimir(char[][] labirinto, String mensagem) {
        System.out.println(mensagem);
        for (char[] chars : labirinto) {
            for (int j = 0; j < labirinto.length; j++) System.out.print(chars[j] + "  ");
            System.out.println();
        }
    }

    public static void imprimirCaminho(List<int[]> solucao, char[][] labirinto, String mensagem) {
        var copy = copyOf(labirinto);
        aplicarSolucaoEm(solucao, copy);
        imprimir(copy, mensagem);
    }

    static char[][] copyOf(char[][] labirinto) {
        var copy = new char[labirinto.length][labirinto.length];
        for (int i = 0; i < labirinto.length; i++) {
            System.arraycopy(labirinto[i], 0, copy[i], 0, labirinto.length);
        }
        return copy;
    }
}
