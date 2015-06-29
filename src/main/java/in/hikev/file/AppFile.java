package in.hikev.file;

import in.hikev.commons.core.ActionResult;
import in.hikev.file.model.File;

/**
 * Created by Administrator on 2015/6/28.
 */
public interface AppFile {
    ActionResult<File> addFile(File file);

    ActionResult<File> addFile(String model,int modelId,String filePath);

    ActionResult<File> addFile(String model,int modelId,String filePath,String fileName);

    ActionResult<File> addFile(String model,int modelId,String filePath,String fileName,String title);

    ActionResult<File> addFile(String model,int modelId,String filePath,String fileName,String title,String mimeType);

    ActionResult<File> addFile(String model, int modelId, String filePath, String fileName, String title, String mimeType, String size);
}
