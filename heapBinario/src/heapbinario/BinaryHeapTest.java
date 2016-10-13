/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heapbinario;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wagner
 */
/**
 * Class BinaryHeapTest *
 */
public class BinaryHeapTest {

    public static void main(String[] args) {
        dados();
    }

    public static void dados() {
        final int TAMANHO = 3000000;
        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        Scanner scan = new Scanner(System.in);

        BinaryHeap bh = new BinaryHeap(gerador.nextInt(10000) + 25000);

        BinomialHeap biBinomialHeap = new BinomialHeap();

        while (!bh.isFull()) {
            try {
                int aux = gerador.nextInt(TAMANHO);
                bh.insert(aux);
                biBinomialHeap.insert(aux);
            } catch (IOException ex) {
                Logger.getLogger(BinaryHeapTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        char ch;

        do {

            try {
                biBinomialHeap.delete(bh.deleteMin());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (!bh.isEmpty());
    }

}
