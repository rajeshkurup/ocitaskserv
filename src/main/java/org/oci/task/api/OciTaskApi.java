package org.oci.task.api;

import org.oci.task.data.api.OciTaskServRequest;
import org.oci.task.data.api.OciTaskServResponse;
import org.oci.task.data.model.OciTask;
import org.oci.task.error.OciError;
import org.oci.task.error.OciErrorCode;
import org.oci.task.service.OciTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @brief APIs hosted by OCI Task Service to persist and manage Tasks.
 * @author rajeshkurup@live.com
 */
@RestController
@RequestMapping("/v1/ocitaskserv")
public class OciTaskApi {

    public static Logger logger = LoggerFactory.getLogger(OciTaskApi.class);

    @Autowired
    OciTaskService taskService;

    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OciTaskServResponse> getTasks() {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.getTasks();
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            logger.error("Exception=" + ex.toString() + " - Stacktrace=" + Arrays.asList(ex.getStackTrace()).toString());
            OciError ociError = new OciError(OciErrorCode.INTERNAL_ERROR, ex.getMessage());
            resp = new OciTaskServResponse();
            resp.setError(ociError);
        }

        return new ResponseEntity<OciTaskServResponse>(resp, httpStatus);
    }

    @GetMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OciTaskServResponse> getTask(@PathVariable long id) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.getTask(id);
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            logger.error("Exception=" + ex.toString() + " - Stacktrace=" + Arrays.asList(ex.getStackTrace()).toString());
            OciError ociError = new OciError(OciErrorCode.INTERNAL_ERROR, ex.getMessage());
            resp = new OciTaskServResponse();
            resp.setError(ociError);
        }

        return new ResponseEntity<OciTaskServResponse>(resp, httpStatus);
    }

    @PostMapping(value = "/tasks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OciTaskServResponse> createTask(@RequestBody OciTaskServRequest ociTaskReq) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.saveTask(0L, ociTaskReq);
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            logger.error("Exception=" + ex.toString() + " - Stacktrace=" + Arrays.asList(ex.getStackTrace()).toString());
            OciError ociError = new OciError(OciErrorCode.INTERNAL_ERROR, ex.getMessage());
            resp = new OciTaskServResponse();
            resp.setError(ociError);
        }

        return new ResponseEntity<OciTaskServResponse>(resp, httpStatus);
    }

    @PutMapping(value = "/tasks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OciTaskServResponse> updateTask(@PathVariable long id, @RequestBody OciTaskServRequest ociTaskReq) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.saveTask(id, ociTaskReq);
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            logger.error("Exception=" + ex.toString() + " - Stacktrace=" + Arrays.asList(ex.getStackTrace()).toString());
            OciError ociError = new OciError(OciErrorCode.INTERNAL_ERROR, ex.getMessage());
            resp = new OciTaskServResponse();
            resp.setError(ociError);
        }

        return new ResponseEntity<OciTaskServResponse>(resp, httpStatus);
    }

    @DeleteMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OciTaskServResponse> deleteTask(@PathVariable long id) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.deleteTask(id);
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            logger.error("Exception=" + ex.toString() + " - Stacktrace=" + Arrays.asList(ex.getStackTrace()).toString());
            OciError ociError = new OciError(OciErrorCode.INTERNAL_ERROR, ex.getMessage());
            resp = new OciTaskServResponse();
            resp.setError(ociError);
        }

        return new ResponseEntity<OciTaskServResponse>(resp, httpStatus);
    }

    private static HttpStatus getHttpStatus(OciError ociError) {
        HttpStatus httpStatus = HttpStatus.OK;

        if(ociError != null) {
            switch(ociError.getErrorCode()) {
                case INTERNAL_ERROR, DATABASE_ERROR: httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; break;
                case INVALID_ARGUMENT: httpStatus = HttpStatus.BAD_REQUEST; break;
                case NO_DATA_FOUND: httpStatus = HttpStatus.NOT_FOUND; break;
                default: httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return httpStatus;
    }

}
