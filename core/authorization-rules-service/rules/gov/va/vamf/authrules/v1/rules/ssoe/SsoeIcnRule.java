package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.model.User;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "SSOE User - ICN Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 3)
public class SsoeIcnRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist()
                && StringUtils.isNotBlank(user.getPatient().getIcn());
    }

    @Action
    public void then(@Fact("user") User user) {
        user.setId(user.getPatient().getIcn());
        user.setIdType("ICN");
    }
}
