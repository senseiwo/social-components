package in.hikev.file.repository;

import in.hikev.commons.annotation.Log4jLogger;
import in.hikev.commons.hibernate.base.HibernateDaoSupport;
import in.hikev.file.AppFile;
import in.hikev.file.model.File;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/6/28.
 */
public class AppFileRepository extends HibernateDaoSupport implements AppFile {
    @Log4jLogger
    Logger logger;

    public File addFile(String model,int modelId,String filePath) {
        File file = new File();
        file.setObjectId(modelId);
        file.setObjectModel(model);
        file.setFilePath(filePath);
        return addFile(file);
    }

    public File addFile(File file){
        file.setGuid(UUID.randomUUID().toString());
        file.setLastUpdateTime(new Date());
        return save(file);
    }
}
