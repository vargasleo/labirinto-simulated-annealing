package main.algoritmo;

import java.util.Map;

import static main.algoritmo.Log.*;

public class DirecoesConceito {

    // Direções indicam as coordenadas de posições adjacentes a uma dada posição.
    public static final int[][] DIRECOES =
            new int[][]{
                    {-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1} /*{0, 0}*/, {0, 1},
                    {1, -1}, {1, 0}, {1, 1}};

    public static final Map<String, int[]> NOME_DIRECOES = Map.of(
            "NOROESTE", DIRECOES[0],
            "NORTE", DIRECOES[1],
            "NORDESTE", DIRECOES[2],
            "OESTE", DIRECOES[3],
            "LESTE", DIRECOES[4],
            "SUDOESTE", DIRECOES[5],
            "SUL", DIRECOES[6],
            "SUDESTE", DIRECOES[7]
    );

    public static void main(String[] args) {
        var direcoes = new int[5][2];
        var labirinto = new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}};
        var caminho = new char[][]{
                {'1', '0', '0'},
                {'1', '0', '0'},
                {'1', '1', '1'}};
        direcoes[0] = NOME_DIRECOES.get("SUL");
        direcoes[1] = NOME_DIRECOES.get("SUL");
        direcoes[2] = NOME_DIRECOES.get("LESTE");
        direcoes[3] = NOME_DIRECOES.get("LESTE");

        var copy = copyOf(labirinto);
        aplicarSolucaoEm(direcoes, copy);
        imprimir(copy, "caminhos");
    }

    public static void aplicarSolucaoEm(int[][] solucao, char[][] labirinto) {
        var linha = 0;
        var coluna = 0;
        labirinto[linha][coluna] = 'X';
        for (var i = 0; i < solucao.length; i++) {
            linha += solucao[i][0];
            coluna += solucao[i][1];
            labirinto[linha][coluna] = 'X';
        }
    }
}
