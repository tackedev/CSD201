package backtracking.core;

/**
 * class for default evaluator (accept all proposal)
 * @author tackedev
 * @since Jan 25, 2021 4:20:18 PM
 */
public class DefaultEvaluator implements Evaluator {

    @Override
    public boolean isSatisfied(Proposal p) {
        return true;
    }
}
