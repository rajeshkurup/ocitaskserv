package org.oci.task.service;

import org.oci.task.data.dao.OciTaskDao;
import org.oci.task.data.model.OciTask;
import org.oci.task.data.OciTaskServResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Implementation of OCI Task Service APIs.
 * @author rajeshkurup@live.com
 */
@Service
public class OciTaskServiceImpl implements OciTaskService {

    @Autowired
    OciTaskDao taskDao;

    @Override
    public OciTaskServResponse createTask(OciTask task) {
        return null;
    }

    @Override
    public OciTaskServResponse updateTask(OciTask task) {
        return null;
    }

    @Override
    public OciTaskServResponse delete(long taskId) {
        return null;
    }

    @Override
    public OciTaskServResponse getTask(long taskId) {
        return null;
    }

    @Override
    public OciTaskServResponse getTasks() {
        List<OciTask> tasks = (List<OciTask>)taskDao.findAll();
        OciTask task = new OciTask();
        task.setId(1234L);
        task.setTitle("Hello World! I'm a Task from OCI Task System!! This is a test version!!!");
        tasks.add(task);
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTasks(tasks);
        return resp;
    }

}
