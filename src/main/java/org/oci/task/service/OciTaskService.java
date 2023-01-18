package org.oci.task.service;

import org.oci.task.data.api.OciTaskServRequest;
import org.oci.task.data.model.OciTask;
import org.oci.task.data.api.OciTaskServResponse;
import org.oci.task.error.OciError;
import org.springframework.stereotype.Component;

/**
 * @brief Interface for OCI Task Service APIs.
 * @author rajeshkurup@live.com
 */
@Component
public interface OciTaskService {

    /**
     * @brief Create new Task or update existing Task in OCI Task System.
     * @param taskId Identifier of Task record in OCI Task System. Must be valid to update existing Task. Pass zero to create new Task.
     * @param ociTaskReq Details of Task {@link OciTask} to be created or updated.
     * @return Identifier of the Task if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse saveTask(long taskId, OciTaskServRequest ociTaskReq);

    /**
     * @brief Delete a Task from OCI Task System.
     * @param taskId Valid identifier of Task to be deleted.
     * @return Nothing if succeeded, instance of {@link OciError} otherwise.
     */
    OciTaskServResponse deleteTask(long taskId);

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
