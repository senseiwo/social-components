package in.hikev.file.repository;

import in.hikev.commons.annotation.HibernateValidator;
import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.core.ActionResult;
import in.hikev.commons.core.AppRepository;
import in.hikev.commons.core.StatusCode;
import in.hikev.file.AppFile;
import in.hikev.file.model.File;
import org.apache.log4j.Logger;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/6/28.
 */
public class AppFileRepository extends AppRepository implements AppFile {
    @Log4jLogger
    Logger logger;

    @HibernateValidator
    Validator validator;

    public File getFile(int id){
        return get(File.class,id);
    }

    public File getFile(String guid){
        return get(File.class,"guid",guid);
    }

    public List<File> getFiles(String model,int id) {
        return query("from File f where f.objectModel=? and f.objectId=?", model, id);
    }

    public void updateFileTitle(int id,String title){
        File file = getFile(id);
        if(file!=null){
            file.setTitle(title);
            update(file);
        }
    }

    public List<File> getFiles(String model,int id,int startIndex,int maxSize) {
        return query(startIndex, maxSize, "from File f where f.objectModel=? and f.objectId=?", model, id);
    }

    public ActionResult<File> addFile(String model,int modelId,String filePath) {
        return addFile(model,modelId,filePath,null,null,null,null);
    }

    public ActionResult<File> addFile(String model,int modelId,String filePath,String fileName){
        return addFile(model,modelId,filePath,fileName,null,null,null);
    }

    public ActionResult<File> addFile(String model,int modelId,String filePath,String fileName,String title){
        return addFile(model,modelId,filePath,fileName,title,null,null);
    }

    public ActionResult<File> addFile(String model,int modelId,String filePath,String fileName,String title,String mimeType){
        return addFile(model,modelId,filePath,fileName,title,mimeType,null);
    }

    public ActionResult<File> addFile(File file) {
        return addFile(file.getObjectModel(), file.getObjectId(), file.getFilePath(), file.getFileName(), file.getTitle(), file.getMimeType(), file.getSize());
    }

    public ActionResult<File> addFile(String model, int modelId, String filePath, String fileName, String title, String mimeType, String size){
        File file = new File();

        file.setObjectId(modelId);
        file.setObjectModel(model);
        file.setFilePath(filePath);
        file.setFileName(fileName);
        file.setTitle(title);
        file.setMimeType(mimeType);
        file.setSize(size);
        file.setGuid(UUID.randomUUID().toString());
        file.setLastUpdateTime(new Date());

        ActionResult<File> result = extractValidationErrors(validator.validate(file));
        if(result.getStatusCode() == StatusCode.VALIDATION_ERROR){
            return result;
        }
        result.setStatusCode(StatusCode.OK);
        result.setData(save(file));
        return result;
    }
}
