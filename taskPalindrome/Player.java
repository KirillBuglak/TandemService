package ru.tandemservice.test.taskPalindrome;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;// даже не понадобилось
    private int score;
    private final List<String> palindromes;

    public Player(String name) {
        this.name = name;
        this.palindromes = new ArrayList<>();
    }

    public void addPalindrome(String s) {
        palindromes.add(s);
    }

    public List<String> getPalindromes() {
        return palindromes;
    }

    public void addScore(int sc) {
        this.score += sc;
    }

    public int getScore() {
        return score;
    }
}
