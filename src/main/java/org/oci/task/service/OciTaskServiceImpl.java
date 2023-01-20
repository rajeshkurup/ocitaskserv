package org.oci.task.service;

import org.oci.task.data.api.OciTaskServRequest;
import org.oci.task.data.api.OciTaskServResponse;
import org.oci.task.data.dao.OciTaskDao;
import org.oci.task.data.model.OciTask;
import org.oci.task.error.OciError;
import org.oci.task.error.OciErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @brief Implementation of OCI Task Service APIs.
 * @author rajeshkurup@live.com
 */
@Service
public class OciTaskServiceImpl implements OciTaskService {

    private static Logger logger = LoggerFactory.getLogger(OciTaskServiceImpl.class);

    @Autowired
    OciTaskDao taskDao;

    private OciTask createTaskEntity(OciTaskServRequest ociTaskReq) {
        OciTask task = new OciTask();
        task.setTitle(ociTaskReq.getTitle());
        task.setCompleted(ociTaskReq.isCompleted());
        task.setDescription(ociTaskReq.getDescription());
        task.setPriority(ociTaskReq.getPriority());
        task.setDueDate(ociTaskReq.getDueDate());
        task.setStartDate(ociTaskReq.getStartDate());
        return task;
    }

    @Override
    public OciTaskServResponse saveTask(long taskId, OciTaskServRequest ociTaskReq) {
        logger.info("Create or Update Task");
        OciTaskServResponse resp = new OciTaskServResponse();
        if(ociTaskReq != null) {
            OciTask task = createTaskEntity(ociTaskReq);
            if(taskId != 0) {
                task.setId(taskId);
                logger.info("Update Task - Id=" + taskId);
            }

            try {
                OciTask result = taskDao.save(task);
                resp.setTaskId(result.getId());
                logger.info("New Task has been created or updated - Id=" + result.getId());
            }
            catch(IllegalArgumentException ex) {
                logger.error("One or more Task details is incorrect - Exception=" + ex.toString());
                resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "One or more Task details is incorrect"));
            }
        }
        else {
            logger.error("Invalid Task");
            resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Task request"));
        }

        return resp;
    }

    @Override
    public OciTaskServResponse deleteTask(long taskId) {
        logger.info("Delete a Task - Id=" + taskId);
        OciTaskServResponse resp = new OciTaskServResponse();

        try {
            taskDao.deleteById(taskId);
            logger.info("Task has been deleted - Id=" + taskId);
        }
        catch(IllegalArgumentException | EmptyResultDataAccessException ex) {
            logger.error("Incorrect identifier - Exception=" + ex.toString());
            resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Incorrect identifier"));
        }

        return resp;
    }

    @Override
    public OciTaskServResponse getTask(long taskId) {
        logger.info("Get a Task - Id=" + taskId);
        OciTaskServResponse resp = new OciTaskServResponse();

        try {
            Optional<OciTask> task = taskDao.findById(taskId);
            if(!task.isEmpty()) {
                resp.setTask(task.get());
                logger.info("Task found - Id=" + task.get().getId() + " - Title=" + task.get().getTitle());
            }
            else {
                logger.info("Task NOT found - Id=" + taskId);
                resp.setError(new OciError(OciErrorCode.NO_DATA_FOUND, "Task NOT found - Id=" + taskId));
            }
        }
        catch(IllegalArgumentException ex) {
            logger.error("Incorrect identifier - Exception=" + ex.toString());
            resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Incorrect identifier"));
        }

        return resp;
    }

    @Override
    public OciTaskServResponse getTasks() {
        logger.info("Get all Tasks");
        List<OciTask> tasks = taskDao.findAll();
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTasks(tasks);
        return resp;
    }

}
