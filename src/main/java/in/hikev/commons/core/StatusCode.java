package in.hikev.commons.core;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/6/23.
 */
public class StatusCode {
    public static int OK = 1;
    public static int DATA_ISVALID = 2;
    public static int INTERNAL_ERROR = 0;

    public static int LOGIN_FAILURE = -1;

    public static int SIGNUP_FAILURE_USERNAME_EXIST = -2;
    public static int SIGNUP_FAILURE_EMAIL_EXIST = -3;

    public static int SETTING_KEY_EXIST = -4;

    public static int VALIDATION_ERROR = -5;

    public static int DELETE_FILE_FAILED=-6;
    public static int LIKE_FAILED = -7;
    public static int UNLIKE_FAILED = -8;
    public static int NOTIFY_USER_FAILED = -9;

    public static String getStatusInfo(int code) {
        String info = null;
        for (Field field : StatusCode.class.getFields()) {
            try {
                if (field.getInt(null) == code) {
                    info = field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return info;
    }
}
