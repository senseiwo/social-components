package in.hikev;

import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hikev.auth.Authorization;
import in.hikev.auth.model.User;
import in.hikev.commons.core.StatusCode;
import in.hikev.commons.guice.scanner.ModuleScanner;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by tomek on 7/3/15.
 */
public class AuthUserTest {
    private Injector injector;

    @Before
    public void setUp() throws Exception {
        ModuleScanner scanner = new ModuleScanner();
        injector = Guice.createInjector(scanner.scanForModules());
    }

    @Test
    public void nullUserTest() {
        Authorization auth = injector.getInstance(Authorization.class);
        User user = auth.getUser(1);
        assertNull(user);
    }

    @Test
    public void testSignInUser() throws Exception {
        User user = new User("tomek", "qwerty1", "tomek@asshole.com");
        Authorization auth = injector.getInstance(Authorization.class);
        auth.signUp(user);
        User signedUser = auth.getUser(1);
        assertNotNull(signedUser);
        assertEquals(user.getName(), signedUser.getName());
        assertEquals(user.getEmail(), signedUser.getEmail());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        User user = new User("tomek", "qwerty1", "tomek@asshole.com");
        Authorization auth = injector.getInstance(Authorization.class);
        auth.signUp(user);
        assertEquals(StatusCode.OK, auth.login(user.getName(), user.getPassword()).getStatusCode());
    }

    @Test
    public void testLoginFailed() throws Exception {
        User user = new User("tomek", "qwerty1", "tomek@asshole.com");
        Authorization auth = injector.getInstance(Authorization.class);
        auth.signUp(user);
        assertEquals(StatusCode.LOGIN_FAILURE, auth.login(user.getName(), "").getStatusCode());
    }

    @Test
    public void testCheckUser() throws Exception {
        User user = new User("tomek", "qwerty1", "tomek@asshole.com");
        Authorization auth = injector.getInstance(Authorization.class);
        auth.signUp(user);
        assertTrue(auth.userExist(1));
        assertTrue(auth.userExist("tomek"));
    }
}
