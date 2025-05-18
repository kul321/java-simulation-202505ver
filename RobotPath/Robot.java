package project.RobotPath;

public class Robot {
    public int[][] map;
    public int robotX, robotY;
    public int destX, destY;

    //0: 빈 공간 (아무것도 없는 곳)
    //1: 장애물이 있는 곳
    //2: 로봇이 있는 위치
    //3: 목적지
    public Robot(int n, int m) {
        map = new int[n][m];
    }

    public void setWall(int x, int y) {
        map[x][y] = 1;  // 1: 장애물
    }

    public void setRobot(int x, int y) {
        robotX = x;
        robotY = y;
        map[x][y] = 2;  // 2: 로봇
    }

    public void setDestination(int x, int y) {
        destX = x;
        destY = y;
        map[x][y] = 3;  // 3: 목적지
    }

    public void showMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) System.out.print("-");
                if (map[i][j] == 1) System.out.print("|");
                if (map[i][j] == 2) System.out.print("R");
                if (map[i][j] == 3) System.out.print("D");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean moveRobot(int newX, int newY) {
        //이동할 수 있는지 확인
        if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length && map[newX][newY] != 1) {
            map[robotX][robotY] = 0;
            if (map[newX][newY] != 3) {
                map[newX][newY] = 2;
            }
            robotX = newX;
            robotY = newY;
            return true;
        }
        return false;
    }

    public void moveToDestination() {
        while (robotX != destX || robotY != destY) {
            System.out.println("현재 위치: (" + robotX + ", " + robotY + ")");

            // 대각선 이동 시도
            if (robotX < destX && robotY < destY) {
                if (moveRobot(robotX + 1, robotY + 1)) {
                    System.out.println("오른쪽 아래 대각선으로 이동");
                    showMap();
                    continue;
                }
            } else if (robotX < destX && robotY > destY) {
                if (moveRobot(robotX + 1, robotY - 1)) {
                    System.out.println("왼쪽 아래 대각선으로 이동");
                    showMap();
                    continue;
                }
            } else if (robotX > destX && robotY < destY) {
                if (moveRobot(robotX - 1, robotY + 1)) {
                    System.out.println("오른쪽 위 대각선으로 이동");
                    showMap();
                    continue;
                }
            } else if (robotX > destX && robotY > destY) {
                if (moveRobot(robotX - 1, robotY - 1)) {
                    System.out.println("왼쪽 위 대각선으로 이동");
                    showMap();
                    continue;
                }
            }

            //세로 방향
            //목적지가 아래쪽
            if (robotX < destX) {
                if (moveRobot(robotX + 1, robotY)) {
                    System.out.println("아래로 이동");
                    showMap();
                    continue;
                }
                //아래로 못 갈 경우
                else {
                    if (robotY < map[0].length - 1 && moveRobot(robotX, robotY + 1)) {
                        System.out.println("오른쪽으로 우회");
                        showMap();
                        continue;
                    } else if (robotY > 0 && moveRobot(robotX, robotY - 1)) {
                        System.out.println("왼쪽으로 우회");
                        showMap();
                        continue;
                    }
                }
            }
            //목적지가 위쪽
            else if (robotX > destX) {
                if (moveRobot(robotX - 1, robotY)) {
                    System.out.println("위로 이동");
                    showMap();
                    continue;
                }
            }

            //가로 방향
            //목적지가 오른쪽에 있으면
            if (robotY < destY) {
                if (moveRobot(robotX, robotY + 1)) {
                    System.out.println("오른쪽으로 이동");
                    showMap();
                    continue;
                }
            }
            //목적지가 왼쪽
            if (robotY > destY) {
                if (moveRobot(robotX, robotY - 1)) {
                    System.out.println("왼쪽으로 이동");
                    showMap();
                    continue;
                }
            }

            //이동 불가
            System.out.println("이동불가능합니다.");
            break;
        }

        if (robotX == destX && robotY == destY) {
            System.out.println("목적지에 도착했습니다!");
        }
    }
}


