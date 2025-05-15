package com.hamedTech.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("auditAwareImpl")
public class AditAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}
