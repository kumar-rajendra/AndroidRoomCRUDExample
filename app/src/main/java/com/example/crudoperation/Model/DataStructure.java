package com.example.crudoperation.Model;

public class DataStructure {
    public boolean  FindTwoSum(int[] input, int targetValue) {

        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length; j++) {
                if (input[i] + input[j] == targetValue) {
                    return true;
                }
            }
        }
        return false;
    }


    int []a = {1, 2, 3, 4, 5, 6};
    int n = a.length, k = 4;
    //reverse(a, n, k);
       // for (int i = 0; i < n; ++i)System.out.print(a[i] + " ");

    static void reverse(int []a, int n, int k)
    {
        if (k > n)
        {
            System.out.println( "Invalid k");
            return;
        }

        // One by one reverse first
        // and last elements of a[0..k-1]
        for (int i = 0; i < k / 2; i++)
        {
            int temp = a[i];
            a[i] = a[k - i - 1];
            a[k - i - 1] = temp;
        }
    }
}
