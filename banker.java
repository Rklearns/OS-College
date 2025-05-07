import java.util.*;

public class banker {
    static int n = 5; // number of processes
    static int m = 3; // number of resource types

    static int[] available = {3, 3, 2}; // Available resources

    static int[][] max = {
        {7, 5, 3},
        {3, 2, 2},
        {9, 0, 2},
        {2, 2, 2},
        {4, 3, 3}
    };

    static int[][] allocation = {
        {0, 1, 0},
        {2, 0, 0},
        {3, 0, 2},
        {2, 1, 1},
        {0, 0, 2}
    };

    static int[][] need = new int[n][m];

    public static void main(String[] args) {
        // Calculate the need matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                need[i][j] = max[i][j] - allocation[i][j];

        // Check system safety
        isSafe();
    }

    static void isSafe() {
        boolean[] finish = new boolean[n];
        int[] safeSequence = new int[n];
        int[] work = Arrays.copyOf(available, m);
        int count = 0;

        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > work[j])
                            break;
                    }
                    if (j == m) {
                        for (int k = 0; k < m; k++)
                            work[k] += allocation[i][k];
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is NOT in a safe state.");
                return;
            }
        }

        System.out.println("System is in a SAFE state.");
        System.out.print("Safe sequence is: ");
        for (int i = 0; i < n; i++)
            System.out.print("P" + safeSequence[i] + " ");
    }
}

