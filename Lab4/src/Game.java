import java.util.HashSet;
import java.util.Random;

public class Game {
    Random random = new Random();
    public HashSet<Integer> winSet;

    public Game() {
        winSet = new HashSet<Integer>();
        winningLotNumber();
    }

    public void winningLotNumber() {
        winSet.clear();
        while (winSet.size() < 5) {
            winSet.add(random.nextInt(1, 42));
        }
    }

    public int numMatch(HashSet<Integer> playerNums) {
        System.out.println("Winning Numbers: " + winSet);
        System.out.println("Player Numbers: " + playerNums);
        int count = 0;
        Object[] playNums = playerNums.toArray();
        for (Object o : playNums) {
            if (winSet.contains((Integer) o)) {
                count++;
            }
        }
        return count;
    }

    public float winnings(int match) {
        switch(match) {
            case 1 -> {
                System.out.println("Winnings: $" + 0.0f + "\n");
                return 0.0f;
            }
            case 2 -> {
                System.out.println("Winnings: $" + 1.0f + "\n");
                return 1.0f;
            }
            case 3 -> {
                System.out.println("Winnings: $" + 10.86f + "\n");
                return 10.86f;
            }
            case 4 -> {
                System.out.println("Winnings: $" + 197.53f + "\n");
                return 197.53f;
            }
            case 5 -> {
                System.out.println("Winnings: $" + 212534.83f + "\n");
                return 212534.83f;
            }
        }
        System.out.println("Winnings: $" + -1.0f + "\n");
        return -1.0f;
    }
}
