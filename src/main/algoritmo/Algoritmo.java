package main.algoritmo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.algoritmo.Log.*;

public final class Algoritmo {

    private static final Random random = new Random();
    // Direções indicam as coordenadas de posições adjacentes a uma dada posição.
    public static final int[][] DIRECOES =
            new int[][]{
                    {-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1} /*{0, 0}*/, {0, 1},
                    {1, -1}, {1, 0}, {1, 1}};
    public static final double TEMPERATURA_MAXIMA = 10000.0;
    public static final double TEMPERATURA_MINIMA = 0.0001;
    public static final double REDUCAO_TEMPERATURA_POR_ITERACAO = 0.9999;
    private final int tamanho; // Labirinto quadrado, logo, o tamanho é o número de linhas e colunas.
    private final int comidaTotal; // Metade do tamanho do labirinto
    private final char[][] labirinto; // 'E'=Início, '0'=Caminho, '1'=Parede, 'C'=Comida
    private Solucao atual;

    // Para facilitar, fiz um construtor privado, para que a classe só possa ser instanciada a partir de um arquivo de labirinto.
    private Algoritmo(final char[][] labirinto) {
        this.labirinto = labirinto;
        this.tamanho = labirinto.length;
        this.comidaTotal = this.tamanho / 2;
        this.atual = new Solucao();
    }

    // Equivalente ao construtor, mas lê o labirinto de um arquivo no formato de exemplo especificado no enunciado.
    public static Algoritmo fromFile(final String nome) throws IOException {
        final var reader = new BufferedReader(new FileReader(nome));
        final var tamanho = Integer.parseInt(reader.readLine());
        final var matriz = new char[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            final var linha = reader.readLine().replace(" ", "").toCharArray();
            matriz[i] = linha;
        }
        reader.close();
        return new Algoritmo(matriz);
    }

    // Executa o algoritmo Simulated Annealing para encontrar uma solução para o labirinto.
    public void executar() {
        imprimir(labirinto, "Iniciando Simulated Annealing\nLABIRINTO");

        atual();
        imprimirSolucao(atual.direcoes, "Solução atual");
        imprimirCaminho(atual.direcoes, labirinto, "Caminho da solução atual");

        var temperatura = TEMPERATURA_MAXIMA;
        while (temperatura > TEMPERATURA_MINIMA) {
            // System.out.println("Temperatura: " + temperatura + "ºC");

            var direcoes = sucessor();
            var direcao = direcoes.get(random.nextInt(direcoes.size())); // Usar heuristica para escolher a direção

            atual.andar(direcao);

            if (isComida(atual.linha, atual.coluna)) {
                atual.tentaComer(atual.linha, atual.coluna);
            }

            // imprimirSolucao(vizinha.direcoes, "Solução vizinha");
            // imprimirCaminho(vizinha.direcoes, labirinto, "Caminho da solução vizinha");
            if (atual.comida == comidaTotal) break;
            else {
                // final var diffEnergia = Math.abs(vizinha.comida - atual.comida);
                // final var chanceAceitePiorSolucao = Math.exp(-diffEnergia / temperatura);
                // if (Math.random() < chanceAceitePiorSolucao) swap();
            }
            temperatura *= REDUCAO_TEMPERATURA_POR_ITERACAO;
        }
        imprimirSolucao(atual.direcoes, "Solução vizinha");
        imprimirCaminho(atual.direcoes, labirinto, "Caminho da solução vizinha");
    }

    private void atual() {
        var linha = 0;
        var coluna = 0;
        var movimentos = 0;
        while (movimentos < 2) {

            var direcao = direcaoAleatoria();

            int tlinha = linha + DIRECOES[direcao][0];// incrementa a linha conforme a direção
            int tcoluna = coluna + DIRECOES[direcao][1];// incrementa a coluna conforme a direção

            if (isForaDoLabirinto(tlinha, tcoluna)) continue;
            if (isParede(tlinha, tcoluna)) continue;
            if (isComida(tlinha, tcoluna)) atual.tentaComer(tlinha, tcoluna);

            atual.andar(DIRECOES[direcao]);

            linha = tlinha;
            coluna = tcoluna;
            movimentos++;
        }
    }

    private boolean isParede(int linha, int coluna) {
        return labirinto[linha][coluna] == '1';
    }

    private int direcaoAleatoria() {
        return random.nextInt(DIRECOES.length);
    }

    private boolean isComida(int linha, int coluna) {
        if (labirinto[linha][coluna] == 'C') {
            System.out.println("Comida encontrada em " + linha + ", " + coluna + "! Comida: " + atual.comida);
            return true;
        }
        return false;
    }

    private boolean isForaDoLabirinto(int linha, int coluna) {
        return linha < 0 || linha > tamanho - 1 || coluna < 0 || coluna > tamanho - 1;
    }

    private List<int[]> sucessor() {
        final var direcoes = new ArrayList<int[]>();
        for (int i = 0; i < DIRECOES.length; i++) {
            final var linha = atual.linha + DIRECOES[i][0];
            final var coluna = atual.coluna + DIRECOES[i][1];
            if (isForaDoLabirinto(linha, coluna)) continue;
            if (isParede(linha, coluna)) continue;
            direcoes.add(DIRECOES[i]);
        }
        return direcoes;
    }
}
