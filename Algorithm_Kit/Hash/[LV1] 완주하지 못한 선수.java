import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        // 참가자 이름과 해당 이름의 등장 횟수를 저장할 HashMap
        // Key: 참가자 이름, Value: 참가자 수
        Map<String, Integer> mp = new HashMap<>();

        // 1. 참가자 명단을 순회하며 이름별 등장 횟수 저장
        for (String p : participant) {
            // 이름이 없으면 0, 있으면 기존 횟수를 가져와 1 증가
            // 동명이인이 존재할 수 있으므로 등장 횟수를 관리
            mp.put(p, mp.getOrDefault(p, 0) + 1);
        }

        // 2. 완주자 명단을 순회하며 해당 이름의 등장 횟수 감소
        for (String c : completion) {
            // 완주한 참가자의 등장 횟수를 1 감소
            int n = mp.get(c) - 1;

            // 등장 횟수가 0이면 모든 동명이인이 완주한 것이므로 제거
            if (n == 0) {
                mp.remove(c);
            }
            // 아직 남은 참가자가 있다면 감소한 횟수로 갱신
            else {
                mp.put(c, n);
            }
        }

        // 3. HashMap에 남아 있는 유일한 참가자가 완주하지 못한 사람
        // keySet(): HashMap에 저장된 모든 Key를 Set으로 반환
        // iterator(): Set을 순회하기 위한 Iterator 생성
        // next(): Iterator의 첫 번째 요소(미완주자 이름) 반환
        return mp.keySet().iterator().next();
    }
}
