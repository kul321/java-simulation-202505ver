package model;

public class Person {
//객체 = state + behavior

    //1. field(멤버변수) - state(상태)
    private String name;
    private int age;
    private String blood;
    private String address;

    //생성자(초기화 용도: 객체가 생성될 때 초기화를 하기 위함이다.)
     public Person(){

     }

     Person(String name){
         this.name = name;
     }

     public Person(String name, int age){
         this.name = name;
         this.age = age;
     }

     public Person(String name, int age, String blood, String address){
         this.name =name;
         this.age = age;
         this.blood = blood;
         this.address = address;
     }

     //접근제어자
    /*
    public (모두 다)
    protected(같은 패키지 내에서 접근 가능)
    private(같은 클래스 내에서만 접근 가능)(
     */

    //2.Method(메소드) - behavior(행동)
    void study(){
        System.out.println("study!");
    }

    public void introduceMyself(){
        System.out.println("내 이름은: "+name+"입니다.");
        System.out.println("내 나이는: "+age+"입니다.");
        System.out.println("내 혈액형은: "+blood+"입니다.");
        System.out.println("내 주소는: "+ address+"입니다.");
    }

    public void walk(){
        System.out.println(name + "이 걷습니다.");
    }

    void speak(){
        System.out.println("Speak!!!");
    }



}
