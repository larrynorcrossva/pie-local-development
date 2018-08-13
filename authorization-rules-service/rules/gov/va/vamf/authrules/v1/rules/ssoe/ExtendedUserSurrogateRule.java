package gov.va.vamf.authrules.v1.rules.ssoe;

import gov.va.vamf.authrules.v1.model.ContextHeaders;
import gov.va.vamf.authrules.v1.model.Patient;
import gov.va.vamf.authrules.v1.model.User;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "SOE User - Extended User Surrogate Rule v1", description = "if the user is a staff member, add the staff user id to the resources", priority = 5)
public class ExtendedUserSurrogateRule {

    @Condition
    public boolean when(@Fact("user") User user) {
        return user.getSupportsSsoe()
                && !user.getSecurityAndPrincipalExist()
                && StringUtils.isNotBlank(user.getContextHeaders().getHeaderString("va_eauth_surrogate_edi"));
    }

    @Action
    public void then(@Fact("user") User user) {
        ContextHeaders contextHeaders = user.getContextHeaders();
        Patient patient = user.getPatient();
        String ssn = null;

        user.setAuthorizedRoles(java.util.Arrays.asList("Veteran", "Surrogate"));
        patient.setEdipid(contextHeaders.getHeaderString("va_eauth_subject_edi"));
        patient.setGender(contextHeaders.getHeaderString("va_eauth_subject_gender"));
        patient.setDob(contextHeaders.getHeaderString("va_eauth_subject_birthdate"));
        patient.setFirstName(contextHeaders.getHeaderString("va_eauth_subject_firstname"));
        patient.setMiddleName(contextHeaders.getHeaderString("va_eauth_subject_middlename"));
        patient.setLastName(contextHeaders.getHeaderString("va_eauth_subject_lastname"));
        patient.setStatus(contextHeaders.getHeaderString("va_eauth_subject_status"));
        user.setAccessCode(contextHeaders.getHeaderString("va_eauth_csid"));
        if ("SSN".equalsIgnoreCase(contextHeaders.getHeaderString("va_eauth_subject_idtype"))) {
            ssn = contextHeaders.getHeaderString("va_eauth_subject_id");
        }
        patient.setSsn(ssn);
    }
}
