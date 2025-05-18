public class Minseo {
    String name = "강민서";
    String hometown = "서울";
    int birthYear = 1990;
    int age = 35;
    double height = 154.0;
    double weight = 48.9;
    String gender = "여자";
    String univ = "방송대학교";
    String job = "구직중";
    String address = "경기도 수원";
    String feeling = "불안";
    String hobby = "독서";
    String pet_1 = "레미";
    String pet_2 = "파미";
    String pet_type = "고양이 ";

    Minseo(){
        age = this.age;
    }
    Minseo(int a, String b){
        age = a;
        pet_1 = b;
    }

    String born(){
        return name + "가 "+birthYear+"에 "+hometown+"에서 태어났다";
    }

    void enter(){
        System.out.println(address+"로 이사했다");
    }

    int age(){
        return age;
    }
    void jobStatus(){
        System.out.println(name+"는 현재 구직중이다.");
    }

    String goal(){
        return "개발자로 취업하고 싶다";
    }

}
