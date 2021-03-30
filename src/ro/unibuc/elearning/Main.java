package ro.unibuc.elearning;

import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int opt = cin.nextInt();
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        while (opt > 0) {
            if (opt == 1) { //adauga utilizator
                eLearningPlatformService.addUser(cin);
            }
            opt = cin.nextInt();
        }
    }
}
