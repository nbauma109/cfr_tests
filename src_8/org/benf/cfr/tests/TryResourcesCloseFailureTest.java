package org.benf.cfr.tests;

public class TryResourcesCloseFailureTest {
    private static class TrackedResource implements AutoCloseable {
        private final String name;
        private final boolean failOnClose;
        private final StringBuilder events;

        private TrackedResource(String name, boolean failOnClose, StringBuilder events) {
            this.name = name;
            this.failOnClose = failOnClose;
            this.events = events;
        }

        @Override
        public void close() {
            events.append(name).append(";");
            if (failOnClose) {
                throw new IllegalStateException(name);
            }
        }
    }

    public String examineClosingException() {
        StringBuilder events = new StringBuilder();
        String outcome = evaluate(events);
        return outcome + "|" + events.toString();
    }

    private String evaluate(StringBuilder events) {
        try (TrackedResource first = new TrackedResource("first", false, events);
             TrackedResource second = new TrackedResource("second", true, events)) {
            events.append("body;");
            return "normal";
        } catch (IllegalStateException exception) {
            events.append("caught;");
            return exception.getMessage() + ":" + exception.getSuppressed().length;
        } finally {
            events.append("finally;");
        }
    }
}
