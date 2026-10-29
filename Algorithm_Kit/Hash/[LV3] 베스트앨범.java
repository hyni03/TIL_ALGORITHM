import java.util.*;

/*
[풀이 의도]
- 장르별 총 재생 횟수가 높은 순서대로 장르를 정렬하고, 각 장르에서 최대 2곡씩 선택하는 문제
- HashMap 2개를 사용하여 장르별 총 재생 횟수와 장르별 상위 2곡을 관리
  - total: 장르별 총 재생 횟수 저장
  - best: 장르별 재생 횟수가 높은 상위 2곡의 고유번호 저장
- 곡의 고유번호는 genres와 plays 배열의 인덱스(i)를 사용
- 곡을 추가할 때마다 정렬하여 장르별 상위 2곡만 유지
  - 재생 횟수가 높은 곡 우선 (내림차순)
  - 재생 횟수가 같으면 고유번호가 낮은 곡 우선 (오름차순)
- 장르별 총 재생 횟수를 기준으로 장르를 내림차순 정렬
- 정렬된 장르 순서대로 상위 2곡의 고유번호를 결과에 추가
- 시간 복잡도: O(N + G log G), 공간 복잡도: O(G)
  ※ N: 전체 곡 개수, G: 장르 개수
  ※ 장르별 곡 목록을 최대 3개까지만 유지하므로 곡 정렬은 O(1)
*/

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // 장르별 총 재생 횟수를 저장
        // Key: 장르, Value: 해당 장르의 총 재생 횟수
        Map<String, Integer> total = new HashMap<>();

        // 장르별 상위 2곡의 고유번호를 저장
        // Key: 장르, Value: 재생 횟수 기준 상위 2곡의 인덱스
        Map<String, List<Integer>> best = new HashMap<>();

        // 1. 모든 곡을 순회하며 장르별 총 재생 횟수와 상위 2곡 관리
        for (int i = 0; i < genres.length; i++) {

            String genre = genres[i];

            // 1-1. 장르별 총 재생 횟수 누적
            // 기존 재생 횟수가 없으면 0부터 시작
            total.put(
                genre,
                total.getOrDefault(genre, 0) + plays[i]
            );

            // 1-2. 해당 장르가 best에 없으면 새로운 ArrayList 생성
            // 이미 존재하는 장르라면 기존 리스트 유지
            best.putIfAbsent(genre, new ArrayList<>());

            // 해당 장르의 상위 곡 목록 가져오기
            List<Integer> songs = best.get(genre);

            // 현재 곡의 고유번호(인덱스)를 추가
            songs.add(i);

            // 1-3. 재생 횟수 기준으로 곡 정렬
            songs.sort((a, b) -> {

                // 재생 횟수가 같으면 고유번호 오름차순
                if (plays[a] == plays[b]) {
                    return Integer.compare(a, b);
                }

                // 재생 횟수가 다르면 재생 횟수 내림차순
                return Integer.compare(plays[b], plays[a]);
            });

            // 1-4. 장르별 최대 2곡만 유지
            // 정렬 후 3번째 곡은 가장 우선순위가 낮으므로 제거
            if (songs.size() > 2) {
                songs.remove(2);
            }
        }

        // 2. 장르별 총 재생 횟수를 기준으로 장르 정렬

        // HashMap의 Key(장르)를 ArrayList로 변환
        List<String> genreList = new ArrayList<>(total.keySet());

        // 장르별 총 재생 횟수 내림차순 정렬
        genreList.sort((a, b) ->
            Integer.compare(total.get(b), total.get(a))
        );

        // 3. 정렬된 장르 순서대로 상위 2곡을 정답에 추가
        List<Integer> answer = new ArrayList<>();

        for (String genre : genreList) {

            // 해당 장르의 상위 2곡 고유번호를 모두 추가
            // addAll(): 컬렉션의 모든 요소를 한 번에 추가
            answer.addAll(best.get(genre));
        }

        // 4. ArrayList<Integer>를 int[] 배열로 변환
        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}
