class Solution {
    public int totalNumbers(int[] digits) {
        // Count the frequency of each digit (0-9) available in the input array
        int[] counts = new int[10];
        for (int digit : digits) {
            counts[digit]++;
        }

        int validCount = 0;

        // Iterate through all possible 3-digit even numbers (100 to 998)
        for (int num = 100; num <= 998; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;

            // Temporarily use the required digits
            counts[hundreds]--;
            counts[tens]--;
            counts[units]--;

            // If we haven't dropped below 0 for any digit, the number can be formed
            if (counts[hundreds] >= 0 && counts[tens] >= 0 && counts[units] >= 0) {
                validCount++;
            }

            // Backtrack and restore the counts for the next iteration
            counts[hundreds]++;
            counts[tens]++;
            counts[units]++;
        }

        return validCount;
    }
}
