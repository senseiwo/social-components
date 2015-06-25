import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hikev.auth.Authorization;
import in.hikev.auth.AuthorizationModule;
import in.hikev.auth.model.User;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.StatusCode;

/**
 * Created by Administrator on 2015/6/22.
 */
public class main {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AuthorizationModule());

        Authorization auth = injector.getInstance(Authorization.class);
        User user = auth.getUser(1);

        User user2 = auth.getUser("name","kevin");

        ActionResult result = auth.login("kevin","1231");

        int count = auth.getTotalUserCount();

        User u = new User();
        u.setName("kevin2");
        u.setPassword("1231");
        u.setEmail("kkk@163.com");

        //ActionResult result2 = auth.signUp(u);

        String info = StatusCode.getStatusInfo(-2);
    }
}
