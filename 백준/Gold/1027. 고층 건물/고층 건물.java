import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 빌딩의 수
        int[] heights = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(input[i]);
        }

        int[] result = mostVisibleBuildings(heights);
        System.out.println(result[1]); 
    }

    private static boolean canSee(int[] buildings, int i, int j) {
        int x1 = i, h1 = buildings[i];
        int x2 = j, h2 = buildings[j];
        for (int k = Math.min(i, j) + 1; k < Math.max(i, j); k++) {
            int hk = buildings[k];
            // x1, x2 사이의 k 위치에서 예상되는 높이
            double expectedH = h1 + (double)(h2 - h1) * (k - x1) / (x2 - x1);
            if (expectedH <= hk) {
                return false;
            }
        }
        return true;
    }

    private static int[] mostVisibleBuildings(int[] buildings) {
        int maxView = 0;
        int bestBuilding = 0;
        int n = buildings.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && canSee(buildings, i, j)) {
                    count++;
                }
            }
            if (count > maxView) {
                maxView = count;
                bestBuilding = i;
            }
        }
        return new int[] {bestBuilding + 1, maxView};
    }
}
