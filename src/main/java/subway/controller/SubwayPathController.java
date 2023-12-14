package subway.controller;

import java.util.function.Supplier;
import subway.domain.LineRepository;
import subway.domain.PathRepository;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayPathController {
    private final InputView inputView;
    private final OutputView outputView;

    public SubwayPathController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        prepare();
        play();
    }

    private void prepare() {
        LineRepository.addInitLine();
        StationRepository.addInitStation();
        PathRepository.addInitPath();
    }

    private void play() {
        while (true) {
            outputView.printMainMenu();
            String function = repeatUntilSuccessWithReturn(inputView::readFunction);
            if (function.equals("Q")) {
                return;
            }
        }
    }

    private <T> T repeatUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void repeatUntilSuccess(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
