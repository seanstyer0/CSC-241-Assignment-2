/*
Name: Sean Styer
File: SelectionSort.java
Input: Path to a file of integers
Output: One sorted array using selection sort
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SelectionSort{
  public static void main(String[]args){
    //get the length of the data file
    String filePath = args[0];
    int numInts = getLength(filePath);

    //enter the values into an array
    int[] unsorted = createArray(filePath, numInts);

    //sort the array and print it out
    int[] sorted = sort(unsorted);
    for(int i = 0; i < sorted.length; i++){
      System.out.println(sorted[i]);
    }
  }
  //parse through the file and record the number of integers
  public static int getLength(String filePath){
    int count = 0;
    File input = new File(filePath);

    try {
            Scanner in = new Scanner(input);
            int i = 0;
            while (in.hasNextInt()) {
                count++;
                in.nextInt();
            }
            in.close();
        }
        //in the event of an error, print the error
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return count;
  }
  //convert the data file into an integer array
  public static int[] createArray(String filePath, int stringNum){
    File file = new File(filePath);
    int[] userInput = new int[stringNum];

    //parse through the file and save each integer in the array
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
  //use selection sort to sort an array of integers
  public static int[] sort(int[] input){
    int max = Integer.MIN_VALUE;
    //traverse through the array and find the max
    //once the max is placed at the end, shorten the indexes to be compared
    for(int j = input.length - 1; j > -1; j--){
      int maxIndex = Integer.MIN_VALUE;
      max = Integer.MIN_VALUE;
      for(int k = j; k > -1; k--){
        if(input[k] > max){
          max = input[k];
          maxIndex = k;
        }
      }
      input[maxIndex] = input[j];
      input[j] = max;
    }
    return input;
  }
}
