package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Authorized Resources - EDIPI Rule v1", description = "if the user is a veteran, add the veteran's EDIPI resource if available", priority = 10)
public class EdipiRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.containsRole("Veteran") && user.getPatient().getEdipid() != null;
    }

    @Action
    public void then(@Fact("user") User user) {
        user.getAuthorizedAllResources().add(String.format("^.*(/)?patient[s]?/EDIPI/%s/.*$", user.getPatient().getEdipid()));

    }
}
