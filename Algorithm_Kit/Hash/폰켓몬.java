import java.util.*;

/*
[풀이 의도]
- N마리의 폰켓몬 중 N/2마리를 선택할 때, 최대한 다양한 종류를 선택하는 문제
- HashSet을 사용하여 중복된 폰켓몬 번호를 제거하고 서로 다른 종류의 개수를 구함
- 선택할 수 있는 폰켓몬 수는 nums.length / 2마리로 제한됨
- 따라서 선택 가능한 최대 종류 수는 min(선택 가능한 폰켓몬 수, 전체 폰켓몬 종류 수)
- 시간 복잡도: O(N), 공간 복잡도: O(N)
*/

class Solution {
    public int solution(int[] nums) {  
        // 중복을 허용하지 않는 HashSet 생성
        Set<Integer> st = new HashSet<>();
        
        // 폰켓몬 번호를 Set에 저장하여 중복 제거
        for (int num : nums) {
            st.add(num);
        }
        
        // nums.length / 2 : 선택할 수 있는 폰켓몬 수
        // st.size() : 중복을 제거한 폰켓몬 종류 수
        // 두 값 중 작은 값이 선택할 수 있는 최대 폰켓몬 종류 수
        return Math.min(nums.length / 2, st.size());
    }
}
