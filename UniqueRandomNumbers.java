import java.util.Random;

public class UniqueRandomNumbers {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Random myRandom = new Random();
        int[] numbers = new int[9];
        boolean[] check = new boolean[9];
        int amountFilled = 0;
        int trial;
        while (amountFilled < 9) {
            trial = myRandom.nextInt(9);
            if (!check[trial]) {
                check[trial] = true;
                numbers[amountFilled] = trial;
                amountFilled++;
            }
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(numbers[i]);
        }
    }
}