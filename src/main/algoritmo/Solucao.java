package main.algoritmo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solucao {

    //direções escolhidas pelo agente a cada passo da solução
    List<int[]> direcoes;

    // quantidade de comida que o agente conseguiu coletar na solução
    int comida;

    Map<String, Boolean> comidas = new HashMap<>();

    int linha;
    int coluna;

    public Solucao() {
        this.direcoes = new LinkedList<>();
        this.comida = 0;
        this.linha = 0;
        this.coluna = 0;
    }

    public void tentaComer(int linha, int coluna) {
        if (naoComeuAinda(linha, coluna)) come(linha, coluna);
    }

    private void come(int linha, int coluna) {
        comidas.put(linha + "," + coluna, true);
        comida++;
    }

    private boolean naoComeuAinda(int linha, int coluna) {
        return comidas.get(linha + "," + coluna) == null || !comidas.get(linha + "," + coluna);
    }

    public void andar(int[] direcao) {
        direcoes.add(direcao);
    }
}