import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hikev.auth.Authorization;
import in.hikev.auth.AuthorizationModule;
import in.hikev.auth.model.User;

/**
 * Created by Administrator on 2015/6/22.
 */
public class main {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AuthorizationModule());

        Authorization auth = injector.getInstance(Authorization.class);
        User user = auth.getUser(1);

        User user2 = auth.getUser("name","kevin");
    }
}
