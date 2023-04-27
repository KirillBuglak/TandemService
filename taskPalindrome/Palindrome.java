package ru.tandemservice.test.taskPalindrome;

import java.util.Scanner;

public class Palindrome {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PlayerRepo playerRepo = new PlayerRepo();

    public static boolean isPalindrome(String s) {
        String chars = getChars(s);
        return chars.equals(new StringBuilder(chars).reverse().toString());
    }

    public static String getChars(String s) {
        return s.toLowerCase().replaceAll(" ", "");
    }

    public static void startTheGame() {
        while (true) {
            Messages.initial();
            String playerName = scanner.nextLine();
            if (playerName.equals("-выход")) {
                Messages.exit();
                break;
            }
            if (!playerRepo.savedUser(playerName)) {
                playerRepo.addUser(playerName);
            }
            Messages.enterPalindrome();
            String phrase = scanner.nextLine();
            while (Palindrome.isPalindrome(phrase) && !phrase.isBlank() && phrase.length() > 1) {
                if (playerRepo.playerHasThisPalindrome(playerName, phrase)) {
                    Messages.alreadyEntered();
                } else {
                    int sc = Palindrome.getChars(phrase).length();
                    playerRepo.addScore(playerName, sc);
                    playerRepo.addPalindrome(playerName, phrase);
                    Messages.congrats(sc);
                    Messages.enterPalindrome();
                }
                phrase = scanner.nextLine();
            }
            Messages.sorry();
            playerRepo.printTable();
        }
    }

    static class Messages {
        private static void initial() {
            System.out.println("Введите имя или -выход: ");
        }

        private static void enterPalindrome() {
            System.out.println("Введите палиндром: ");
        }

        private static void exit() {
            System.out.println("Выход");
        }

        private static void sorry() {
            System.out.println("Извините, это не палиндром");
        }

        private static void alreadyEntered() {
            System.out.println("Вы уже вводили этот палиндром, введите новый");
        }

        private static void congrats(int sc) {
            System.out.printf("Отлично, вы заработали %d баллов!\n", sc);
        }
    }
}
