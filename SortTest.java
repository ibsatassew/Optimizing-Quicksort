/**
 *  Collection of sorting methods
 */ 

import java.util.Random;
import java.util.Stack;


public class SortTest <E extends Comparable<? super E>> {

   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);  // size of the array taken from the command line
      Random r = new Random();
      Integer[] myarr = new Integer[N];   
      Integer[] myarr2 = new Integer[N];   
      for(int i = 0; i<N; i++){
         Integer num = r.nextInt(10000) + 1;
         myarr[i] = num;

      }
      //Integer[] temp = new Integer[N];
       
      long start = System.nanoTime(); 
      quickSort(myarr, 0, myarr.length-1);
      //isSorted(myarr);
      long end = System.nanoTime(); 
      System.out.println("Time: " + (end - start)/1000000.0 + " ms.");
      /*if(isSorted(myarr)){
         printArray(myarr);  
      }
       */ 
   }

   /**
   *  Determine whether the array is sorted
   *  @param an array 
   *  @return true if the array is sorted; false otherwise
   */
  
   private static <E extends Comparable<? super E>> boolean isSorted(E[] arr){
      for(int i = 1; i<arr.length; i++){
         if(arr[i].compareTo(arr[i-1])<0){
            return false;
         }
      }
      return true;
   }
   

   /**
   *  Prints the array
   *  @param an array
   */
   private static <E extends Comparable<? super E>> void printArray(E[] A){
      for (int i = 0; i < A.length; i++)
         System.out.print(A[i] + " ");
      System.out.println();
   }

   /**
   *  Generates an "almost sorted" array
   *  @param an array 
   */
   private static <E extends Comparable<? super E>> void getAlmostSorted(E[] A, int degree) {
      int size = A.length;
      Random r = new Random();
      for (int i = 0; i < degree; i++) 
         swap(A, r.nextInt(size), r.nextInt(size)); // swap two random elements
   }

   /**
   *  Selection Sort
   *  @param an array to be sorted
   */
   private static <E extends Comparable<? super E>> void selectionSort(E[] A) {
      for (int i = 0; i < A.length-1; i++){ 
         int lowindex = i; 
         for (int j = A.length-1; j > i; j--)
            if (A[j].compareTo(A[lowindex])<0) 
               lowindex = j; 
         swap(A, i, lowindex); 
      }
   }

   /**
   *  Swaps of two array elements
   *  @param an array
   *  @param the index of the first element
   *  @param the index of the second element
   */
   private static void swap(Object[] A, int i, int j) {
      Object temp = A[i];
      A[i] = A[j];
      A[j] = temp;
   }

   /**
   *  Insertion Sort
   *  @param an array to be sorted
   */
   private static <E extends Comparable<? super E>> void insertionSort(E[] A) {
      for (int i = 1; i < A.length; i++) 
         for (int j = i; (j > 0) && (A[j].compareTo(A[j-1]) < 0); j--) 
            swap(A, j, j-1);
   }

   /**
   *  Bubble Sort
   *  @param an array to be sorted
   */
   private static <E extends Comparable<? super E>> void bubbleSort(E[] A) {
      for (int i = 0; i < A.length-1; i++) 
         for (int j = A.length-1; j > i; j--)
            if (A[j].compareTo(A[j-1]) < 0)
               swap(A, j, j-1);
   }

   /**
   *  Shell Sort
   *  @param an array to be sorted
   */
   private static <E extends Comparable<? super E>> void shellSort(E[] A) {
      for (int i = A.length/2; i > 2; i /= 2) 
         for (int j = 0; j < i; j++)
            inssort2(A, j, i); 
      inssort2(A, 0, 1);
   }

   /**
   *  Modified insertion sort for varying increments
   *  @param an array to be sorted 
   *  @param a starting index 
   *  @param separator value
   */
   private static <E extends Comparable<? super E>> void inssort2(E[] A, int start, int incr) {
   for (int i = start + incr; i < A.length; i += incr) 
      for (int j = i; (j >= incr)&& (A[j].compareTo(A[j-incr]) < 0); j -= incr) 
         swap(A, j, j-incr);
   }



   /**
   *  Merge Sort
   *  @param an array to be sorted
   *  @param a temporary array
   *  @param index of the first element of the array
   *  @param index of the last element of the array
   */
   private static <E extends Comparable<? super E>> void mergeSort(E[] A, E[] temp, int l, int r) {
      if (l == r) return; 
      int mid = (l + r) / 2; 
      mergeSort(A, temp, l, mid); 
      mergeSort(A, temp, mid + 1, r); 
      for (int i = l; i <= r; i++) 
          temp[i] = A[i];
        merge(A, temp, l, mid, r); 
   }

   /**
   *  Merges two sorted arrays
   *  @param an array to be sorted
   *  @param a temporary array
   *  @param the index of the first element of the array
   *  @param the index of the middle element of the array
   *  @param the index of the last element of the array
   */
   private static <E extends Comparable<? super E>> void merge(E[] A, E[] temp, int l, int mid, int r) {
      int i1 = l; 
      int i2 = mid + 1;
      for (int curr = l; curr <= r; curr++) {
         if (i1 == mid+1) 
            A[curr] = temp[i2++];
         else if (i2 > r) 
            A[curr] = temp[i1++];
         else if (temp[i1].compareTo(temp[i2])<0) 
            A[curr] = temp[i1++];
         else
            A[curr] = temp[i2++];
      }
    }

   /**
   *  Quick Sort 
   *  @param an array to be sorted
   *  @param the index of the first element of the array
   *  @param the index of the last element of the array
   *  @return nothing
   */
  //original quickSort() method
  /* 
  private static <E extends Comparable<? super E>> void quickSort(E[] A, int l, int r) {
   int pivotindex = findpivot(A, l, r);
   swap(A, pivotindex, r); 
   int k = partition(A, l-1, r, A[r]); 
   swap(A, k, r); 
   if ((k-l) > 1) 
      quickSort(A, l, k-1); 
   if ((r-k) > 1) 
      quickSort(A, k+1, r); 
}
*/
  //optimization2
  /* 
   private static <E extends Comparable<? super E>> void quickSort(E[] A, int l, int r) {
      int pivotindex = findpivot(A, l, r);
      swap(A, pivotindex, r); 
      int k = partition(A, l-1, r, A[r]); 
      swap(A, k, r); 
      if ((k-l) > 200) 
         quickSort(A, l, k-1); 
      if ((r-k) > 200) 
         quickSort(A, k+1, r); 
      if ((k-l) < 200) 
         insertionSort2(A, l, k-1); 
      if ((r-k) < 200) 
         insertionSort2(A, k+1, r); 
   }
   */
   //optimization3

   private static <E extends Comparable<? super E>> void quickSort(E[] A, int l, int r) {
      Stack<Integer> stack = new Stack<>();
      stack.push(l);
      stack.push(r);
  
      while (!stack.isEmpty()) {
          r = stack.pop();
          l = stack.pop();
  
          int pivotindex = findpivot(A, l, r);
          swap(A, pivotindex, r);
          int k = partition(A, l - 1, r, A[r]);
          swap(A, k, r);
  
          if ((k - l) > 20) {
              stack.push(l);
              stack.push(k - 1);
          } else {
              insertionSort2(A, l, k - 1);
          }
  
          if ((r - k) > 20) {
              stack.push(k + 1);
              stack.push(r);
          } else {
              insertionSort2(A, k + 1, r);
          }
      }
  }


   private static <E extends Comparable<? super E>> void insertionSort2(E[] A, int start, int end) {
      for (int i = start; i < end+1; i++) 
         for (int j = i; (j > 0) && (A[j].compareTo(A[j-1]) < 0); j--) 
            swap(A, j, j-1);
   }
   /**
   *  Partiitions an array with respect to the pivot element
   *  @param an array to be sorted
   *  @param the index of the first element of the array
   *  @param the index of the last element of the array
   *  @param the pivot element
   */
   private static <E extends Comparable<? super E>> int partition(E[] A, int l, int r, E pivot) {
      do { 
         while (A[++l].compareTo(pivot) < 0);
         while ((r != 0) && (A[--r].compareTo(pivot) > 0)); 
         swap(A, l, r); 
      } while (l < r); 
      swap(A, l, r); 
      return l; 
   }

   /**
   *  Determines the pivot element
   *  @param an array to be sorted
   *  @param the index of the first element of the array
   *  @param the index of the last element of the array
   *  @return the index of the pivot
   */
  //original findpivot() method
  /* 
   private static <E extends Comparable<? super E>> int findpivot(E[] A, int l, int r) {
        return (l + r)/2;
   }
*/
//optimization1

