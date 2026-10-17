package com.visualai.visual;

public class VisualTestResult {

    private final int matchedSteps;
    private final int mismatchedSteps;
    private final int missingSteps;
    private final int newSteps;
    private final String dashboardUrl;
    private final String errorMessage;
    private final ResultStatus status;

    // =============================

    public enum ResultStatus {
        PASSED,
        FAILED,
        NEW_BASELINE,
        NOT_RUN,
        ERROR
    }

    // =============================

    public VisualTestResult(int matchedSteps,
                            int mismatchedSteps,
                            int missingSteps,
                            int newSteps,
                            String dashboardUrl) {

        this.matchedSteps = matchedSteps;
        this.mismatchedSteps = mismatchedSteps;
        this.missingSteps = missingSteps;
        this.newSteps = newSteps;
        this.dashboardUrl = dashboardUrl;
        this.errorMessage = null;

        if (newSteps > 0 && mismatchedSteps == 0) {
            this.status = ResultStatus.NEW_BASELINE;
        } else if (mismatchedSteps > 0 || missingSteps > 0) {
            this.status = ResultStatus.FAILED;
        } else {
            this.status = ResultStatus.PASSED;
        }
    }

    // =============================

    public static VisualTestResult notRun() {
        return new VisualTestResult(0, 0, 0, 0, "N/A") {
            @Override public ResultStatus getStatus() { return ResultStatus.NOT_RUN; }
            @Override public boolean isPassed() { return false; }
        };
    }

    public static VisualTestResult withError(String errorMessage) {
        return new VisualTestResult(0, 0, 0, 0, "N/A") {
            @Override public ResultStatus getStatus() { return ResultStatus.ERROR; }
            @Override public boolean isPassed() { return false; }
            @Override public String getErrorMessage() { return errorMessage; }
        };
    }

    // =============================

    public boolean isPassed() {
        return status == ResultStatus.PASSED ||
                status == ResultStatus.NEW_BASELINE;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public int getMatchedSteps() {
        return matchedSteps;
    }

    public int getMismatchedSteps() {
        return mismatchedSteps;
    }

    public int getMissingSteps() {
        return missingSteps;
    }

    public int getNewSteps() {
        return newSteps;
    }

    public String getDashboardUrl() {
        return dashboardUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "VisualTestResult{" +
                "status=" + status +
                ", matched=" + matchedSteps +
                ", mismatched=" + mismatchedSteps +
                ", missing=" + missingSteps +
                ", new=" + newSteps +
                ", url='" + dashboardUrl + '\'' +
                '}';
    }
}