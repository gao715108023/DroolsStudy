package test;

import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

/**
 * Created by gaochuanjun on 14-8-24.
 */
public class TestAgendaFilter implements AgendaFilter {

    private String startName;

    public TestAgendaFilter(String startName) {
        this.startName = startName;
    }

    @Override
    public boolean accept(Activation activation) {
        String ruleName = activation.getRule().getName();
        return ruleName.startsWith(this.startName);
    }
}
