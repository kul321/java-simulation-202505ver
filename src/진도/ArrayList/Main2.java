package 진도.ArrayList;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args){
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 35));
        for(Person p : people){
            System.out.println(p.name + " is " + p.age + " years old.");
        }
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}
