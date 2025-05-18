package project.Energy;

public class Alliance {
    public Zone[] members;           // 동맹 멤버 배열
    public int memberCount;          // 현재 멤버 수
    public String[] rejectedPairs;   // 거절된 요청 기록
    public int[] rejectedTurns;      // 거절된 시점
    public int rejectedCount;        // 거절 기록 수
    public static int max_members = 10;
    public static int max_rejected = 20;

    public Alliance() {
        this.members = new Zone[max_members];
        this.rejectedPairs = new String[max_rejected];
        this.rejectedTurns = new int[max_rejected];
        this.memberCount = 0;
        this.rejectedCount = 0;
    }

    //동맹에 새 구역 추가
    public boolean addMember(Zone newZone) {
        if (memberCount >= max_members) {
            return false;
        }

        // 이미 다른 동맹에 속해있다면 해제
        if (newZone.alliance != null) {
            newZone.alliance = null;
        }

        members[memberCount] = newZone;
        newZone.alliance = this;
        memberCount++;
        return true;
    }

    // 기존 동맹에 새 구역 추가
    private static Alliance addToExistingAlliance(Alliance existing, Zone newZone) {
        if (existing.memberCount < max_members) {
            existing.addMember(newZone);
            return existing;
        }
        return null;  // 동맹 추가 실패
    }

    // 새로운 동맹 생성
    public static Alliance createAlliance(Zone zone1, Zone zone2) {
        // zone1이나 zone2가 이미 동맹에 속해있는 경우
        if (zone1.alliance != null) {
            // 기존 동맹에 zone2를 추가
            return addToExistingAlliance(zone1.alliance, zone2);
        } else if (zone2.alliance != null) {
            // 기존 동맹에 zone1을 추가
            return addToExistingAlliance(zone2.alliance, zone1);
        }

        // 둘 다 동맹이 없는 경우 새로운 동맹 생성
        Alliance alliance = new Alliance();
        alliance.members[0] = zone1;
        alliance.members[1] = zone2;
        alliance.memberCount = 2;

        zone1.alliance = alliance;
        zone2.alliance = alliance;

        return alliance;
    }

    // 동맹인지 확인
    public static boolean areAllies(Zone zone1, Zone zone2) {
        return zone1.alliance != null &&
                zone2.alliance != null &&
                zone1.alliance == zone2.alliance;
    }

    // 동맹 요청 거절 기록
    public void rejectRequest(Zone requester, Zone target, int currentTurn) {
        if(rejectedCount < max_rejected) {
            rejectedPairs[rejectedCount] = requester.name + "-" + target.name;
            rejectedTurns[rejectedCount] = currentTurn;
            rejectedCount = rejectedCount + 1;
        }
    }

    // 동맹 요청 가능 여부 확인 (5턴 규칙)
    public boolean canRequestAlliance(Zone requester, Zone target, int currentTurn) {
        String key = requester.name + "-" + target.name;
        for(int i = 0; i < rejectedCount; i = i + 1) {
            if(rejectedPairs[i].equals(key)) {
                if(currentTurn - rejectedTurns[i] < 5) {
                    return false;
                }
            }
        }
        return true;
    }

    // 현재 동맹에 속한 모든 구역을 반환
    public Zone[] getMembers() {
        Zone[] result = new Zone[memberCount];
        for(int i = 0; i < memberCount; i = i + 1) {
            result[i] = members[i];
        }
        return result;
    }

    // 현재 동맹 멤버 수를 반환
    public int getMemberCount() {
        return memberCount;
    }
}
