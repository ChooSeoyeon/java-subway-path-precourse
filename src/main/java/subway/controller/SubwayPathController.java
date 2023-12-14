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
            outputView.printMainMenu();
            String function = repeatUntilSuccessWithReturn(inputView::readFunction);
            if (function.equals("Q")) {
                return;
            }
            if (function.equals("1")) {
                outputView.printPathFunction();
                String subFunction = repeatUntilSuccessWithReturn(inputView::readPathFunction);
                if (subFunction.equals("1")) {
                    // TODO : 출발지, 도착지
                    ShortestPath shortestPath = pathFinder.findShortestPathBy(subFunction, "강남역", "양재시민의숲역");
                    System.out.println("shortestPath.getPath() = " + shortestPath.getPath());
                    continue;
                }
                if (subFunction.equals("2")) {

                    continue;
                }
                if (subFunction.equals("B")) {
                    continue;
                }
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
