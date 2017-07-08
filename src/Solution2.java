import java.util.*;

public class Solution2 {

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

        System.out.println();
        ArrayList<ArrayList<Integer>> list = arrayAnalizer(array);

        //Пример 1
        System.out.print("Пример 1: массив ");
        for (int i : array){
            System.out.print(i + ", ");
        }
        System.out.println("\nОтвет " + list.get(0).get(0) + ". Так как" +
                " число " + list.get(0).get(1) + " повторяется " +
                list.get(0).get(0) + " раза\n");
        //Пример 2
        System.out.print("Пример 2: массив ");
        for (int i : array){
            System.out.print(i + ", ");
        }
        if (list.get(1).get(0) == 1){
            System.out.println("\nДанные не подходят для Примера 2");
            System.out.println();
        }
        else {
            System.out.println("\nОтвет " + list.get(0).get(0) + ". Так как" +
                    " число " + list.get(0).get(1) + " повторяется " +
                    list.get(0).get(0) + " раза. А число " + list.get(1).get(1) +
                    " повторяется " + list.get(1).get(0) + " раза. " +
                    "Поскольку надо вывести максимум, выводим "  + list.get(0).get(0) + ".\n");
        }
        //Пример 3
        System.out.print("Пример 3: массив ");
        for (int i : array){
            System.out.print(i + ", ");
        }
        if (list.get(0).size() < 3){
            System.out.println("\nДанные не подходят для Примера 2");
            System.out.println();
        }
        else {

            System.out.println("\nОтвет " + list.get(0).get(0) + ". Так как " +
                    list.get(0).get(1) + " и " + list.get(0).get(2) + " повторяются по " +
                    list.get(0).get(0) + " раза, не важно, кого из них подсчитывать, цель - " +
                    "вывести максимум.\nВ этом примере максимум повторений чисел является " +
                    list.get(0).get(0) + " раза.\n");
        }

        //Вывод минимального числа повторений
        if (list.get(1).get(0) == 1){
            System.out.println("Минимум повторений - " + list.get(0).get(0));
        }
        else {
            System.out.println("Минимум повторений - " + list.get(list.size() - 2).get(0));
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

    public static ArrayList<ArrayList<Integer>> arrayAnalizer(int[] arr){

        //Заполнение карты числами
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++){
            int j = repeatsCount(arr[i], arr);
            map.put(arr[i], j);
        }

        //Находим число/числа с максимальным количеством повторений
        ArrayList<Integer> max = new ArrayList<>();
        int maxValue = 0;
        for (Map.Entry<Integer, Integer> pairs : map.entrySet()){
            if (pairs.getValue() > maxValue)
                maxValue = pairs.getValue();
        }
        max.add(maxValue); //Первым в лист кладём кол-во повторов, потом значения
        for (Map.Entry<Integer, Integer> pairs : map.entrySet()){
            if (pairs.getValue() == maxValue)
                max.add(pairs.getKey());
        }

        //Находим число/числа, число повторений которых на втором месте
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> preMax = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pairs : map.entrySet()){
            if (!values.contains(pairs.getValue())){
                values.add(pairs.getValue());
            }
        }
        int[] valuesArr = new int[values.size()];
        for (int i = 0; i < valuesArr.length; i++){
            valuesArr[i] = values.get(i);
        }
        Arrays.sort(valuesArr);
        preMax.add(valuesArr[valuesArr.length - 2]); //Первым в лист кладём кол-во повторов, потом значения
        for (Map.Entry<Integer, Integer> pairs : map.entrySet()){
            if (pairs.getValue() == preMax.get(0))
                preMax.add(pairs.getKey());
        }

        //Минимальное число повторений в массиве

        ArrayList<Integer> min = new ArrayList<>();
        try {
            min.add(valuesArr[valuesArr.length - 2]);
        }
        catch (Exception e){
            min.add(valuesArr[valuesArr.length - 1]);
        }

        //Формируем список, который содержит списки из предыдущих пунктов

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(max);
        list.add(preMax);
        list.add(min);

        return list;
    }

    public static int repeatsCount(int number, int[] array){
        int counter = 0;
        for (int a : array){
            if (a == number)
                counter++;
        }
        return counter;
    }

}
