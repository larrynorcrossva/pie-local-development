package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.domain.IamAuthorizationHeader;
import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "SSOE User - SSN Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 2)
public class SsnRule {

    @Condition
    public boolean when(@Fact("user") User user,
                        @Fact("authResponse") IamAuthorizationHeader.AuthorizationResponse authResponse) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist()
                && authResponse != null
                && authResponse.getIdType().equalsIgnoreCase("SSN");
    }

    @Action
    public void then(@Fact("user") User user,
                     @Fact("authResponse") IamAuthorizationHeader.AuthorizationResponse authResponse) {
        user.getPatient().setSsn(String.valueOf(authResponse.getId()));
    }
}
