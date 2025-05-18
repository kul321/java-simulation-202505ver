package project.Students;

public class Student {
    private String name;
    private int age;
    private String major;
    private int idNumber;

    //생성자
    public Student(String name, int age, String major, int idNumber){
        this.name = name;
        this.age = age;
        this.major = major;
        this.idNumber = idNumber;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void displayStudentsInfo(){
        System.out.println("이름: "+name);
        System.out.println("나이: "+age);
        System.out.println("전공: "+major);
        System.out.println("학번: "+idNumber);
    }


}


