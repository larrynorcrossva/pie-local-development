package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Authorized Resources - ICN Rule v1", description = "if the user is a veteran, add the veteran's ICN resource if available", priority = 10)
public class IcnRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.containsRole("Veteran") && user.getPatient().getIcn() != null;
    }

    @Action
    public void then(@Fact("user") User user) {
        user.getAuthorizedAllResources().add(String.format("^.*(/)?patient[s]?/(ICN/)?%s/.*$", user.getPatient().getIcn()));
    }
}
