package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.model.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.util.CollectionUtils;

@Rule(name = "Authorized Resources - New Staff VistA Accounts Rule v1", description = "if the user has VistA accounts, add" +
        " the protected URIs to the resources", priority = 10)
public class VistaStaffRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.containsRole("Staff") && !CollectionUtils.isEmpty(user.getVistaPairedAccounts());
    }

    @Action
    public void then(@Fact("user") User user) {
        String authResourceFormat = ".*/site(s)?/(dfn-)?%s/.*";
        user.getVistaPairedAccounts().stream().forEach(vistaPairedAccount ->
                user.getAuthorizedAllResources().add(
                        String.format(authResourceFormat, vistaPairedAccount.getSiteId())));
    }
}
