package in.hikev.like;

import in.hikev.commons.core.ActionResult;
import in.hikev.like.model.Like;

/**
 * Created by Administrator on 2015/6/30.
 */
public interface AppLike {
    ActionResult<Like> like(int userId,String model,int objectId);

    ActionResult<Like> unlike(int userId,String model,int objectId);

    boolean hasLiked(int userId,String model,int objectId);
}
