package org.oci.task.api;

import org.oci.task.data.OciTaskServResponse;
import org.oci.task.error.OciError;
import org.oci.task.error.OciErrorCode;
import org.oci.task.service.OciTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @brief APIs hosted by OCI Task Service to persist and manage Tasks.
 * @author rajeshkurup@live.com
 */
@RestController
@RequestMapping("/ocitask")
public class OciTaskApi {

    @Autowired
    OciTaskService taskService;

    @GetMapping("/")
    public ResponseEntity<OciTaskServResponse> getTasks() {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        OciTaskServResponse resp = null;

        try {
            resp = taskService.getTasks();
            httpStatus = getHttpStatus(resp.getError());
        }
        catch(Exception ex) {
            ex.printStackTrace();
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
            }
        }

        return httpStatus;
    }

}
