package in.hikev.file;

import in.hikev.commons.core.ActionResult;
import in.hikev.file.model.File;

/**
 * Created by Administrator on 2015/6/28.
 */
public interface AppFile {
    ActionResult addFile(File file);

    ActionResult addFile(String model,int modelId,String filePath);
}
