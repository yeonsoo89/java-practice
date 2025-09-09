package level02.normal;

import java.util.Random;

public class RandomMaker {
    RandomMaker randomMaker = new RandomMaker();
    public static void main(String[] args) {
        System.out.println(RandomMaker.randomNumber());
        System.out.println(RandomMaker.rockPaperScissors());
        System.out.println(RandomMaker.tossCoin());

    }

    public static int randomNumber() {
        Random random = new Random();
        int randomNum = random.nextInt(10) + 1;
        return randomNum;
    }

    //public static String randomUpperAlphabet(int a) {

    //}

    public static String rockPaperScissors() {
        String[] rps = {"가위", "바위", "보"};
        Random random = new Random();
        int index = random.nextInt(rps.length);
        return rps[index];
    }

    public static String tossCoin() {
        String[] coin = {"앞면", "뒷면"};
        Random random = new Random();
        int index = random.nextInt(coin.length);
        return coin[index];
    }

}
