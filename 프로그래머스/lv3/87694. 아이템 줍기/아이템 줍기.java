import java.util.*;

class Solution {
    static int[][] visited = new int[101][101]; // 1~50까지 칸
    static int shortestDistance = Integer.MAX_VALUE;
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};
    
    public static int solution(int[][] rectangles, int startX, int startY, int itemX, int itemY) {
        startX *= 2;
        startY *= 2;
        itemX *= 2;
        itemY *= 2;

        // Fill in all the rectangles
        for (int i = 0; i < rectangles.length; i++) {
            for (int x = rectangles[i][0] * 2; x <= rectangles[i][2] * 2; x++) {
                for (int y = rectangles[i][1] * 2; y <= rectangles[i][3] * 2; y++) {
                    visited[x][y] = 7;
                }
            }
        }

        // Clear the insides of rectangles
        for (int i = 0; i < rectangles.length; i++) {
            for (int x = rectangles[i][0] * 2 + 1; x <= rectangles[i][2] * 2 - 1; x++) {
                for (int y = rectangles[i][1] * 2 + 1; y <= rectangles[i][3] * 2 - 1; y++) {
                    visited[x][y] = 0;
                }
            }
        }

        visited[startX][startY] = 1;
        findPath(startX, startY, itemX, itemY, 0);

        return shortestDistance / 2;
    }
    
    public static void findPath(int currentX, int currentY, int targetX, int targetY, int steps) {
        if (currentX == targetX && currentY == targetY) { // Arrived!
            shortestDistance = Math.min(shortestDistance, steps);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = currentX + moveX[i];
            int nextY = currentY + moveY[i];
            
            if (nextX >= 0 && nextX < 101 && nextY >= 0 && nextY < 101 && visited[nextX][nextY] == 7) {
                visited[nextX][nextY] = 0;
                findPath(nextX, nextY, targetX, targetY, steps + 1);
                visited[nextX][nextY] = 7;
            }
        }
    }
}
