/*
Name: Sean Styer
File: Merge.java
Input: Paths to two data files of integers
Output: One sorted array using insertion sort and the merge function
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Merge{
  public static void main(String[] args){
    //take in the paths of the data files and convert them to arrays
    String path1 = args[0];
    String path2 = args[1];
    int length1 = getLength(path1);
    int length2 = getLength(path2);
    int[] group1 = createArray(path1, length1);
    int[] group2 = createArray(path2, length2);

    //sort the arrays using insertion sorted
    //sort the first one forwards and the second one backwards
    int[] new1 = sort(group1);
    int[] new2 = sortBackwards(group2);

    //print out the sorted versions of the first and second arrays
    for(int i = 0; i < group1.length; i++){
      System.out.print(new1[i]);
      if(i < group1.length-1)
        System.out.print(" ");
    }
    System.out.println("");
    for(int i = 0; i < group2.length; i++){
      System.out.print(new2[i]);
      if(i < group2.length-1)
        System.out.print(" ");
    }
    System.out.println("");
    //use the merge function to merge the two arrays into one array
    //that is sorted backwards
    int[] finalSorted = merge(new1, new2);

    //print out the final array
    for(int i = 0; i < finalSorted.length; i++){
      System.out.print(finalSorted[i]);
      if(i < finalSorted.length-1)
        System.out.print(" ");
    }
  }

  //take in a path to a data file of integers and enter them into an array
  public static int[] createArray(String filePath, int numInts){
    File file = new File(filePath);
    int[] userInput = new int[numInts];

    //parse through the file and save each line as an integer in the array
    try {
            Scanner in = new Scanner(file);
            int i = 0;
            while (in.hasNextInt()) {
                userInput[i] = in.nextInt();
                i++;
            }
            in.close();
        }
        //in the event of an error, print the error
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    return userInput;
  }

  //parse through a file and return the number of elements
  public static int getLength(String filePath){
    int count = 0;
    File input = new File(filePath);

      //while the data file has a next element, increase the counter
    try {
            Scanner in = new Scanner(input);
            int i = 0;
            while (in.hasNextLine()) {
                count++;
                in.nextLine();
            }
            in.close();
        }
        //in the event of an error, print the error
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return count;
  }

  //sorts an array of integers backwards using the insertion sort algorithm
  public static int[] sortBackwards(int[] unsortedArray){
    //copy the array into another array that will be sorted
    int[] sortedArray = new int[unsortedArray.length];
      for(int i = 0; i < sortedArray.length; i++){
        sortedArray[i] = unsortedArray[i];
      }

      //use insertion sort to sort the array backwards
      int i = 0;
      int key = 0;

      for(int j = sortedArray.length - 2; j > -1; j--){
        key = sortedArray[j];
        i  = j + 1;

        while(i < sortedArray.length && key < sortedArray[i]){
          sortedArray[i-1] = sortedArray[i];
          i = i+1;
          sortedArray[i-1] = key;
        }
      }
    return sortedArray;
  }

  //sorts an array of integers using the insertion sort algorithm
  public static int[] sort(int[] unsortedArray){
    //copy the array into another array that will be sorted
    int[] sortedArray = new int[unsortedArray.length];
      for(int i = 0; i < sortedArray.length; i++){
        sortedArray[i] = unsortedArray[i];
      }

      //use insertion sort to sort the array
      int i = 0;
      int key = 0;

      for(int j = 1; j < sortedArray.length; j++){
        key = sortedArray[j];
        i  = j - 1;

        while(i > -1 && key < sortedArray[i]){
          sortedArray[i+1] = sortedArray[i];
          i = i-1;
          sortedArray[i+1] = key;
        }
      }
    return sortedArray;
  }

  //use the merge function to merge two arrays in backwards order
  static int[] merge(int[] L, int[] R) {
    int[] A = new int[L.length + R.length];
    int[] left = new int[L.length + 1];
    int[] right = new int[R.length + 1];
    //transfer the vaules into two new arrays that are one element longer
    for(int i = 0; i < L.length; i++){
      left[i] = L[i];
    }

    for(int i = 0; i < R.length; i++){
      right[i] = R[i];
    }
    //populate the last values of the new arrays with negative infinity
    //to keep arrays from running out of elements
    left[L.length] = Integer.MIN_VALUE;
    right[R.length] = Integer.MIN_VALUE;

    //merge the arrays
    int i = L.length - 1;
    int j = 0;
    //compare values in each of the array and save the larger value in array A
    for (int k = 0; k < A.length; k++) {
      if (left[i] >= right[j]) {
        A[k] = left[i];
        i--;
      } else {
        A[k] = right[j];
        j += 1;
      }
    }
    return A;
  }
}
