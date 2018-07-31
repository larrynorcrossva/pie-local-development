package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import static java.util.Collections.singletonList;

@Rule(name = "Authorized Resources - Staff Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 10)
public class StaffRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.containsRole("Staff");
    }

    @Action
    public void then(@Fact("user") User user) {
        user.getAuthorizedAllResources().add(String.format("^.*(/)?staff/%s/.*$", user.getId()));
        user.getAuthorizedAllResources().add("^.*(/)?patient[s]?((/.*$)|(?.*$)|($))");
        user.setAuthorizedRoles(singletonList("Staff"));
    }
}
