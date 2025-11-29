package exercice4;

import java.util.Stack;

public class MaxRectangle {

    static class Rectangle {
        int top;
        int left;
        int bottom;
        int right;
        int area;

        @Override
        public String toString() {
            return "Rectangle : top=" + top + ", left=" + left +
                   ", bottom=" + bottom + ", right=" + right +
                   ", area=" + area;
        }
    }

   
    public static Rectangle trouverMaxRectangle(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return null;

        int R = m.length;
        int C = m[0].length;

        int[][] h = new int[R][C];

        for (int j = 0; j < C; j++) {
            h[0][j] = m[0][j] == 1 ? 1 : 0;
        }

        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (m[i][j] == 1)
                    h[i][j] = h[i - 1][j] + 1;
                else
                    h[i][j] = 0;
            }
        }

        Rectangle best = new Rectangle();
        best.area = 0;

        for (int i = 0; i < R; i++) {
            Rectangle r = largestRectangleInHistogram(h[i], i);
            if (r.area > best.area) {
                best = r;
            }
        }

        return best;
    }
    private static Rectangle largestRectangleInHistogram(int[] heights, int bottomLine) {
        Stack<Integer> stack = new Stack<>();
        Rectangle best = new Rectangle();
        best.area = 0;

        int C = heights.length;
        int i = 0;

        while (i < C) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                best = updateBestRectangle(heights, bottomLine, stack, best, i);
            }
        }

        while (!stack.isEmpty()) {
            best = updateBestRectangle(heights, bottomLine, stack, best, C);
        }

        return best;
    }
    private static Rectangle updateBestRectangle(int[] heights, int bottom, Stack<Integer> stack, Rectangle best, int i) {
        int topIndex = stack.pop();
        int height = heights[topIndex];

        int right = i - 1;
        int left = stack.isEmpty() ? 0 : stack.peek() + 1;

        int area = height * (right - left + 1);

        if (area > best.area) {
            Rectangle r = new Rectangle();
            r.area = area;
            r.left = left;
            r.right = right;
            r.bottom = bottom;
            r.top = bottom - height + 1;
            best = r;
        }

        return best;
    }

    public static int maxRectangle(int[][] m) {
        Rectangle r = trouverMaxRectangle(m);
        return r.area;
    }

    public static void main(String[] args) {

        int[][] mat1 = {
                {0,1,1,0,1},
                {1,1,1,1,0},
                {1,1,1,1,0},
                {1,1,0,0,1}
        };

        int[][] mat2 = {
                {1,1,0,1},
                {1,1,0,1},
                {0,0,1,1}
        };

        int[][] mat3 = {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };

        int[][] mat4 = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };

        test(mat1, "mat1");
        test(mat2, "mat2");
        test(mat3, "mat3 (tout à 1)");
        test(mat4, "mat4 (tout à 0)");
    }

    private static void test(int[][] m, String name) {
        System.out.println("==== Test " + name + " ====");
        Rectangle r = trouverMaxRectangle(m);
        System.out.println(r);
        System.out.println();
    }
}
