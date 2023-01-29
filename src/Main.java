import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static final int[] cases = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static void calc(String input) throws IOException {
        input = input.replaceAll(" ", "");
        String[] operands = input.split("[+\\-*/]");

        if (input.replaceAll("[IVXivx\\d+\\-*/]", "").length() > 0 ||
                operands.length != 2 ||
                (Arrays.stream(operands).anyMatch(Main::isRomanNumber) &&
                        Arrays.stream(operands).anyMatch(Main::isArabicNumber))) {
            throw new IOException();
        }

        if (Arrays.stream(operands).allMatch(Main::isArabicNumber)) {
            switch (input.replaceAll("\\d", "")) {
                case "+" -> System.out.println(Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]));
                case "-" -> System.out.println(Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]));
                case "*" -> System.out.println(Integer.parseInt(operands[0]) * Integer.parseInt(operands[1]));
                case "/" -> System.out.println(Integer.parseInt(operands[0]) / Integer.parseInt(operands[1]));
            }
        } else if (Arrays.stream(operands).allMatch(Main::isRomanNumber)) {
            switch (input.replaceAll("[IVXivx]", "")) {
                case "+" -> System.out.println(convertingToRome(convertingToArabic(operands[0]) +
                        convertingToArabic(operands[1])));
                case "-" -> System.out.println(convertingToRome(convertingToArabic(operands[0]) -
                        convertingToArabic(operands[1])));
                case "*" -> System.out.println(convertingToRome(convertingToArabic(operands[0]) *
                        convertingToArabic(operands[1])));
                case "/" -> System.out.println(convertingToRome(Math.floorDiv(convertingToArabic(operands[0]),
                        convertingToArabic(operands[1]))));
            }
        }
    }

    public static boolean isRomanNumber(String number) {
        return number.replaceAll("^[IVXivx]+$", "").length() == 0;
    }

    public static boolean isArabicNumber(String number) {
        return number.replaceAll("^(10|[0-9])$", "").length() == 0;
    }

    public static int convertingToArabic(String input) {

        switch (input) {
            case "I" -> {
                return 1;
            }
            case "II" -> {
                return 2;
            }
            case "III" -> {
                return 3;
            }
            case "IV" -> {
                return 4;
            }
            case "V" -> {
                return 5;
            }
            case "VI" -> {
                return 6;
            }
            case "VII" -> {
                return 7;
            }
            case "VIII" -> {
                return 8;
            }
            case "IX" -> {
                return 9;
            }
            case "X" -> {
                return 10;
            }
        }
        return 0;
    }

    public static String convertingToRome(int input) throws IOException {
        if (input <= 0) {
            throw new IOException();
        }
        StringBuilder output = new StringBuilder();

        for (int i : cases) {
            if (input >= i) {
                switch (i) {
                    case 1 -> output.append("I");
                    case 2 -> output.append("II");
                    case 3 -> output.append("III");
                    case 4 -> output.append("IV");
                    case 5 -> output.append("V");
                    case 6 -> output.append("VI");
                    case 7 -> output.append("VII");
                    case 8 -> output.append("VIII");
                    case 9 -> output.append("IX");
                    case 10 -> output.append("X");
                    case 20 -> output.append("XX");
                    case 30 -> output.append("XXX");
                    case 40 -> output.append("XL");
                    case 50 -> output.append("L");
                    case 60 -> output.append("LX");
                    case 70 -> output.append("LXX");
                    case 80 -> output.append("LXXX");
                    case 90 -> output.append("XC");
                    case 100 -> output.append("C");
                }
                input -= i;
            }
        }
        return output.toString();
    }
}
