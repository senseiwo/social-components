package in.hikev.commons.io;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by Administrator on 2015/6/30.
 */
public class FileIOUtils {
    public static boolean deleteFile(String filePath) {
        boolean result = false;
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            result = FileUtils.deleteQuietly(new File(filePath));
        }
        return result;
    }
}
