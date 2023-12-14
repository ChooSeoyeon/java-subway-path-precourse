package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFunction() {
        String inputFunction = scanner.nextLine();
        validateFunction(inputFunction);
        return inputFunction;
    }

    private void validateFunction(String inputFunction) {
        if (inputFunction.equals("1")) {
            return;
        }
        if (inputFunction.equals("Q")) {
            return;
        }
        throw new IllegalArgumentException("지원하지 않는 기능입니다.\n");
    }
}
