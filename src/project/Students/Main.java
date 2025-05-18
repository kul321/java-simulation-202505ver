package project.Students;

public class Main {
    public static void main(String[] args){
        StudentManager manager = new StudentManager();

        //학생 추가 테스트
        System.out.println("1.학생 추가 테스트");
        manager.addStudent(new Student("김사과", 23, "국문학과",2010001));
        manager.addStudent(new Student("박철수", 25, "컴퓨터공학과",2010002));
        manager.addStudent(new Student("최영희", 20, "국문학과",2010003));

        //전체 학생 출력 테스트
        System.out.println("2.전체 학생 출력 테스트");
        manager.showAllStudent();

        //학생 검색 테스트
        System.out.println("3-1.학생 검색(번호/해당없음)");
        manager.searchStudentByIdNumber(10);
        System.out.println("3-2.학생 검색(번호/해당함)");
        manager.searchStudentByIdNumber(2010002);
        System.out.println("3-3.학생 검색(이름/해당없음)");
        manager.searchStudentByName("김숨");
        System.out.println("3-4.학생 검색(이름/해당함)");
        manager.searchStudentByName("김사과");

        //학생 제거 테스트
        System.out.println("4-1.학생 제거 테스트(박철수)");
        manager.removeStudent(2010002);

        System.out.println("4-2.다시 학생 제거 테스트(박철수)");
        manager.removeStudent(2010002);

        System.out.println("5. 전체 테스트 후 결과 출력");
        manager.showAllStudent();
    }
}
