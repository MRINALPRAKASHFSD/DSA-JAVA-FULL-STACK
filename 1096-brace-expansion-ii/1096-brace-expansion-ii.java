import java.util.*;

class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        return new ArrayList<>(result);
    }

    private Set<String> parse(String s) {
        Set<String> result = new TreeSet<>();

        while (index < s.length() && s.charAt(index) != '}') {
            Set<String> current = parseTerm(s);

            result.addAll(current);

            if (index < s.length() && s.charAt(index) == ',') {
                index++;
            }
        }

        return result;
    }

    private Set<String> parseTerm(String s) {
        Set<String> result = new TreeSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> current = parseFactor(s);
            Set<String> next = new TreeSet<>();

            for (String a : result) {
                for (String b : current) {
                    next.add(a + b);
                }
            }

            result = next;
        }

        return result;
    }

    private Set<String> parseFactor(String s) {
        Set<String> result = new TreeSet<>();

        if (s.charAt(index) == '{') {
            index++;

            result = parse(s);

            index++; // skip '}'
        } else {
            result.add(String.valueOf(s.charAt(index)));
            index++;
        }

        return result;
    }
}