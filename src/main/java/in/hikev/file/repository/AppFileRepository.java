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
import java.util.UUID;

/**
 * Created by Administrator on 2015/6/28.
 */
public class AppFileRepository extends AppRepository implements AppFile {
    @Log4jLogger
    Logger logger;

    @HibernateValidator
    Validator validator;

    public ActionResult addFile(String model,int modelId,String filePath) {
        File file = new File();
        file.setObjectId(modelId);
        file.setObjectModel(model);
        file.setFilePath(filePath);
        return addFile(file);
    }

    public ActionResult addFile(File file){
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
