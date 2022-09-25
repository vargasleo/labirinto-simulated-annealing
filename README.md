# Trabalho 1 - Labirinto

#### Inteligência Artificial, PUCRS - 2022/2.

_Gustavo Lopes, Jennifer Borges, Jennifer Klein, e Leonardo Vargas._

Trabalho desenvolvido para a disciplina de Inteligência Artificial, ministrada pela professora _Sílvia Moraes_.

    O trabalho consiste em implementar um algoritmo capaz de percorrer
    um labirinto de N x N**, com **N/2 células com comida, 
    utilizando algoritmos de busca com Informação através de refinamentos sucessivos.

## Premissas do problema

    1. O problema consiste numa matriz N x N, onde cada célula pode conter:
        1. '1' representa uma célula de parede, que não pode ser transpassada.
        2. '0' representa um caminho livre, que pode ser transpassado.
        3. 'C' representa uma célula que contém comida.
    2. Existe N/2 células com comida, distribuídas aleatoriamente.
    4. O agente pode se em qualquer uma das 8 direções adjacentes a sua posição atual.
    3. O algoritmo termina quando o agente passa por todas as células que contém comida.

## Entrada

    Qual a entrada de dados? (arquivos)
    Como o programa deve ser executado? (parâmetros, com logs ou sem logs)

## Saída e Observabilidade

    Qual a saída de dados? (solução ótima)

    Explicação da LabirintoSimulatedAnnealingUtils.java
    Logs de execução (tempo, memória, comida, passos, estado atual e vizinho, etc)

## Solução

### Simulated Annealing

**Conceito**

    Tal qual a têmpera dos metais, 
    ...

    O simulated annealing simula esse comportamento de resfriamento, 
    ...

**Explicação do algoritmo**

    Inicia-se tendo duas soluções: A atual e a vizinha.

    A atual é a melhor solução encontrada até o momento.
    A vizinha é gerada aplicando uma mutação na solução atual.

    A melhor solução é a que:
        a) possui mais comida
        b) possui a mesma quantidade de comida e menor custo.

    Se a vizinha for melhor que a atual, ela torna-se a atual.
    Se a vizinha for pior que a atual:
        Torna-se a atual com uma probabilidade de exp(-deltaE/T).
        deltaE = abs(energia da atual - energia da vizinha) e T é a temperatura.

    A cada iteração, a temperatura é reduzida.
    O algoritmo se repete até que a temperatura seja muito baixa.
    Assim, a solução atual é a melhor solução encontrada.

#### Definições dos parâmetros para o problema proposto

    1. Temperatura
    2. Cálculo da energia
    3. Cálculo da probabilidade de aceitação
    4. Mutação da solução vizinha

### Exibição dos resultados
    
    Qual o formato dos logs?
    O que eles significam?


## Análise dos resultados

    Como interpretar os resultados?
    Valeu a pena utilizar o algoritmo?

## Conclusão

    A motivação do grupo pela escolha do algoritmo de **simulated annealing** é
    que se permita escolher a pior solução entre aatual e a vizinha eventualmente,
    com uma probabilidade exponencialmente menor à medida que a execução avança.

    Assim, o algoritmo tende a convergir para uma solução melhor.
    Se não aceitássemos soluções piores, poderíamos ficar preso num mínimo local.
