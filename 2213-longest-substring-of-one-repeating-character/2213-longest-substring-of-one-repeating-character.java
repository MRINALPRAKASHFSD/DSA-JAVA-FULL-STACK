class Solution {
    class Node {
        int size;
        int max;
        int preMax;
        int sufMax;
        char preChar;
        char sufChar;
    }

    Node[] tree;
    char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryCharacters.length();
        tree = new Node[4 * n];
        chars = s.toCharArray();
        
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        build(1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].max;
        }
        
        return ans;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node].size = 1;
            tree[node].max = 1;
            tree[node].preMax = 1;
            tree[node].sufMax = 1;
            tree[node].preChar = chars[start];
            tree[node].sufChar = chars[start];
            return;
        }
        
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            chars[idx] = c;
            tree[node].preChar = c;
            tree[node].sufChar = c;
            return;
        }
        
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        merge(node, 2 * node, 2 * node + 1);
    }

    private void merge(int node, int left, int right) {
        Node curr = tree[node];
        Node l = tree[left];
        Node r = tree[right];

        curr.size = l.size + r.size;
        curr.preChar = l.preChar;
        curr.sufChar = r.sufChar;

        curr.preMax = l.preMax;
        if (l.preMax == l.size && l.sufChar == r.preChar) {
            curr.preMax += r.preMax;
        }

        curr.sufMax = r.sufMax;
        if (r.sufMax == r.size && r.preChar == l.sufChar) {
            curr.sufMax += l.sufMax;
        }

        curr.max = Math.max(l.max, r.max);
        if (l.sufChar == r.preChar) {
            curr.max = Math.max(curr.max, l.sufMax + r.preMax);
        }
    }
}
