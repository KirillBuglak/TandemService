package ru.tandemservice.test.taskPalindrome;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepo {
    private final Map<String, Player> players = new HashMap<>();

    public void addUser(String playerName) {
        players.put(playerName, new Player(playerName));
    }

    public void addScore(String playerName, int sc) {
        players.get(playerName).addScore(sc);
    }

    public void addPalindrome(String playerName, String phrase) {
        players.get(playerName).addPalindrome(phrase);
    }

    public boolean playerHasThisPalindrome(String playerName, String phrase) {
        return players.get(playerName).getPalindromes().stream()
                .filter(p -> p.equals(phrase)).findAny().orElse(null) != null;
    }

    public boolean savedUser(String userName) {
        return players.get(userName) != null;
    }

    public void printTable() {
        System.out.println("Таблица");
        players.entrySet().stream().sorted((e1, e2) -> e2.getValue().getScore() - e1.getValue().getScore())
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue().getScore()));
        System.out.println();
    }
}
