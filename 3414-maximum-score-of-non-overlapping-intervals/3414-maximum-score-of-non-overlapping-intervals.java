class Solution {
    private class Interval {
        int start, end, weight, origIndex;
        public Interval(int start, int end, int weight, int origIndex) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.origIndex = origIndex;
        }
    }
    
    private class State {
        long score;
        int[] indices;
        public State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }
    
    private boolean isBetter(State a, State b) {
        if (a.score != b.score) {
            return a.score > b.score; // Prefer higher score
        }
        // If scores are equal, prefer lexicographically smaller index sequence
        int minLen = Math.min(a.indices.length, b.indices.length);
        for (int i = 0; i < minLen; i++) {
            if (a.indices[i] != b.indices[i]) {
                return a.indices[i] < b.indices[i];
            }
        }
        return a.indices.length < b.indices.length;
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(intervals.get(i).get(0), intervals.get(i).get(1), intervals.get(i).get(2), i);
        }
        
        // Sort intervals by start time ascending (then end time ascending)
        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start) return Integer.compare(a.start, b.start);
            return Integer.compare(a.end, b.end);
        });
        
        // Precompute the next non-overlapping interval for each interval using Binary Search
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1;
            int ans = n; // Defaults to n (out of bounds) if no such interval exists
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid].start > arr[i].end) {
                    ans = mid;
                    high = mid - 1; // Try to find an earlier valid start
                } else {
                    low = mid + 1;
                }
            }
            next[i] = ans;
        }
        
        // DP table: dp[i][k] represents the best State from suffix arr[i...n-1] picking at most k intervals
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0L, new int[0]);
            }
        }
        
        // Populate DP table backwards
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                
                State takeNext = dp[next[i]][k - 1];
                long newScore = arr[i].weight + takeNext.score;
                
                // Construct the sorted index array for the 'take' choice
                int[] newIndices = new int[takeNext.indices.length + 1];
                newIndices[0] = arr[i].origIndex;
                for (int m = 0; m < takeNext.indices.length; m++) {
                    newIndices[m + 1] = takeNext.indices[m];
                }
                Arrays.sort(newIndices); 
                
                State take = new State(newScore, newIndices);
                
                // Store the optimal choice
                dp[i][k] = isBetter(take, skip) ? take : skip;
            }
        }
        
        return dp[0][4].indices;
    }
}
