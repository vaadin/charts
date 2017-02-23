package com.vaadin.addon.charts.testbenchtests;

import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A retry rule is used to re-run a test several times in case of a random failure.
 * The test passes as soon as one attempt is executed without any errors,
 * i.e. it is only run as many times as needed.
 * The maximum number of attempts is specified in the constructor.
 *
 * @since 5.0
 */
public class RetryRule implements TestRule {
    private int maxAttempts;

    /**
     *
     * Constructs the retry rule with a maximum number of attempts.
     * The maximum number of attempts specifies how many times the test
     * will be run in case of a random failure.
     *
     * @param maxAttempts
     *            a maximum number of attempts.
     */
    public RetryRule(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     * Gets the maximum number of times to run the test.
     *
     * @return the maximum number of times to run the test.
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }
    @Override
    public Statement apply(Statement base, Description description) {
        if(maxAttempts >1) {
            return statement(base, description);
        } else {
            return base;
        }

    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable caughtThrowable = null;
                for (int i = 0; i < maxAttempts; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (AssumptionViolatedException t) {
                        throw t;
                    } catch (Throwable t) {
                        caughtThrowable = t;
                    }
                }
                String errorMessage = String.format("%s: run failed %s times",
                        description.getDisplayName(),
                        maxAttempts);
                throw new RuntimeException(errorMessage, caughtThrowable);
            }
        };
    }
}