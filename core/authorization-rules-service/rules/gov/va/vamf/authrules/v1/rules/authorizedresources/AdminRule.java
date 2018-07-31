package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import static java.util.Collections.singletonList;

@Rule(name = "Authorized Resources - Admin Rule v1", description = "if the user is a admin member, add the admin regex to the authorized resources", priority = 10)
public class AdminRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.containsRole("Admin");
    }

    @Action
    public void then(@Fact("user") User user) {
        user.getAuthorizedAllResources().add(".*/system/.*$");
        user.setAuthorizedRoles(singletonList("Admin"));
    }
}
