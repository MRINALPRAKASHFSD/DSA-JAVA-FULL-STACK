import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Store pairs of [value, original_index]
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }

        // Sort pairs primarily by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] res = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i + 1;
            // Find the boundary of the current connected component
            while (j < n && paired[j][0] - paired[j - 1][0] <= limit) {
                j++;
            }

            // Extract and sort the original indices for this specific component
            int[] indices = new int[j - i];
            for (int k = i; k < j; k++) {
                indices[k - i] = paired[k][1];
            }
            Arrays.sort(indices);

            // Place the sorted values back into the sorted available indices
            for (int k = i; k < j; k++) {
                res[indices[k - i]] = paired[k][0];
            }

            // Move to the next disconnected component
            i = j;
        }

        return res;
    }
}
