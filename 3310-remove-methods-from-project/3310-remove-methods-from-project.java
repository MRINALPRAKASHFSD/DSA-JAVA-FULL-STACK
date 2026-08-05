import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the directed graph using an adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }

        // Step 2: Use BFS to find all suspicious methods
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph[curr]) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Step 3: Verify if any non-suspicious method invokes a suspicious one
        boolean canRemove = true;
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                for (int neighbor : graph[i]) {
                    if (isSuspicious[neighbor]) {
                        canRemove = false;
                        break;
                    }
                }
            }
            if (!canRemove) break; // Early exit if we find a violation
        }

        // Step 4: Populate the result list based on the removal check
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // If we can't remove them, add everything. 
            // If we can remove them, only add the non-suspicious ones.
            if (!canRemove || !isSuspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
