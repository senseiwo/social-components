package in.hikev.auth;

import com.google.inject.AbstractModule;
import in.hikev.auth.repository.AuthorizationRepository;
import in.hikev.commons.annotation.AutoloadModule;

/**
 * Created by Administrator on 2015/6/22.
 */
@AutoloadModule
public class AuthorizationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Authorization.class).to(AuthorizationRepository.class);
    }
}
