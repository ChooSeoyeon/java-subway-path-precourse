package subway.view;

import java.util.Scanner;
import subway.domain.StationRepository;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFunction() {
        System.out.println("\n## 원하는 기능을 선택하세요.");
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

    public String readPathFunction() {
        System.out.println("\n## 원하는 기능을 선택하세요.");
        String inputFunction = scanner.nextLine();
        validatePathFunction(inputFunction);
        return inputFunction;
    }

    private void validatePathFunction(String inputFunction) {
        if (inputFunction.equals("1")) {
            return;
        }
        if (inputFunction.equals("2")) {
            return;
        }
        if (inputFunction.equals("B")) {
            return;
        }
        throw new IllegalArgumentException("지원하지 않는 기능입니다.\n");
    }

    public String readStartStation() {
        System.out.println("\n## 출발역을 입력하세요.");
        String inputStation = scanner.nextLine();
        validateStation(inputStation);
        return inputStation;
    }

    public String readEndStation() {
        System.out.println("\n## 도착역을 입력하세요.");
        String inputStation = scanner.nextLine();
        validateStation(inputStation);
        return inputStation;
    }

    private void validateStation(String inputStation) { // TODO
        if (!StationRepository.existsStationByName(inputStation)) {
            throw new IllegalArgumentException("존재하지 않는 역입니다.\n");
        }
    }
}
