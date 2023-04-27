package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorter implements Comparator<String[]> {

    private static final String regex = "\\d+";
    private final int columnIndex;

    public Sorter(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    private int check(String left, String right) {
        List<String> first = new ArrayList<>();
        List<String> second = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(left);
        while (matcher.find()) {
            first.add(left.substring(matcher.start(), matcher.end()));
        }
        first.add(left.replaceAll(pattern.toString(), ""));
        matcher = pattern.matcher(right);
        while (matcher.find()) {
            second.add(right.substring(matcher.start(), matcher.end()));
        }
        second.add(right.replaceAll(pattern.toString(), ""));
        for (int i = 0; i < Math.max(first.size(), second.size()); i++) {
            String f = first.get(i);
            String s = second.get(i);
            if (f.equals(s)) {
                continue;
            }
            if (matchesRegex(f) && matchesRegex(s)) {
                return Integer.valueOf(f).compareTo(Integer.valueOf(s));
            }
            return f.compareTo(s);
        }
        return left.compareTo(right);
    }

    @Override
    public int compare(String[] s1, String[] s2) {
        String first = s1[columnIndex];
        String second = s2[columnIndex];
        if (first.isEmpty() && second.isEmpty()) {
            return 0;
        }
        if (first.isEmpty()) {
            return -1;
        }
        if (second.isEmpty()) {
            return 1;
        }
        return check(first, second);
    }

    private boolean matchesRegex(String digits) {
        return digits.matches(regex);
    }
}
