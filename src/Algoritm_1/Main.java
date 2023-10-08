package Algoritm_1;


import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);
        int size;
        size = scanner.nextInt();
        int[] array = new int[size];
        Random random = new Random();
        int randomNumber;


        for( int i = 0; i<size;i++){
            randomNumber = random.nextInt(size);
            array[i]=randomNumber;
        }

        for( int i = 0; i<size;i++){
            System.out.println(array[i]);
        }

        System.out.println("Введите число для поиска индекса: ");
        int number;
        int steps=0;
        number = scanner.nextInt();

        for( int i = 0; i<size;i++){
            steps++;
            if(number==array[i]){
                System.out.println("Индекс числа: "+i);
                System.out.println(steps);
                break;
            } else if(i==size-1){
                System.out.println("Такого числа нету в массиве");
            }
        }
    }
}
