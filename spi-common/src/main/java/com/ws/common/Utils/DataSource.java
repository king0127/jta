package com.ws.common.Utils;

public class DataSource {

    public static int[] sort(int[] numbers) {
        // n-1次冒泡
        for (int i = 0; i < numbers.length -1; i++) {
            for (int j = 0; j < numbers.length - 1 -i; j++) {
                if(numbers[j] > numbers[j+1]) {
                    int tem = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = tem;
                }
            }
        }
        return numbers;
    }


    public static void main(String[] args) {

        int[] nu = new int[]{1,5,1,8,6,3,9,4};
        int[] sort = sort(nu);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+",");
        }

    }

}
