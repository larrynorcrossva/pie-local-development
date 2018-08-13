package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.model.ContextHeaders;
import gov.va.vamf.authrules.v1.model.Patient;
import gov.va.vamf.authrules.v1.model.User;
import gov.va.vamf.authrules.v1.util.DateTimeUtil;
import gov.va.vamf.authrules.v1.util.RulesUtil;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Optional;

@Rule(name = "SSOE User - Initialize Extended User Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 5)
public class InitializeExtendedUserRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist()
                && StringUtils.isBlank(user.getContextHeaders().getHeaderString("va_eauth_surrogate_edi"));
    }

    @Action
    public void then(@Fact("user") User user) {
        ContextHeaders contextHeaders = user.getContextHeaders();
        Patient patient = user.getPatient();

        patient.setGender(contextHeaders.getHeaderString("va_eauth_gender"));
        Optional<String> dateOfBirth = DateTimeUtil.convertMviDobToIso8601String(
                contextHeaders.getHeaderString("va_eauth_birthdate_v1"));
        if (dateOfBirth.isPresent()) {
            patient.setDob(dateOfBirth.get());
        }
        patient.setFirstName(user.getFirstName());
        patient.setMiddleName(user.getMiddleName());
        patient.setLastName(user.getLastName());
        patient.setStatus(contextHeaders.getHeaderString("va_eauth_status"));
        RulesUtil.parseSsnFromHeaders(user);
    }
}
