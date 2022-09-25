package main.algoritmo;

import java.io.IOException;

import static main.algoritmo.Algoritmo.fromFile;

public class Main {

    public static void main(String[] args) throws IOException {
        fromFile(args[0]).executar();
    }
}
