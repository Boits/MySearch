import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    // Линейный поиск, если нужное число встречается 1 раз и выдается первое встречное
    static int linearSearch1(int[] a, int found) {

        for (int i = 0; i < a.length; i++) {
            if (a[i] == found) {
                return i;
            }
        }

        return -1;
    }

    // Линейный поиск, если нужное число встречается несколько раз
    static void linearSearch2(int[] a, int found){

        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < a.length; i++) {
            if(a[i] == found){
                arrayList.add(i);
            }
        }

        if(!arrayList.isEmpty()){
            System.out.print("Index : ");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.print(arrayList.get(i) + " ");
            }
        }else{
            System.out.println("Not Found");
        }
    }

    // Сортировка слиянием - O(N*LOG(N))
    static int[] merge_sort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;

            array = merge_sort(array, start, middle);
            array = merge_sort(array, middle + 1, end);

            //слияние двух массивов
            array = merge(array, start, middle, end);
        }
        return array;
    }

    static int[] merge(int array[], int start, int middle, int end){
        int a = start;
        int b = middle + 1;

        int result[] = new int[end - start + 1];

        for (int i = 0; i < end - start + 1; i++){
            if ((a <= middle) && (b <= end)) {
                if (array[a] > array[b]){
                    result[i] = array[b];
                    b++;
                }else {
                    result[i] = array[a];
                    a++;
                }
            }else{
                if (a > middle){
                    result[i] = array[b];
                    b++;
                }
                else{
                    result[i] = array[a];
                    a++;
                }
            }
        }
        System.arraycopy(result,0, array, start,end - start + 1);
        return array;
    }

    // Бинарный (двоичный) поиск
    static int binarySearch(int a[], int found, int start_index, int last_index){
        int a1 = start_index;
        int b1 = last_index;

        int middle_index = (a1 + b1) /2;
        int result = -100;

        if (found >= a[a1] && found <= a[b1]) {
            if (a[middle_index] == found) {
                result = middle_index;
                return result;
            } else {
                if (a[middle_index] > found) {
                    result = binarySearch(a, found, a1, middle_index);
                } else {
                    if (a[middle_index] < found) {
                        result = binarySearch(a, found, middle_index + 1, b1);
                    }
                }
            }

            return result;

        }else {

            return -1;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];

        for(int i = 0; i < a.length; i++){
            a[i] = scanner.nextInt();
        }

        int found = scanner.nextInt();

        // Линейный поиск
        System.out.println("Linear Search");
        if(linearSearch1(a,found) != -1){
            System.out.println("Index : " + linearSearch1(a,found));
        }else {
            System.out.println("Not Found");
        }

        // Линейный поиск 2
        System.out.println("Linear Search 2");
        linearSearch2(a,found);
        System.out.println();


        // Бинарный поиск
        System.out.println("Binary Search");
        merge_sort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("Index : " + binarySearch(a, found, 0,a.length - 1));

    }
}

/*

9
3 0 3 8 7 2 5 4 6
3

9
3 0 1 8 7 2 5 4 6
5

10
100 90 80 70 60 50 40 30 20 10
60

*/