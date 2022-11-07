package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class Computer {
    private Integer[] answer;
    private int ball;
    private int strike;
    private static final int MAX_LENGTH = 3;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
    private static final String GAME_OVER_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String BALL = "볼";
    private static final String STRIKE = "스트라이크";
    private static final String NOTHING = "낫싱";

    public Computer() {
        answer = new Integer[MAX_LENGTH];
        ball = 0;
        strike = 0;
    }

    public void generateValue() {
        Set<Integer> value = new HashSet<>();
        while (value.size() < MAX_LENGTH) {
            value.add(Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER));
        }
        setToArray(value);
    }

    private void setToArray(Set<Integer> value) {
        int index = 0;
        for (Integer number : value) {
            //TODO 삭제!!
            System.out.print(number);
            answer[index] = number;
            index++;
        }
        System.out.println();
    }

    public boolean isAnswer(Integer[] value) {
        initBallAndStrike();
        countBallAndStrike(value);
        printResult();
        if (strike == 3) {
            System.out.println(GAME_OVER_MESSAGE);
            return true;
        }
        return false;
    }

    private void initBallAndStrike() {
        ball = 0;
        strike = 0;
    }

    private void countBallAndStrike(Integer[] value) {
        for (int index = 0; index < MAX_LENGTH; index++) {
            if (answer[index] == value[index]) {
                strike++;
            }
            isBall(answer[index], index, value);
        }
    }

    private void isBall(int answer, int answerIndex, Integer[] value) {
        int valueIndex = 0;
        for (int number : value) {
            if (number == answer && valueIndex != answerIndex) {
                ball++;
            }
            valueIndex++;
        }
    }

    private void printResult() {
        if (ball == 0 && strike == 0) {
            System.out.println(NOTHING);
        }
        if (strike > 0 && ball == 0) {
            System.out.println(strike + STRIKE);
        }
        if (ball > 0 && strike == 0) {
            System.out.println(ball + BALL);
        }
        if (ball > 0 && strike > 0) {
            System.out.println(ball + BALL + " " + strike + STRIKE);
        }
    }
}