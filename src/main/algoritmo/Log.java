package main.algoritmo;

import java.util.Map;

public class Log {

    // Tradução das direções para os seus devidos nomes.
    public static final Map<String, String> DIRECOES_NOME = Map.of(
            "-1-1", "NOROESTE",
            "-10", "OESTE",
            "-11", "SUDESTE",
            "01", "SUL",
            "11", "SUDESTE",
            "10", "LESTE",
            "1-1", "NORDESTE",
            "0-1", "NORTE"
    );

    private Log() {
    }

    public static void imprimirSolucao(int[][] solucao, String mensagem) {
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

    public static void aplicarSolucaoEm(int[][] solucao, char[][] labirinto) {
        labirinto[0][0] = 1;
        for (int i = 0; i < solucao.length; i++) {
            int linha = 0;
            int coluna = 0;
            for (int j = 0; j < i; j++) {
                linha += solucao[j][0];
                coluna += solucao[j][1];
            }
            if (linha < 0 || linha > labirinto.length - 1 || coluna < 0 || coluna > labirinto.length - 1) {
                break;
            }
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

    public static void imprimirCaminho(int[][] solucao, char[][] labirinto, String mensagem) {
        var copy = copyOf(labirinto);
        aplicarSolucaoEm(solucao, copy);
        imprimir(copy, mensagem);
    }

    private static char[][] copyOf(char[][] labirinto) {
        var copy = new char[labirinto.length][labirinto.length];
        for (int i = 0; i < labirinto.length; i++) {
            System.arraycopy(labirinto[i], 0, copy[i], 0, labirinto.length);
        }
        return copy;
    }
}
