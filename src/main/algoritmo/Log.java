package main.algoritmo;

import java.util.List;
import java.util.Map;

import static main.algoritmo.Algoritmo.DIRECOES;

public class Log {

    // Tradução das direções para os seus devidos nomes.
    public static final Map<String, int[]> DIRECOES_NOME = Map.of(
            "NOROESTE", DIRECOES[0],
            "NORTE", DIRECOES[1],
            "NORDESTE", DIRECOES[2],
            "OESTE", DIRECOES[3],
            "LESTE", DIRECOES[4],
            "SUDOESTE", DIRECOES[5],
            "SUL", DIRECOES[6],
            "SUDESTE", DIRECOES[7]
    );

    private Log() {
    }

    public static void imprimirSolucao(List<int[]> solucao, String mensagem) {
        System.out.println(mensagem);
        System.out.print("[");
        for (int[] ints : solucao) {
            if (DIRECOES_NOME.get(ints[0] + "" + ints[1]) != null) {
                System.out.print(DIRECOES_NOME.get(ints[0] + "" + ints[1]) + " ");
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
