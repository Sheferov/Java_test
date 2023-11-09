import java.util.Scanner;
class Main {
    private static int romanToInt(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I': num = 1;
                break;
                case 'V': num = 5;
                break;
                case 'X': num = 10;
                break;
                case 'L': num = 50;
                break;
                case 'C': num = 100;
                break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

    private static String intToRoman(int num) {

        String[] romanNumerals = {"C","L","X","V","I"};
        int[] values = {100,50,10, 5, 1};;

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (num > 0) {
            if (num >= values[i]) {

                sb.append(romanNumerals[i]);
                num -= values[i];
            } else i++;
        }

        return sb.toString();
    }
    private static boolean isRomanNumeral(String num) {
        return num.matches("[IVXLC]+");
    }

    public static String calc(String input) {
        String[] parts = input.split("[+,--,*,/]");
        boolean flag;
        int num1;
        int num2;
        if (parts.length!=2){
            throw new UnsupportedOperationException("Cтрока не является математической операцией");
        }
        if (isRomanNumeral(parts[0])&&isRomanNumeral(parts[1])){
            num1 = romanToInt(parts[0]);
            num2 = romanToInt(parts[1]);
            flag = true;
        }
        else {
            try{
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e) {
                throw new UnsupportedOperationException("Используются одновременно разные системы счисления");
            }
            flag = false;
        }
        if ((num1>10 || num1<=0) || (num2>10 || num2<=0)) {
            throw new UnsupportedOperationException("Числа не удовлетворяют условиям ");
        }
        char operator = input.charAt(parts[0].length());

        int result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new UnsupportedOperationException("Неподдерживаемая операция: " + operator);

        }
        if (flag){
            if (result<=0){
                throw new UnsupportedOperationException("В римской системе нет отрицательных чисел");
            }
            return intToRoman(result);
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine();

        String result = calc(input);
        System.out.println("Результат: " + result);
    }
}
