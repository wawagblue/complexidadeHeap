/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heapbinario;

/**
 *
 * @author wagne
 */
/**
 * Java Program to Implement Binary Heap
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class BinaryHeap *
 */
class BinaryHeap {

    /**
     * The number of children each node has *
     */
    private static final int d = 2;
    private int heapSize;
    private int[] heap;

    /**
     * Constructor *
     */
    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    /**
     * Function to check if heap is empty *
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Check if heap is full *
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }

    /**
     * Clear heap
     */
    public void makeEmpty() {
        heapSize = 0;
    }

    /**
     * Function to get index parent of i *
     */
    private int parent(int i) {
        return (i - 1) / d;
    }

    /**
     * Function to get index of k th child of i *
     */
    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     * Function to insert element
     */
    public void insert(int x) throws IOException {
        if (isFull()) {
            throw new NoSuchElementException("Overflow Exception");
        }
        /**
         * Percolate up *
         */
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    /**
     * Function to find least element *
     */
    public int findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow Exception");
        }
        return heap[0];
    }

    /**
     * Function to delete min element *
     */
    public int deleteMin() {
        int keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    /**
     * Function to delete element at an index *
     */
    public int delete(int ind) {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow Exception");
        }
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        try {
            heapifyDown(ind);
        } catch (IOException ex) {

        }
        return keyItem;
    }

    /**
     * Function heapifyUp *
     */
    private void heapifyUp(int childInd) throws IOException {
        int tmp = heap[childInd];
        FileWriter arq;

        arq = new FileWriter("dadosInsert.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);
        int contador = 1;
        while (childInd > 0 && tmp < heap[parent(childInd)]) {
            contador++;
            heap[childInd] = heap[parent(childInd)];
            childInd = parent(childInd);
        }
        gravarArq.println("OpI\t" + contador + "\t" + heapSize);
        arq.close();
        heap[childInd] = tmp;
    }

    /**
     * Function heapifyDown *
     */
    private void heapifyDown(int ind) throws IOException {
        int child;
        int tmp = heap[ind];
        int contador = 0;
        FileWriter arq;

        arq = new FileWriter("dados.txt", true);

        PrintWriter gravarArq = new PrintWriter(arq);
        while (kthChild(ind, 1) < heapSize) {
            child = minChild(ind);

            contador++;
            if (heap[child] < tmp) {
                //System.out.println("passouMEnor\n");

                heap[ind] = heap[child];
            } else {
                break;
            }
            ind = child;
        }
        gravarArq.println("OpD\t" + contador + "\t" + heapSize);

        arq.close();
        heap[ind] = tmp;
    }

    /**
     * Function to get smallest child *
     */
    private int minChild(int ind) {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) {
            if (heap[pos] < heap[bestChild]) {
                bestChild = pos;
                //  System.out.println("passou\n");
            }
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

    /**
     * Function to print heap *
     */
    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
