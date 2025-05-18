


public class StudyPerson implements Comparable<StudyPerson>{
    public String name;
    public int age;

    public StudyPerson(String name, int age){
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString(){
        return "StudyPerson{name='" + name + "', age=" + age + "}";
    }

    @Override
    public int compareTo(StudyPerson other) {
        return Integer.compare(this.age, other.age);
    }
}
