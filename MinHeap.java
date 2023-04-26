/** 
    Modified from Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.lang.Comparable;

/** Min-heap implementation */
public class MinHeap<E extends Comparable<? super E>> {
//public class MinHeap<E extends Comparable> {
  private E[] Heap;   // Pointer to the heap array
  private int size;   // Maximum size of the heap
  private int n;      // Number of things in heap

@SuppressWarnings("unchecked") // Generic array allocation
public MinHeap(int max) { 
	Heap = (E[]) new Comparable[max];
	n = 0;  
	size = max; 
}

/** Return current size of the heap */
public int heapsize() { return n; }

/** Is pos a leaf position? */
private boolean isLeaf(int pos)
{ return (pos >= n/2) && (pos < n); }

/** Return position for left child of pos */
private int leftchild(int pos) {
  assert pos < n/2 : "Position has no left child";
  return 2*pos + 1;
}

/** Return position for right child of pos */
private int rightchild(int pos) {
  assert pos < (n-1)/2 : "Position has no right child";
  return 2*pos + 2;
}

/** Return position for parent */
private int parent(int pos) {
  assert pos > 0 : "Position has no parent";
  return (pos-1)/2;
}

/** Insert into heap */
public void insert(E val) {
  assert n < size : "Heap is full";
  int curr = n ++;
  Heap[curr] = val;                 // Start at end of heap
  // Now sift up until curr's parent's key > curr's key
  while ((curr != 0)  &&
         (Heap[curr].compareTo(Heap[parent(curr)]) < 0)) {
    swap(Heap, curr, parent(curr));
    curr = parent(curr);
  }
}
/** Put element in its correct place */
private void siftdown(int pos) {
  assert (pos >= 0) && (pos < n) : "Illegal heap position";
  while (!isLeaf(pos)) {
    int j = leftchild(pos);
    if ((j<(n-1)) && (Heap[j].compareTo(Heap[j+1]) > 0)) 
      j++; // j is now index of child with greater value
    if (Heap[pos].compareTo(Heap[j]) <= 0)
      return;
    swap(Heap, pos, j);
    pos = j;  // Move down
  }
}

public E removemin() {     // Remove minimum value
  assert n > 0 : "Removing from empty heap";
  swap(Heap, 0, --n); // Swap minimum with last value
  if (n != 0)      // Not on last element
    siftdown(0);   // Put new heap root val in correct place
  return Heap[n];
}

  /** Swap two Objects in an array
      @param A The array
      @param p1 Index of one Object in A
      @param p2 Index of another Object A
  */
  private static <E> void swap(E[] A, int p1, int p2) {
    E temp = A[p1];
	 A[p1] = A[p2];
	 A[p2] = temp;
  }
}
