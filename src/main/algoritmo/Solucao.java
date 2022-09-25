package main.algoritmo;

import java.util.HashMap;
import java.util.Map;

public class Solucao {

    //direções escolhidas pelo agente a cada passo da solução
    int[][] direcoes;
    //último índice onde o agente parou de andar (última posição válida da solução)
    int ultimoIndiceValido;
    // quantidade de comida que o agente conseguiu coletar na solução
    int comida;

    Map<String, Boolean> comidas = new HashMap<>();

    int linha;
    int coluna;

    public Solucao(int tamanho) {
        this.direcoes = new int[tamanho * tamanho][2];
        this.ultimoIndiceValido = 0;
        this.comida = 0;
        this.linha = 0;
        this.coluna = 0;
    }

    public void reset() {
        this.direcoes = new int[this.direcoes.length * this.direcoes.length][2];
        this.ultimoIndiceValido = 0;
        this.comida = 0;
    }
}