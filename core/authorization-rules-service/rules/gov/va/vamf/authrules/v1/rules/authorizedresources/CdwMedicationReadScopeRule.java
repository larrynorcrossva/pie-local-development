package gov.va.vamf.authrules.v1.rules.authorizedresources;

import gov.va.vamf.authrules.v1.domain.smartonfhir.ScopeMatcher;
import gov.va.vamf.authrules.v1.model.User;
import gov.va.vamf.security.v1.VamfJwtClaimsConstants;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Optional;

@Rule(name = "Authorized Resources - CDW Medication Scope Rule v1", description = "If the user represents a patient with an ICN "
        + "and the user has a scope which encompasses 'patient/Medication.read', add the authorized 'GET' Resource for the CDW patient "
        + "medication endpoint.", priority = 10)
public class CdwMedicationReadScopeRule {

    /**
     * If the user has a scope which matches with "patient/Medication.read"...
     *
     * @param user
     * @return
     */
    @Condition
    public boolean when(@Fact("user") User user) {
        return ScopeMatcher.userIsInScope(user, "patient/Medication.read");
    }

    /**
     * ...Set CDW V1-specific Medication.read resource(s) for that user
     *
     * @param user
     */
    @org.jeasy.rules.annotation.Action
    public void then(@Fact("user") User user) {
        user.getAuthorizedGetResources().add(String.format("^patients/%s/medications((/.*$)|(\\?.*$)|($))", getPatientId(user).get()));
    }

    Optional<String> getPatientId(User user) {
        if (user.getAuthorizedRoles().stream().anyMatch(
                role -> role.equalsIgnoreCase(VamfJwtClaimsConstants.VAMF_ROLE_VETERAN))) {
            return Optional.of(user.getPatient().getIcn());
        } else {
            return Optional.ofNullable(user.getAdditionalAttributes().get("fhirPatientId"));
        }
    }
}
