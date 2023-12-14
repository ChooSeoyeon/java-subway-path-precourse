package subway;

import java.util.Scanner;
import subway.controller.SubwayPathController;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        new SubwayPathController(new InputView(scanner), new OutputView()).run();
    }
}
