import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.StatusCode;
import in.hikev.commons.guice.scanner.ModuleScanner;
import in.hikev.file.AppFile;
import in.hikev.file.model.File;
import in.hikev.like.AppLike;
import in.hikev.like.model.Like;
import in.hikev.setting.AppSetting;
import in.hikev.setting.model.Setting;
import java.util.List;

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
        ActionResult<User> result2 = auth.signUp(new User());
    }

    static void settingTest() {
        AppSetting settings = injector.getInstance(AppSetting.class);

        //settings.addSetting(1, "enable_cache", "true");

        //settings.delete(1,"enable_cache");

        //settings.updateSetting(1,"enable_cache","false");

        //Setting result = settings.getSetting(1,"enable_cache");

        List<Setting> result = settings.getAllSettingsByType(1);
    }

    static void fileTest(){
        AppFile appFile = injector.getInstance(AppFile.class);

        File file = new File();
        file.setObjectModel("Profile");
        file.setObjectId(1);
        file.setFilePath("f:/avatar.png");

        //ActionResult<File> result = appFile.addFile(file);

        //File file =  appFile.getFile("35323278-4476-4294-a1ac-ff04d72598d2");
        //List<File> fiels = appFile.getFiles("Profile",1);
        //appFile.updateFileTitle(1,"abc");

        //ActionResult<File> result = appFile.deleteFile(4);
    }

    static void likeTest(){
        AppLike like = injector.getInstance(AppLike.class);

        ActionResult<Like> result = like.like(1, "User", 2);
        ActionResult<Like> result2 =like.unlike(1,"User",2);
    }

    static void StatusCodeTest(){
        String info = StatusCode.getStatusInfo(-2);
    }

    public static void main(String[] args){
        initialize();
        likeTest();
    }
}