private static <E extends Comparable<? super E>> int findpivot(E[] A, int l, int r) {
   int mid = (l + r) / 2; // Find the middle index

   // Find the index of the median element among the three candidates in the original array A
   int pivotIndex;
   if (A[mid].compareTo(A[l]) > 0) {
       if (A[mid].compareTo(A[r]) < 0) {
           pivotIndex = mid;
       } else {
           if (A[l].compareTo(A[r]) < 0) {
               pivotIndex = r;
           } else {
               pivotIndex = l;
           }
       }
   } else {
       if (A[l].compareTo(A[r]) < 0) {
           pivotIndex = l;
       } else {
           if (A[mid].compareTo(A[r]) < 0) {
               pivotIndex = r;
           } else {
               pivotIndex = mid;
           }
       }
   }
   return pivotIndex;
}

   /**
   *  Heap Sort
   *  @param an array to be sorted
   */
   private static <E extends Comparable<? super E>> void heapSort(E[] A) {
      int size = A.length; 
      MinHeap<E> heap = new MinHeap<E>(size); 
      for (int k = 0; k < size; k++) 
         heap.insert(A[k]);
      for (int k = 0; k < size; k++) 
         A[k] = heap.removemin();
   }
}
/*GitHub repo  https://github.com/ibsatassew/Performance-Analysis-of-Sorting-Algorithms.git */