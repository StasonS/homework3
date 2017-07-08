import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {

    public static void main(String[] args) {

        System.out.print("Введите размер массива: ");

        int[] array = new int[intInput()];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int counter = 0;

        System.out.println("Введите " + array.length + " чисел");
        for (int i = 0; i < array.length; i++){
            array[i] = intInput();
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
            if (array[i] == 5)
                counter++;
        }
        System.out.println("Максимальное число: " + max + "\nМинимальное число: " + min);
        System.out.println("Число 5 введено " + counter + " раз");

        System.out.println("Отсортированный массив:");
        Arrays.sort(array);
        for (int i : array) {
            System.out.println(i);
        }


    }

    public static int intInput(){
        int a;
        Scanner sc = new Scanner(System.in);
        try {
            a = sc.nextInt();
        }
        catch (Exception e){
            System.out.print("Введено не число, повторите ввод: ");
            a = intInput();
        }
        return a;
    }
}
