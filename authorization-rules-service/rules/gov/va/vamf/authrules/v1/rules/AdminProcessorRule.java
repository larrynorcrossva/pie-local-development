package gov.va.vamf.authrules.v1.rules;

import gov.va.vamf.authrules.v1.model.User;
import gov.va.vamf.authrules.v1.util.RulesUtil;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Set;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Rule(name = "Admin Processor Rule v1", description = "Update the vamf user if the admin headers are populated", priority = 7)
public class AdminProcessorRule {

    @Condition
    public boolean when(@Fact("user") User user,
                        @Fact("adminTokenEnabled") boolean adminTokenEnabled,
                        @Fact("accessTokens") Set<String> accessTokens) {
        if (user.getContextHeaders() == null) {
            return false;
        }

        String adminHeader = user.getContextHeaders().getHeaderString("X-VAMF-ADMIN");
        return isNotBlank(adminHeader)
                && !user.getSecurityAndPrincipalExist()
                && adminTokenEnabled
                && accessTokens.contains(adminHeader);
    }

    @Action
    public void then(@Fact("user") User user) {
        user.setAuthenticated(true);
        user.setId(RulesUtil.getTokenId(user.getContextHeaders().getHeaderString("X-VAMF-ADMIN")));
        user.setIdType("AdminToken");
        user.setAuthorizedRoles(singletonList("Admin"));
    }
}