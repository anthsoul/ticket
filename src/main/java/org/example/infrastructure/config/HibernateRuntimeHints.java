package org.example.infrastructure.config;

import org.example.infrastructure.utils.SnowflakeIdGenerator;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.List;

@Configuration
@ImportRuntimeHints(HibernateRuntimeHints.HibernateRegistrar.class)
public class HibernateRuntimeHints {
    static class HibernateRegistrar implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection()
                    .registerTypes(
                            List.of(
                                    TypeReference.of(SnowflakeIdGenerator.class)
                            ),
                            builder -> builder.withMembers(MemberCategory.values()));
        }
    }
}