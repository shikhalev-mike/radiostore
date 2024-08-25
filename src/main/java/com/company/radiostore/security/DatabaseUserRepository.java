package com.company.radiostore.security;

import com.company.radiostore.entity.User;
import io.jmix.securitydata.user.AbstractDatabaseUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Primary
@Component("UserRepository")
public class DatabaseUserRepository extends AbstractDatabaseUserRepository<User> {

    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    @Override
    protected void initSystemUser(final User systemUser) {
        final Collection<GrantedAuthority> authorities = getGrantedAuthoritiesBuilder()
                .addResourceRole(FullAccessRole.CODE)
                .build();
        systemUser.setAuthorities(authorities);
    }

    @Override
    protected void initAnonymousUser(final User anonymousUser) {
        final Collection<GrantedAuthority> authorities = getGrantedAuthoritiesBuilder()
                .addResourceRole(UiMinimalRole.CODE)
                .build();
        anonymousUser.setAuthorities(authorities);
    }
}