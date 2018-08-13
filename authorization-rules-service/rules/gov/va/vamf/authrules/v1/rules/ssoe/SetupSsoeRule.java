package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.model.Patient;
import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "SSOE User - Setup SSOE Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 1)
public class SetupSsoeRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist();
    }

    @Action
    public void then(@Fact("user") User user) {
        user.setPatient(new Patient());
        user.getPatient().setIcn(user.getContextHeaders().getHeaderString("va_eauth_icn"));
        user.getPatient().setEdipid(user.getContextHeaders().getHeaderString("va_eauth_dodedipnid"));
        user.setLevelOfAssurance(2);
        user.setAuthenticated(true);
    }
}
