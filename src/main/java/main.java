import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.StatusCode;
import in.hikev.commons.guice.scanner.ModuleScanner;

/**
 * Created by Administrator on 2015/6/22.
 */
public class main {

    private static Injector injector;

    static void initialize(){
        ModuleScanner scanner = new ModuleScanner();
        injector = Guice.createInjector(scanner.scanForModules());
    }

    static void authTest(){
        Authorization auth = injector.getInstance(Authorization.class);
        User user = auth.getUser(1);

        User user2 = auth.getUser("name","kevin");
        ActionResult result = auth.login("kevin", "1231");
        int count = auth.getTotalUserCount();
    }

    static void StatusCodeTest(){
        String info = StatusCode.getStatusInfo(-2);
    }

    public static void main(String[] args){
        initialize();
        authTest();
    }
}
