package 진도;

public class Person {
    public String name;
    public int age;
    public String blood;
    public String address;

    public Person(){

    }

    /*private Person(String name) {
        this.name = name;
    }*/

    public void sleep1(String test){
        System.out.println(test);
    }

    static void sleep2(String test){
        System.out.println(test);
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String blood, String address){
        this.name = name;
        this.age = age;
        this.blood = blood;
        this.address = address;
    }

    public void study(){
        System.out.println("study !");
    }

    public void introduceMyself(){
        System.out.println("내 이름은: " + name + "입니다.");
        System.out.println("내 나이는: " + age + "입니다.");
        System.out.println("내 혈액형은: "+ blood + "입니다.");
        System.out.println("내 주소는: " + address + "입니다.");
    }

    public void walk(){
        System.out.println(name + "이 걷습니다.");
    }

    public void speak(){
        System.out.println("Speak!!");
    }
}