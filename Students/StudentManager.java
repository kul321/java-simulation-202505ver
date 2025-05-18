package project.Students;

import java.util.LinkedList;

public class StudentManager {
    private LinkedList<Student> students;

    public StudentManager() {
        students = new LinkedList<>();
    }

    //1.학생 추가
    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student.getName() + " 학생이 추가되었습니다.");
    }

    //2.학생 출력
    public void showAllStudent() {
        System.out.println("===전체 학생 목록===");

        if (students.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }

        for (Student student : students) {
            System.out.println(student.getIdNumber() + ". " + student.getName());
        }
    }

    //3-1.학생 검색 - 이름으로
    public void searchStudentByName(String name) {
        System.out.println("===검색 결과===");

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getName().equals(name)) {
                System.out.println("학생번호: " + student.getIdNumber() + ", 학생이름: " + student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("같은 이름의 학생을 찾을 수 없습니다.");
        }
    }

    //3-2.학생 검색 - 번호로
    public Student searchStudentByIdNumber(int number) {
        System.out.println("===검색 결과===");

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getIdNumber() == number) {
                System.out.println("학생번호: " + student.getIdNumber() + ", 학생이름: " + student.getName());
                found = true;
                return student;
            }
        }
        if (!found) {
            System.out.println("같은 이름의 학생을 찾을 수 없습니다.");

        }
        return null;
    }

    //4. 학생 제거
    public void removeStudent(int number){
        boolean removed = false;

        for(int i=0; i< students.size(); i++){
            Student student = students.get(i);
            if(student.getIdNumber() == number){
                System.out.println(number+": "+student.getName()+"학생이 제거되었습니다.");
                students.remove(i);
                removed = true;
            }
        }

        if(!removed){
            System.out.println("삭제하고자 하는 학생을 찾을 수 없습니다.");
        }
    }

    //5. 학생 정보 수정
    public void updateStudent(int number, String sector, String newValue){


        Student student = searchStudentByIdNumber(number);



        switch(sector){
            case "이름":
                System.out.println(student.getName()+"의 이름이"+newValue+"로 변경됩니다.");
                student.setName(newValue);
                break;
            case "나이":
                int newAge = Integer.parseInt(newValue);
                System.out.println(student.getName()+"의 나이가"+newAge+"로 변경됩니다.");
                student.setAge(newAge);
                break;
            case "전공":
                System.out.println(student.getName()+"의 전공이"+newValue+"로 변경됩니다.");
                student.setMajor(newValue);
                break;
            case "학번":
                int newNumber = Integer.parseInt(newValue);
                System.out.println(student.getName()+"의 학번이"+newNumber+"로 변경됩니다.");
                break;
            default:
                System.out.println("인식할 수 없는 입력입니다. 다시 확인해주세요.");
        }

    }


}
