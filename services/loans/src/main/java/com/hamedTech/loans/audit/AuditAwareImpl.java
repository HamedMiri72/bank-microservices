package com.hamedTech.loans.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor, which is the user who is currently logged in.
     *
     * @return The current auditor
     *  For now, we assume that the current auditor is the "Loans_MS" service.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Loans_MS");
    }
}
