package in.hikev.like.repository;

import com.google.inject.Inject;
import in.hikev.auth.Authorization;
import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.AppRepository;
import in.hikev.commons.core.StatusCode;
import in.hikev.like.AppLike;
import in.hikev.like.model.Like;
import org.apache.log4j.Logger;

import javax.validation.Validator;
import java.util.Date;

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

    public ActionResult<Like> like(int userId, String model, int objectId) {
        ActionResult<Like> result = new ActionResult<Like>();
        if (auth.userExist(userId) && !likedAlready(userId, model, objectId)) {
            Like like = new Like();
            like.setTargetUserId(userId);
            like.setObjectModel(model);
            like.setObjectId(objectId);
            like.setLastUpdateTime(new Date());

            result = extractValidationErrors(validator.validate(like));
            if (result.getStatusCode() == StatusCode.VALIDATION_ERROR) {
                return result;
            }

            like = save(like);
            result.setData(like);
            result.setStatusCode(StatusCode.OK);
            return result;
        }
        result.setStatusCode(StatusCode.LIKE_FAILED);
        return result;
    }

    public ActionResult<Like> unlike(int userId, String model, int objectId) {
        ActionResult<Like> result = new ActionResult<Like>();
        if (auth.userExist(userId) && likedAlready(userId, model, objectId)) {
            String hql = "from Like l where l.targetUserId = ? and l.objectModel = ? and l.objectId = ?";
            Like like = querySingle(hql, userId, model, objectId);
            if (like != null) {
                delete(like);
                result.setData(like);
                result.setStatusCode(StatusCode.OK);
                return result;
            }
        }
        result.setStatusCode(StatusCode.UNLIKE_FAILED);
        return result;
    }

    private boolean likedAlready(int userId, String model, int objectId) {
        String hql = "from Like l where l.targetUserId = ? and l.objectModel = ? and l.objectId = ?";
        return count(hql, userId, model, objectId) > 0;
    }
}
