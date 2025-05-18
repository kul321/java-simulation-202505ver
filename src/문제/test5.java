import java.util.Scanner;

public class test5 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //몇 층, 몇 호
        System.out.println("해당 아파트는 몇 층까지 있습니까?");
        int floors = sc.nextInt();
        System.out.println("각 층에 몇 호까지 있습니까?");
        int rooms = sc.nextInt();


        //아파트 배열을 유동적으로 생성
        String apt[][][] = new String[floors][rooms][2];//0:이름 1:나이
        //각 호수에 사는 사람들 입력 받기
        originalApt(apt);

        while(true){
            showApt(apt);
            System.out.println("무엇을 선택하시겠습니까?");
            System.out.println("1.이사 \n2.입주");
            int choice = sc.nextInt();

            switch ( choice ){
                case 1:
                    moveOut(apt);
                    break;
                case 2:
                    moveIn(apt);
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }//while문 종료
    }


    //초기 입주민들
    public static void originalApt(String apt[][][]){
        System.out.println("각 호수에 살고 있는 입주자 정보를 입력하세요. 빈 호수라면 이름에 '빈'이라고 입력하세요.");
        for (int i = 0; i< apt.length; i++){
            for(int j = 0; j< apt.length; j++){
                System.out.println((i+1)+"0"+(j+1)+"호 입주민의 이름을 입력하세요.");
                String name = sc.next();
                if(!name.equals("빈")) {
                    System.out.println("나이를 입력하세요.");
                    String age = sc.next();
                    apt[i][j][0] = name;
                    apt[i][j][1] = age;
                }
                if(name.equals("빈")){
                    apt[i][j][0] = null;
                    apt[i][j][1] = null;
                }
            }
        }
    }

    //아파트 상태 보여주기
    public static void showApt(String[][][] apt){
        for (int i = 0; i<apt.length; i++){
            System.out.println("---------------------------------------------------------------------------");
            for(int j = 0; j<apt[i].length; j++){
                if(apt[i][j][0] == null ){
                    System.out.print(((i+1)+"0"+(j+1)+"호 빈 호수  |  "));
                }
                if(apt[i][j][0] != null){
                    System.out.print(((i+1)+"0"+(j+1)+"호 이름: "+apt[i][j][0]+" 나이: "+ apt[i][j][1])+"  |  ");
                }


            }System.out.println();
        }
    }

    //입주
    public static void moveIn(String apt[][][]){
        System.out.println("입주할 층과 호수를 입력하세요");
        int floor = sc.nextInt() -1;
        int room = sc.nextInt() -1;

        if (apt[floor][room][0] != null){
            System.out.println("이미 사람이 거주 중입니다.");
        }
        if(apt[floor][room][0] == null){
            System.out.println("입주할 사람의 이름을 입력하세요.");
            String name = sc.next();
            System.out.println("입주할 사람의 나이를 입력하세요.");
            String age = sc.next();

            apt[floor][room][0] = name;
            apt[floor][room][1] = age;

            System.out.println(name + "님이 "+(floor+1)+"0"+(room+1)+"호에 입주했습니다.");

        }
    }

    //이사
    public static void moveOut(String apt[][][]){
        System.out.println("이사하는 사람의 층과 호수를 입력하세요");
        int floor = sc.nextInt()-1;
        int room = sc.nextInt()-1;

        if(apt[floor][room][0] == null){
            System.out.println("그곳은 아무도 살지 않습니다.");
        }
        if(apt[floor][room][0] != null){

            System.out.println("몇 층, 몇 호로 이사하시겠습니까?");
            System.out.println("해당 아파트가 아닌 곳으로 이사나간다면 -1을 두번 입력하세요");
            int floor2 = sc.nextInt()-1;
            int room2 = sc.nextInt()-1;

            if (floor2 != -1 && room2 != -1) {
                apt[floor2][room2][0] = apt[floor][room][0];
                apt[floor2][room2][1] = apt[floor][room][0];

                apt[floor][room][0] = null;
                apt[floor][room][1] = null;
                System.out.println("이사 완료. "+apt[floor2][room2][0]+"님이 이사했습니다.");
            }
            if(floor2 == -1 && room2 == -1){
                System.out.println("이사 완료. "+apt[floor][room][0]+"님이 이사했습니다.");
                apt[floor][room][0] = null;
                apt[floor][room][1] = null;

            }

        }
    }
}