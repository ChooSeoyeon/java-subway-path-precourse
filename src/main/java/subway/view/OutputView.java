package subway.view;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR]" + message);
    }

    private void printInfoMessage(String message) {
        System.out.println("[INFO]" + message);
    }

    public void printMainMenu() {
        System.out.println("\n## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
    }

    public void printPathFunction() {
        System.out.println("\n## 경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
    }
}
