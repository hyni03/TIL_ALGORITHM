import java.util.*;

/*
[풀이 의도]
- 전화번호 목록에서 어떤 번호가 다른 번호의 접두어인지 확인하는 문제
- Arrays.sort()를 사용하여 전화번호를 사전순으로 정렬
- 사전순 정렬 시 접두어 관계에 있는 문자열은 서로 인접하게 위치함
- 따라서 모든 전화번호를 비교할 필요 없이 인접한 두 번호만 비교하면 됨
- startsWith()를 사용하여 현재 번호가 이전 번호로 시작하는지 확인
- 접두어 관계가 존재하면 false, 존재하지 않으면 true 반환
- 시간 복잡도: O(N log N × L), 공간 복잡도: O(N) (정렬 구현에 따라 달라짐)
  ※ N: 전화번호 개수, L: 전화번호의 최대 길이
*/

class Solution {
    public boolean solution(String[] phone_book) {

        // 1. 전화번호를 사전순(오름차순)으로 정렬
        // 접두어 관계에 있는 전화번호들이 인접하도록 만듦
        Arrays.sort(phone_book);

        // 2. 인접한 두 전화번호를 비교
        for (int i = 1; i < phone_book.length; i++) {

            // 현재 전화번호가 이전 전화번호로 시작하는지 확인
            // 예: "1195524421".startsWith("119") → true
            if (phone_book[i].startsWith(phone_book[i - 1])) {
                // 접두어 관계가 존재하므로 false 반환
                return false;
            }
        }

        // 3. 모든 인접한 번호를 비교했지만 접두어 관계가 없는 경우
        return true;
    }
}
