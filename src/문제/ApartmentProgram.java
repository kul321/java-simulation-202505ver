package 문제;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ApartmentProgram {
    // 상수 정의
    static final int FLOORS = 5;
    static final int ROOMS_PER_FLOOR = 5;
    static final int TOTAL_ROOMS = FLOORS * ROOMS_PER_FLOOR;
    static final int MIN_AGE = 20;
    static final int MAX_AGE = 100;

    // 전역 변수
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static Person[][] apartment = new Person[FLOORS][ROOMS_PER_FLOOR];
    static boolean[] usedAges = new boolean[MAX_AGE + 1];

    // 입주자 클래스
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 메인 메서드
     */
    public static void main(String[] args) {
        initializeApartment();
        runMainMenu();
    }

    /**
     * 1. 입주자 정보 입력 관련 메서드
     */
    // 입주자 정보 입력
    public static List<Person> inputOccupantsInfo(int numberOfPeople) {
        List<Person> newOccupants = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = inputDetail(i + 1);
            if (person == null) break;
            newOccupants.add(person);
        }
        return newOccupants;
    }

    // 개별 입주자 상세 정보 입력
    public static Person inputDetail(int order) {
        System.out.printf("%d번째 입주자의 이름을 입력하세요: ", order);
        String name = sc.nextLine().trim();

        int age = getRandomAge();
        if (age == -1) {
            System.out.println("더 이상 입주자를 추가할 수 없습니다.");
            return null;
        }

        return new Person(name, age);
    }

    /**
     * 2. 이동 로직 관련 메서드
     */
    // 왼쪽으로 이동
    public static void processShiftLeft(int targetIndex, Person newOccupant) {
        int emptyIndex = findEmptyIndex(targetIndex, -1); // -1은 왼쪽 방향
        shiftOccupants(emptyIndex, targetIndex, newOccupant, -1); // -1은 왼쪽 방향
    }

    // 오른쪽으로 이동
    public static void processShiftRight(int targetIndex, Person newOccupant) {
        int emptyIndex = findEmptyIndex(targetIndex, 1); // 1은 오른쪽 방향
        shiftOccupants(emptyIndex, targetIndex, newOccupant, 1); // 1은 오른쪽 방향
    }

    // 빈 방 찾기 (방향 매개변수 통합: -1 왼쪽, 1 오른쪽)
    public static int findEmptyIndex(int startIndex, int direction) {
        if (direction < 0) { // 왼쪽 방향
            for (int i = startIndex; i >= 0; i--) {
                int[] rc = getRowColFromIndex(i);
                Person p = apartment[rc[0]][rc[1]];
                if (p == null || p.name.equals("")) {
                    return i;
                }
            }
            return 0;
        } else { // 오른쪽 방향
            for (int i = startIndex; i < TOTAL_ROOMS; i++) {
                int[] rc = getRowColFromIndex(i);
                Person p = apartment[rc[0]][rc[1]];
                if (p == null || p.name.equals("")) {
                    return i;
                }
            }
            return TOTAL_ROOMS - 1;
        }
    }

    // 입주자 이동 처리 (방향 매개변수 통합: -1 왼쪽, 1 오른쪽)
    public static void shiftOccupants(int emptyIndex, int targetIndex, Person newOccupant, int direction) {
        if (direction < 0) { // 왼쪽 방향
            for (int i = emptyIndex; i < targetIndex; i++) {
                int[] fromRC = getRowColFromIndex(i + 1);
                int[] toRC = getRowColFromIndex(i);
                apartment[toRC[0]][toRC[1]] = apartment[fromRC[0]][fromRC[1]];
            }
        } else { // 오른쪽 방향
            for (int i = emptyIndex; i > targetIndex; i--) {
                int[] fromRC = getRowColFromIndex(i - 1);
                int[] toRC = getRowColFromIndex(i);
                apartment[toRC[0]][toRC[1]] = apartment[fromRC[0]][fromRC[1]];
            }
        }
        placeOccupant(targetIndex, newOccupant);
    }

    // 최종 배치
    public static void placeOccupant(int targetIndex, Person newOccupant) {
        int[] targetRC = getRowColFromIndex(targetIndex);
        apartment[targetRC[0]][targetRC[1]] = newOccupant;
    }

    /**
     * 3. 아파트 출력 관련 메서드
     */
    // 아파트 전체 출력
    public static void printApartment() {
        printHeaderLine();
        for (int i = 0; i < FLOORS; i++) {
            printFloor(i);
        }
    }

    // 구분선 출력
    public static void printHeaderLine() {
        for (int i = 0; i < ROOMS_PER_FLOOR; i++) {
            System.out.print("---------------------------------------------------------");
        }
        System.out.println();
    }

    // 층별 출력
    public static void printFloor(int floor) {
        System.out.print("|");
        for (int room = 0; room < ROOMS_PER_FLOOR; room++) {
            printRoom(floor, room);
        }
        System.out.println();
        printHeaderLine();
    }

    // 개별 방 출력
    public static void printRoom(int floor, int room) {
        int roomNumber = (floor + 1) * 100 + (room + 1);
        Person p = apartment[floor][room];
        String name = (p == null) ? "" : p.name;
        int age = (p == null) ? 0 : p.age;
        System.out.printf(" %3d호 이름 : %-4s 나이 : %-2d |", roomNumber, name, age);
    }

    /**
     * 4. 입주 및 퇴실 관련 메서드
     */
    // 입주 처리
    public static void processMoveIn(int type) {
        String roomStr = inputRoomNumber("입주");
        int[] rc = getRowColFromRoomNumber(roomStr);
        if (rc == null) {
            System.out.println("잘못된 호실 번호입니다.");
            return;
        }

        System.out.print("몇 명이나 가게 하겠습니까? ");
        int numberOfPeople = sc.nextInt();
        sc.nextLine();

        List<Person> newOccupants = inputOccupantsInfo(numberOfPeople);
        int targetIndex = get1DIndex(rc[0], rc[1]);

        // 타입1 또는 타입2 처리
        processMultipleOccupants(targetIndex, newOccupants, type);
    }

    // 다수 입주자 처리 메인 메서드
    public static void processMultipleOccupants(int targetIndex, List<Person> newOccupants, int type) {
        // 1. 타입에 따른 입주자 제한 적용
        List<Person> eligibleOccupants = applyTypeRestriction(newOccupants, targetIndex, type);

        // 2. 층과 방 번호 계산
        int targetFloor = targetIndex / ROOMS_PER_FLOOR;
        int targetRoom = targetIndex % ROOMS_PER_FLOOR;
        int availableInFloor = ROOMS_PER_FLOOR - targetRoom;

        // 3. 현재 층에 배치할 수 있는 입주자 수 계산
        int placedCount = Math.min(availableInFloor, eligibleOccupants.size());

        // 4. 현재 층에 배치 가능한 입주민들은 블록 쉬프트로 처리
        if (placedCount > 0) {
            List<Person> currentFloorOccupants = new ArrayList<>(eligibleOccupants.subList(0, placedCount));
            processBlockShift(targetIndex, currentFloorOccupants);
        }

        // 5. 남은 입주자 다른 층에 배치
        if (eligibleOccupants.size() > placedCount) {
            placeRemainingOccupants(eligibleOccupants, placedCount);
        }
    }

    // 타입 제한 적용
    public static List<Person> applyTypeRestriction(List<Person> newOccupants, int targetIndex, int type) {
        List<Person> eligibleOccupants = new ArrayList<>(newOccupants);

        if (type == 1) {
            int availableSpaces = targetIndex + 1;
            if (eligibleOccupants.size() > availableSpaces) {
                System.out.println("!!!!더이상 입주가 불가능합니다.!!!");
                eligibleOccupants = new ArrayList<>(newOccupants.subList(0, availableSpaces));
            }
        }

        return eligibleOccupants;
    }

    // 입주자 배치 계산 (타입별)
    public static int calculatePlacementIndex(int index, int type) {
        // type2일 경우 음수 인덱스 방지
        if (type == 2 && index < 0) {
            return 0;
        }
        return index;
    }

    // 쉬프트 방향 결정 및 실행
    public static void applyShiftAndPlace(int newIndex, Person newOccupant) {
        int[] currentRC = getRowColFromIndex(newIndex);
        int middleRoomInFloor = ROOMS_PER_FLOOR / 2;

        if (currentRC[1] <= middleRoomInFloor) {
            processShiftLeft(newIndex, newOccupant);
        } else {
            processShiftRight(newIndex, newOccupant);
        }
    }

    // 남은 입주자 다른 층에 배치
    public static void placeRemainingOccupants(List<Person> eligibleOccupants, int alreadyPlaced) {
        if (eligibleOccupants.size() <= alreadyPlaced) {
            return; // 모든 입주자가 이미 배치됨
        }

        for (int i = alreadyPlaced; i < eligibleOccupants.size(); i++) {
            Person person = eligibleOccupants.get(i);
            boolean placed = findEmptyRoomAndPlace(person);

            if (!placed) {
                System.out.println("더 이상 재배치할 공간이 없습니다.");
            }
        }
    }

    // 빈 방 찾아서 배치
    public static boolean findEmptyRoomAndPlace(Person person) {
        // 501호부터 순차적으로 빈 방 찾기
        for (int floor = FLOORS - 1; floor >= 0; floor--) {
            for (int room = 0; room < ROOMS_PER_FLOOR; room++) {
                if (apartment[floor][room] == null) {
                    apartment[floor][room] = person;
                    System.out.println("입주민이 " + ((floor + 1) * 100 + (room + 1)) + "호로 재배치되었습니다.");
                    return true;
                }
            }
        }
        return false;
    }

    // 호실 번호 입력
    public static String inputRoomNumber(String action) {
        System.out.printf("%s할 호실 번호를 입력하세요 (예: 101): ", action);
        return sc.nextLine().trim();
    }

    // 이사(퇴실) 처리
    public static void processMoveOut() {
        String roomStr = inputRoomNumber("이사");
        int[] rc = getRowColFromRoomNumber(roomStr);
        if (rc == null) {
            System.out.println("잘못된 호실 번호입니다.");
            return;
        }

        processVacate(rc, roomStr);
    }

    // 퇴실 처리
    public static void processVacate(int[] rc, String roomStr) {
        if (apartment[rc[0]][rc[1]] == null) {
            System.out.println(roomStr + "호는 이미 빈 방입니다.");
        } else {
            apartment[rc[0]][rc[1]] = null;
            System.out.println(roomStr + "호의 사람이 이사(퇴실)하였습니다.");
        }
    }

    /**
     * 5. 초기화 및 메인 메뉴 실행
     */
    // 아파트 초기화
    public static void initializeApartment() {
        apartment[0][0] = new Person("d", 4);
        apartment[0][1] = new Person("e", 5);
        apartment[0][2] = new Person("h", 8);
        apartment[1][0] = new Person("f", 6);
        apartment[2][0] = new Person("g", 7);
    }

    // 메인 메뉴 실행
    public static void runMainMenu() {
        int type = 1; // type 기본값은 1type

        while (true) {
            printApartment();
            System.out.println("[1. 이사(퇴실)  2. 입주  3. 종료  4. 타입 선택]");
            System.out.print("무엇을 선택하시겠습니까? : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    processMoveOut();
                    break;
                case 2:
                    processMoveIn(type);
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 4:
                    System.out.println("입주는 어떤 타입으로 하시겠습니까? 1. type1 2.type2");
                    type = sc.nextInt();
                    sc.nextLine();
                    System.out.println("타입 " + type + "이 선택되었습니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        }
    }

    /**
     * 6. 유틸리티 메서드
     */
    // 랜덤으로 나이 지정
    public static int getRandomAge() {
        int usedCount = 0;
        for (int i = MIN_AGE; i <= MAX_AGE; i++) {
            if (usedAges[i]) usedCount++;
        }

        if (usedCount >= (MAX_AGE - MIN_AGE + 1)) {
            System.out.println("더 이상 나이 지정이 불가능합니다.");
            return -1;
        }

        int age;
        do {
            age = random.nextInt(MAX_AGE - MIN_AGE + 1) + MIN_AGE;
        } while (usedAges[age]);

        usedAges[age] = true;
        return age;
    }

    // 2차원 좌표->1차원 인덱스
    public static int get1DIndex(int row, int col) {
        return row * ROOMS_PER_FLOOR + col;
    }

    // 1차원 인덱스->2차원 좌표
    public static int[] getRowColFromIndex(int index) {
        int row = index / ROOMS_PER_FLOOR;
        int col = index % ROOMS_PER_FLOOR;
        return new int[]{row, col};
    }

    // 호수를 좌표로 변환
    public static int[] getRowColFromRoomNumber(String roomStr) {
        try {
            roomStr = roomStr.replaceAll("[^0-9]", "");
            int roomNum = Integer.parseInt(roomStr);
            int floor = roomNum / 100;
            int roomNo = roomNum % 100;
            int row = floor - 1;
            int col = roomNo - 1;
            if (row < 0 || row >= FLOORS || col < 0 || col >= ROOMS_PER_FLOOR) {
                return null;
            }
            return new int[]{row, col};
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 7. 블록 쉬프트 관련 메서드
     */
    // 블록 쉬프트 처리
    public static void processBlockShift(int targetIndex, List<Person> newOccupants) {
        int numToInsert = newOccupants.size();

        // 방 위치에 따라 쉬프트 방향 결정
        int[] targetRC = getRowColFromIndex(targetIndex);
        int targetFloor = targetRC[0];
        int targetRoom = targetRC[1];
        int middleRoom = ROOMS_PER_FLOOR / 2;

        // 중간 방을 기준으로 왼쪽/오른쪽 방향 결정
        boolean pushLeft = (targetRoom <= middleRoom);

        // 1. 현재 층의 상태를 임시 배열에 복사
        Person[] floorCopy = new Person[ROOMS_PER_FLOOR];
        for (int i = 0; i < ROOMS_PER_FLOOR; i++) {
            floorCopy[i] = apartment[targetFloor][i];
        }

        // 2. 빈 방 개수 확인
        int emptyRooms = 0;
        for (int i = 0; i < ROOMS_PER_FLOOR; i++) {
            if (floorCopy[i] == null) {
                emptyRooms++;
            }
        }

        // 3. 빈 방이 충분하면 기존 입주자 이동 없이 바로 배치
        if (emptyRooms >= numToInsert) {
            placeDirect(targetFloor, targetRoom, pushLeft, numToInsert, newOccupants);
        } else {
            // 4. 빈 방이 부족하면 밀어내기 수행
            if (pushLeft) {
                pushOccupantsLeft(targetFloor, targetRoom, numToInsert, floorCopy, newOccupants);
            } else {
                pushOccupantsRight(targetFloor, targetRoom, numToInsert, floorCopy, newOccupants);
            }
        }

        // 각 입주자마다 메시지 출력
        for (int i = 0; i < numToInsert; i++) {
            System.out.println("입주가 완료되었습니다.");
        }
    }

    // 직접 배치 (충분한 빈 방이 있을 때)
    private static void placeDirect(int targetFloor, int targetRoom, boolean pushLeft, int numToInsert, List<Person> newOccupants) {
        for (int i = 0; i < numToInsert; i++) {
            int roomToPlace = pushLeft ? targetRoom - i : targetRoom + i;

            // 방 번호가 유효한지 확인
            if (roomToPlace >= 0 && roomToPlace < ROOMS_PER_FLOOR) {
                // 이미 입주자가 있는 경우 다른 방으로 이동
                if (apartment[targetFloor][roomToPlace] != null) {
                    // 빈 방 찾기
                    int emptyRoom = -1;
                    for (int j = 0; j < ROOMS_PER_FLOOR; j++) {
                        if (apartment[targetFloor][j] == null) {
                            emptyRoom = j;
                            break;
                        }
                    }

                    if (emptyRoom != -1) {
                        // 기존 입주자를 빈 방으로 이동
                        apartment[targetFloor][emptyRoom] = apartment[targetFloor][roomToPlace];
                    } else {
                        // 빈 방이 없으면 다른 층으로 이동
                        findEmptyRoomAndPlace(apartment[targetFloor][roomToPlace]);
                    }
                }

                // 새 입주자 배치
                apartment[targetFloor][roomToPlace] = newOccupants.get(i);
            } else {
                // 방 번호가 유효하지 않으면 다른 층에 배치
                findEmptyRoomAndPlace(newOccupants.get(i));
            }
        }
    }

    // 왼쪽으로 밀기
    private static void pushOccupantsLeft(int targetFloor, int targetRoom, int numToInsert, Person[] floorCopy, List<Person> newOccupants) {
        // 필요한 공간만큼 왼쪽으로 밀기
        for (int i = 0; i < ROOMS_PER_FLOOR - numToInsert; i++) {
            if (i < targetRoom - numToInsert + 1) {
                apartment[targetFloor][i] = floorCopy[i + numToInsert];
            } else {
                apartment[targetFloor][i] = floorCopy[i];
            }
        }

        // 밀린 입주자 중 방을 잃은 사람들을 다른 곳에 배치
        for (int i = 0; i < numToInsert; i++) {
            if (i < targetRoom + 1) {
                if (floorCopy[i] != null) {
                    findEmptyRoomAndPlace(floorCopy[i]);
                }
            }
        }

        // 새 입주자 배치
        for (int i = 0; i < numToInsert; i++) {
            int roomToPlace = targetRoom - i;
            if (roomToPlace >= 0) {
                apartment[targetFloor][roomToPlace] = newOccupants.get(i);
            } else {
                findEmptyRoomAndPlace(newOccupants.get(i));
            }
        }
    }

    // 오른쪽으로 밀기
    private static void pushOccupantsRight(int targetFloor, int targetRoom, int numToInsert, Person[] floorCopy, List<Person> newOccupants) {
        // 필요한 공간만큼 오른쪽으로 밀기
        for (int i = ROOMS_PER_FLOOR - 1; i >= numToInsert; i--) {
            if (i > targetRoom + numToInsert - 1) {
                apartment[targetFloor][i] = floorCopy[i - numToInsert];
            } else {
                apartment[targetFloor][i] = floorCopy[i];
            }
        }

        // 밀린 입주자 중 방을 잃은 사람들을 다른 곳에 배치
        for (int i = ROOMS_PER_FLOOR - 1; i >= ROOMS_PER_FLOOR - numToInsert; i--) {
            if (i > targetRoom) {
                if (floorCopy[i] != null) {
                    findEmptyRoomAndPlace(floorCopy[i]);
                }
            }
        }

        // 새 입주자 배치
        for (int i = 0; i < numToInsert; i++) {
            int roomToPlace = targetRoom + i;
            if (roomToPlace < ROOMS_PER_FLOOR) {
                apartment[targetFloor][roomToPlace] = newOccupants.get(i);
            } else {
                findEmptyRoomAndPlace(newOccupants.get(i));
            }
        }
    }
}