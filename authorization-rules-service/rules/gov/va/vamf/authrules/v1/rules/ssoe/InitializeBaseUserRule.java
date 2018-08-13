package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.model.ContextHeaders;
import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import static gov.va.vamf.authrules.v1.util.RulesUtil.getHeaderOrDefault;

@Rule(name = "SSOE User - Initialize Base User Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 4)
public class InitializeBaseUserRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist();
    }

    @Action
    public void then(@Fact("user") User user) {
        ContextHeaders contextHeaders = user.getContextHeaders();

        user.setLastName(getHeaderOrDefault(contextHeaders.getHeaderString("va_eauth_lastname"), "USER"));
        user.setFirstName(getHeaderOrDefault(contextHeaders.getHeaderString("va_eauth_firstname"), "ANONYMOUS"));
        user.setMiddleName(getHeaderOrDefault(contextHeaders.getHeaderString("va_eauth_middlename"), ""));
        user.setAuthenticationAuthority("gov.va.iam.ssoe.v1");
        user.getAdditionalAttributes().put("va_eauth_csid", contextHeaders.getHeaderString("va_eauth_csid"));
        user.getAdditionalAttributes().put("va_eauth_secid", contextHeaders.getHeaderString("va_eauth_secid"));
        user.setAuthorizedRoles(java.util.Collections.singletonList("Veteran"));
    }
}
