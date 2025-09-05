package level01.basic;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.checkMethod();
        System.out.println(calculator.sum1to10());
        System.out.println(calculator.checkMaxNumber(10,20));
        System.out.println(calculator.sumTwoNumber(10, 20));
        System.out.println(calculator.minusTwoNumber(10, 5));
    }

    public void checkMethod(){
        System.out.println("메소드 호출 확인");
    }

    public int sum1to10() {
        return (int)(1+2+3+4+5+6+7+8+9+10);
    }

    public int checkMaxNumber(int a, int b) {
        return Math.max(a, b);
    }

    public int sumTwoNumber(int a, int b) {
        return a+b;
    }

    public int minusTwoNumber(int a, int b) {
        return a - b;
    }

}
