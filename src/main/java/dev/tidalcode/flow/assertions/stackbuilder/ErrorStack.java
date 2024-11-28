package dev.tidalcode.flow.assertions.stackbuilder;

import dev.tidalcode.flow.data.ExceptionData;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ErrorStack {
    private String errorDetail;
    private StackTraceElement[] stackTrace;
    private boolean softAssertion;
    private boolean passed;
    private static final String PASS_KEY = "_pass";
    private static final ThreadLocal<Builder> builder = ThreadLocal.withInitial(Builder::new);

    public static Builder builder(String errorDetail, String description){
        return builder.get().withErrorDetails(errorDetail + "\nDescription: " + description);
    }

    public void execute() {
        try {
            StringBuilder errorMessage = getErrorMessage(ExceptionData.getExceptionDataMap());
            if (errorMessage.length() > 0) {
                throw new AssertionError(System.lineSeparator() + errorMessage);
            }
        } finally {
            builder.remove();
            ExceptionData.removeExceptionData();
        }
    }

    private void runSoftAssertion() {
        if (softAssertion) {
            String randomId = UUID.randomUUID().toString();
            if (!passed){
                ExceptionData.addExceptionData(randomId + "_d", errorDetail);
                ExceptionData.addExceptionData(randomId, stackTrace);
            }
        }
    }

    private void runHardAssertion() {
        if (!softAssertion) {
            String randomId = UUID.randomUUID().toString();
            if (!passed) {
                StringBuilder errorMessage = new StringBuilder();

                errorMessage.append(System.lineSeparator());
                errorMessage.append("Verification Failed: ");
                errorMessage.append(System.lineSeparator());
                errorMessage.append("Reason: ").append(errorDetail);

                errorMessage.append(System.lineSeparator());

                AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());
                AtomicInteger stackBuilderNumber = getStackBuilderNumber(stackTrace);

                Arrays.stream(stackTrace).forEach(e -> {
                    if (i.get().get() > stackBuilderNumber.get()) {
                        return;
                    }

                    if (i.get().get() <= stackBuilderNumber.get()) {
                        if(!e.toString().contains("getStackTrace")) {
                            errorMessage.append("at ");
                            errorMessage.append(e);
                            errorMessage.append(System.lineSeparator());
                        }
                    }
                    i.get().getAndIncrement();
                });
                throw new AssertionError(errorMessage.toString());
            }
        }
    }

    private StringBuilder getErrorMessage(Map<String, Object> errorData) {
        StringBuilder errorMessage = new StringBuilder();
        AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());

        errorData.forEach((k, v) -> {
            if (v instanceof StackTraceElement[]) {
                AtomicInteger stackBuilderNumber = getStackBuilderNumber((StackTraceElement[]) v);

                errorMessage.append(System.lineSeparator());
                errorMessage.append("Verification Failed: ");
                errorMessage.append(ExceptionData.getExceptionData(k + "_d"));
                errorMessage.append(System.lineSeparator());

                Arrays.stream((StackTraceElement[]) v).forEach(e -> {
                    if (i.get().get() > stackBuilderNumber.get()) {
                        return;
                    }

                    if (i.get().get() <= stackBuilderNumber.get()) {
                        if(!e.toString().contains("getStackTrace")) {
                            errorMessage.append("at ");
                            errorMessage.append(e);
                            errorMessage.append(System.lineSeparator());
                        }
                    }
                    i.get().getAndIncrement();
                });
                i.set(new AtomicInteger());
            }
        });
        return errorMessage;
    }

    private AtomicInteger getStackBuilderNumber(StackTraceElement[] stackTrace) {
        AtomicInteger stackBuilderNumber = new AtomicInteger(0);
        for (StackTraceElement e : stackTrace) {
            if (e.toString().contains("NativeMethodAccessorImpl.invoke0")) {
                stackBuilderNumber.decrementAndGet();
                break;
            }
            stackBuilderNumber.getAndIncrement();
        }
        return stackBuilderNumber;
    }

    private static StringBuilder getPassedMessages(Map<String, Object> verificationData) {
        StringBuilder passMessage = new StringBuilder();
        verificationData.forEach((k, v) -> {
            if (k.contains(PASS_KEY)) {
                passMessage.append(System.lineSeparator());
                passMessage.append("Verification Passed: ");
                passMessage.append(ExceptionData.getExceptionData(k));
            }
        });
        return passMessage;
    }

    public static class Builder {
        private String errorDetail;
        private StackTraceElement[] stackTrace;
        private boolean softAssertion;
        private boolean passed;

        public Builder() {
        }

        public Builder(String errorDetail, String description) {
            this.errorDetail = errorDetail + "\nDescription: " + description;
        }

        public Builder withErrorDetails(String errorDetail){
            this.errorDetail = errorDetail;
            return this;
        }

        public Builder withStackTrace(StackTraceElement[] stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public Builder withAssertionStatus(boolean softAssertion, boolean passed) {
            this.softAssertion = softAssertion;
            this.passed = passed;
            return this;
        }

        public void build() {
            ErrorStack errorStack = new ErrorStack();
            errorStack.errorDetail = this.errorDetail;
            errorStack.stackTrace = this.stackTrace;
            errorStack.softAssertion = this.softAssertion;
            errorStack.passed = this.passed;
            errorStack.runSoftAssertion();
            errorStack.runHardAssertion();
        }
    }
}
