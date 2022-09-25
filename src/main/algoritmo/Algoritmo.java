package main.algoritmo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static main.algoritmo.Log.*;

public final class Algoritmo {

    // Direções indicam as coordenadas de posições adjacentes a uma dada posição.
    public static final int[][] DIRECOES =
            new int[][]{
                    {-1, -1}, {0, -1}, {1, -1},
                    {-1, 0} /*{0, 0}*/, {1, 0},
                    {-1, 1}, {0, 1}, {1, 1}};
    private final int tamanho; // Labirinto quadrado, logo, o tamanho é o número de linhas e colunas.
    private final int comida; // Metade do tamanho do labirinto
    private final char[][] labirinto; // 'E'=Início, '0'=Caminho, '1'=Parede, 'C'=Comida
    private Solucao atual;
    private Solucao vizinha;

    // Para facilitar, fiz um construtor privado, para que a classe só possa ser instanciada a partir de um arquivo de labirinto.
    private Algoritmo(final char[][] labirinto) {
        this.labirinto = labirinto;
        this.tamanho = labirinto.length;
        this.comida = this.tamanho / 2;
        this.atual = new Solucao(tamanho);
        this.vizinha = new Solucao(tamanho);
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
        return new Algoritmo(matriz);
    }

    // Executa o algoritmo Simulated Annealing para encontrar uma solução para o labirinto.
    public void executar() {
        imprimir(labirinto, "Iniciando Simulated Annealing\nLABIRINTO");

        atual();
        imprimirSolucao(atual.direcoes, "Solução atual");
        imprimirCaminho(atual.direcoes, labirinto, "Caminho da solução atual");

        var temperatura = 1000.0;
        while (temperatura > 0.0001) {
            vizinha();
            imprimirSolucao(vizinha.direcoes, "Solução vizinha");
            imprimirCaminho(vizinha.direcoes, labirinto, "Caminho da solução vizinha");

            if (atual.comida == comida) break;
            if (vizinha.ultimoIndiceValido > atual.ultimoIndiceValido) swap();

            else {
                final var diffEnergia = Math.abs(vizinha.ultimoIndiceValido - atual.ultimoIndiceValido);
                final var chanceAceitePiorSolucao = Math.exp(-diffEnergia / temperatura);
                if (Math.random() < chanceAceitePiorSolucao) swap();
            }
            temperatura *= 0.9999;
        }
    }

    private void atual() {
        Random random = new Random();
        var linha = 0;
        var coluna = 0;
        var i = 0;
        atual.ultimoIndiceValido = 0;
        while (true) {
            var indiceDirecao = random.nextInt(DIRECOES.length);
            linha += DIRECOES[indiceDirecao][0];
            coluna += DIRECOES[indiceDirecao][1];
            if (linha < 0 || linha > tamanho - 1 || coluna < 0 || coluna > tamanho - 1) break;
            if (labirinto[linha][coluna] == 'C') {
                if (atual.comidas.get(linha + "," + coluna) == null || !atual.comidas.get(linha + "," + coluna)) {
                    atual.comidas.put(linha + "," + coluna, true);
                    vizinha.comida++;
                }
                atual.direcoes[i] = DIRECOES[indiceDirecao];
            } else if (labirinto[linha][coluna] == '0') atual.direcoes[i] = DIRECOES[indiceDirecao];
            else break;
            atual.ultimoIndiceValido++;
            i++;
        }
    }

    private void vizinha() {
        final var random = new Random();
        System.out.println(atual.ultimoIndiceValido);
        int indiceMutacao = atual.ultimoIndiceValido == 0 ? 0 : random.nextInt(atual.ultimoIndiceValido);
        vizinha = copiarAte(indiceMutacao, atual);
        var linha = vizinha.linha;
        var coluna = vizinha.coluna;
        int indice = indiceMutacao;
        while (true) {
            int indiceDirecao = random.nextInt(DIRECOES.length);
            linha += DIRECOES[indiceDirecao][0];
            coluna += DIRECOES[indiceDirecao][1];
            if (linha < 0 || linha > tamanho - 1 || coluna < 0 || coluna > tamanho - 1) break;
            if (labirinto[linha][coluna] == 'C') {
                if (atual.comidas.get(linha + "," + coluna) == null || !atual.comidas.get(linha + "," + coluna)) {
                    atual.comidas.put(linha + "," + coluna, true);
                    vizinha.comida++;
                }
                vizinha.direcoes[indice] = DIRECOES[indiceDirecao];
            } else if (labirinto[linha][coluna] == '0') vizinha.direcoes[indice] = DIRECOES[indiceDirecao];
            else break;
            vizinha.ultimoIndiceValido++;
            indice++;
        }
    }

    private Solucao copiarAte(int indiceMutacao, Solucao atual) {
        var copia = new Solucao(tamanho);
        copia.ultimoIndiceValido = indiceMutacao;
        copia.comida = 0;
        copia.comidas = new HashMap<>();
        var linha = 0;
        var coluna = 0;
        for (int i = 0; i < indiceMutacao; i++) {
            linha += atual.linha;
            coluna += atual.coluna;
            copia.direcoes[i] = atual.direcoes[i];
            if (labirinto[linha][coluna] == 'C') {
                if (atual.comidas.get(linha + "," + coluna) == null || !atual.comidas.get(linha + "," + coluna)) {
                    copia.comidas.put(linha + "," + coluna, true);
                    copia.comida++;
                }
            }
        }
        return copia;
    }

    private void swap() {
        final var aux = atual;
        atual = vizinha;
        vizinha = aux;
    }
}
