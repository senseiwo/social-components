package in.hikev.like.repository;

import com.google.inject.Inject;
import in.hikev.auth.Authorization;
import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.AppRepository;
import in.hikev.like.AppLike;
import org.apache.log4j.Logger;

import javax.validation.Validator;

/**
 * Created by Administrator on 2015/6/30.
 */
public class AppLikeRepository extends AppRepository implements AppLike {
    @Log4jLogger
    Logger logger;

    @HibernateValidator
    Validator validator;

    @Inject
    Authorization auth;
}
