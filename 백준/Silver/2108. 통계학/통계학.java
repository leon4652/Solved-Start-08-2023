import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        
        // 산술평균
        int sum = Arrays.stream(numbers).sum();
        System.out.println((int) Math.round((double) sum / N));
        
        // 중앙값
        System.out.println(numbers[N / 2]);
        
        // 최빈값
        System.out.println(findMode(numbers));
        
        // 범위
        System.out.println(numbers[N - 1] - numbers[0]);
    }

 private static int findMode(int[] numbers) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int num : numbers) {
        countMap.put(num, countMap.getOrDefault(num, 0) + 1);
    }

    List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
    entryList.sort((e1, e2) -> {
        int freqCompare = Integer.compare(e2.getValue(), e1.getValue());
        if (freqCompare == 0) return Integer.compare(e1.getKey(), e2.getKey());
        else return freqCompare;
    });

    if (entryList.size() > 1 && entryList.get(0).getValue().equals(entryList.get(1).getValue())) {
        return entryList.get(1).getKey();
    } else {
        return entryList.get(0).getKey();
    }
}

}
