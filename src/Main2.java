import project.CoffeeShop.Ingredient;
import 진도.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*int[] arr = {1,2,3,4,5};
        int minValue = Arrays.stream(arr).min().getAsInt();
        int maxValue = Arrays.stream(arr).max().getAsInt();

        System.out.println("minValue = " + minValue);
        System.out.println("maxValue = " + maxValue);*/

/*int [] arr = {3, 6, 1, 8, 4, 7, 2, 5};
        Arrays.sort(arr);//배열을 오름차순으로 정렬
int index = Arrays.binarySearch(arr, 0);

        System.out.println("index= "+index);
        */
/*String str = "1234";
int num = Integer.parseInt(str);
num = num + num;

        System.out.println("num = " + num);

        String str = "data";
    try {
        float num = Float.parseFloat(str);
        System.out.println(num);
    }catch (NumberFormatException e){
        System.out.println("부동 소수점 수가 아닙니다.");
    }*/


public class Main2 {
    public static void main(String[] args){
        Person han = new Person("Han",35,"B","의정부시");
        Person kim = new Person("Kim", 10, "A", "서울시");
        Person lee = new Person("Lee", 20, "O","수원시");

        LinkedList<Person> linkedList = new LinkedList<>();
        linkedList.add(han);
        linkedList.add(kim);
        linkedList.add(lee);

        for(int i = 0; i<linkedList.size(); i++) {
            Person p = linkedList.get(i);
            if (p.name.equals("Han")) {//java 문자 비교
                p.age = 10;
                p.name = "한대현"; //값을 바꾼다.
                linkedList.remove(i); //값을 지운다.
            }
        }

        //출력
        for(int i=0; i<linkedList.size(); i++){
            Person p = linkedList.get(i);
            p.introduceMyself();
        }

    }
}
