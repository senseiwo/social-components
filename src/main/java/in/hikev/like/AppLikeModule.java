package in.hikev.like;

import com.google.inject.AbstractModule;
import in.hikev.commons.annotation.AutoloadModule;
import in.hikev.like.repository.AppLikeRepository;

/**
 * Created by Administrator on 2015/6/30.
 */
@AutoloadModule
public class AppLikeModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AppLike.class).to(AppLikeRepository.class);
    }
}
