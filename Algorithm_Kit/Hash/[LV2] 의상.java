import java.util.*;

/*
[풀이 의도]
- 의상의 종류별로 하나씩 선택하여 만들 수 있는 서로 다른 조합의 수를 구하는 문제
- HashMap을 사용하여 의상 종류별 개수를 저장
  - Key: 의상 종류, Value: 해당 종류에 속하는 의상 개수
- 같은 종류의 의상은 최대 1개만 착용할 수 있음
- 각 의상 종류마다 (의상 개수 + 1)개의 선택지가 존재
  - 의상 개수: 해당 종류에서 하나를 선택하는 경우
  - +1: 해당 종류의 의상을 착용하지 않는 경우
- 각 종류의 선택은 독립적이므로 곱의 법칙을 적용하여 모든 경우의 수를 계산
- 단, 아무것도 착용하지 않는 경우는 제외해야 하므로 최종 결과에서 1을 뺌
- 시간 복잡도: O(N), 공간 복잡도: O(K)
  ※ N: 전체 의상 개수, K: 의상 종류 개수
*/

class Solution {
    public int solution(String[][] clothes) {
        // 모든 의상 조합의 경우의 수를 계산하기 위해 1로 초기화
        int answer = 1;

        // 의상 종류별 개수를 저장할 HashMap
        // Key: 의상 종류, Value: 해당 종류의 의상 개수
        Map<String, Integer> mp = new HashMap<>();

        // 1. 의상 종류별 개수 계산
        for (String[] cloth : clothes) {
            // cloth[0]: 의상 이름
            // cloth[1]: 의상 종류

            // 해당 종류가 존재하면 기존 개수 + 1
            // 존재하지 않으면 0 + 1
            mp.put(cloth[1], mp.getOrDefault(cloth[1], 0) + 1);
        }

        // 2. 각 의상 종류별 선택 가능한 경우의 수를 곱함
        for (int count : mp.values()) {
            // count: 해당 종류의 의상 개수
            // count + 1: 의상을 선택하지 않는 경우까지 포함
            answer *= (count + 1);
        }

        // 3. 아무것도 착용하지 않는 경우 1가지를 제외
        return answer - 1;
    }
}
