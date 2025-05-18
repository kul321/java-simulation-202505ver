package 진도;

public class Main {
    public static void main(String[] args){
/*       Person.name = "Hi";
       Person.age = 10;

       System.out.println(Person.name);*/

       Person person = new Person();
       person.sleep1("test");
       Person.sleep2("test2");


       Person han = new Person("Han",35,"B","의정부시");
       han.introduceMyself();
       Person lee = new Person();
       lee.introduceMyself();
       Person park = new Person("Park", 50);
       park.introduceMyself();
       Person kim = new Person("Kim", 30, "A", "서울시");
       kim.introduceMyself();
       kim.walk();
    }
}
