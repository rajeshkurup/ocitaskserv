package org.oci.task.service;

import org.oci.task.data.model.OciTask;
import org.oci.task.data.OciTaskServResponse;
import org.oci.task.error.OciError;
import org.springframework.stereotype.Component;

/**
 * @brief Interface for OCI Task Service APIs.
 * @author rajeshkurup@live.com
 */
@Component
public interface OciTaskService {

    /**
     * @brief Create new Task in OCI Task System.
     * @param task Details of Task {@link OciTask} to be created.
     * @return Identifier of new Task if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse createTask(OciTask task);

    /**
     * @brief Update an existing Task in OCI Task System.
     * @param task Details of Task {@link OciTask} to be updated. Valid identifier of the Task must be present.
     * @return Nothing if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse updateTask(OciTask task);

    /**
     * @brief Delete a Task from OCI Task System.
     * @param taskId Valid identifier of Task to be deleted.
     * @return Nothing if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse delete(long taskId);

    /**
     * @brief Get a Task from OCI Task System.
     * @param taskId Valid identifier of Task to be loaded.
     * @return Details of Task found in the OCI Task System if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse getTask(long taskId);

    /**
     * @brief Get all Tasks persisted in OCI Task System.
     * @return Details of Tasks persisted in OCI Task System if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse getTasks();

}
