package subway.controller;

import java.util.function.Supplier;
import subway.domain.LineRepository;
import subway.domain.PathFinder;
import subway.domain.PathRepository;
import subway.domain.StationRepository;
import subway.domain.dto.ShortestPath;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayPathController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PathFinder pathFinder;

    public SubwayPathController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pathFinder = new PathFinder();
    }

    public void run() {
        prepare();
        play();
    }

    private void prepare() {
        LineRepository.addInitLine();
        StationRepository.addInitStation();
        PathRepository.addInitPath();
        pathFinder.initPathFinder();
    }

    private void play() {
        while (true) {
            String function = repeatUntilSuccessWithReturn(this::readFunction);
            if (function.equals("Q")) {
                return;
            }
            if (function.equals("1")) {
                String subFunction = repeatUntilSuccessWithReturn(this::readPathFunction);
                if (subFunction.equals("B")) {
                    continue;
                }
                if (subFunction.equals("1") || subFunction.equals("2")) {
                    String startStation = repeatUntilSuccessWithReturn(inputView::readStartStation);
                    String endStation = repeatUntilSuccessWithReturn(inputView::readEndStation);
                    try {
                        ShortestPath shortestPath = pathFinder.findShortestPathBy(subFunction, startStation,
                                endStation);
                        outputView.printShortestPath(shortestPath);
                    } catch (IllegalArgumentException e) {
                        outputView.printErrorMessage(e.getMessage());
                    }
                }
            }
        }
    }

    private String readFunction() {
        outputView.printMainMenu();
        return inputView.readFunction();
    }

    private String readPathFunction() {
        outputView.printPathFunction();
        return inputView.readPathFunction();
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
