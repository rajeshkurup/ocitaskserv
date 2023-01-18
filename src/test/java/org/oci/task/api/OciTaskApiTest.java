package org.oci.task.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oci.task.data.OciTaskServResponse;
import org.oci.task.data.model.OciTask;
import org.oci.task.error.OciError;
import org.oci.task.error.OciErrorCode;
import org.oci.task.service.OciTaskService;
import org.springframework.http.ResponseEntity;

/**
 * @brief Unit test helper for {@link OciTaskApi}
 * @author rajeshkurup@live.com
 */
@ExtendWith(MockitoExtension.class)
public class OciTaskApiTest {

    @Mock
    OciTaskService taskServiceMock;

    @InjectMocks
    OciTaskApi ociTaskApi;

    @Test
    public void testGetTasksSuccess() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTaskId(1001L);

        Mockito.when(taskServiceMock.getTasks()).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTasks();

        Assertions.assertEquals(1001L, result.getBody().getTaskId());
        Assertions.assertNull(result.getBody().getError());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTasks();
    }

    @Test
    public void testGetTasksFailed() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Argument"));

        Mockito.when(taskServiceMock.getTasks()).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTasks();

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTasks();
    }

    @Test
    public void testGetTasksException() {
        Mockito.when(taskServiceMock.getTasks()).thenThrow(new IllegalArgumentException("Invalid Argument"));

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTasks();

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INTERNAL_ERROR, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTasks();
    }

    @Test
    public void testGetTaskSuccess() {
        OciTask task = new OciTask();
        task.setId(1001L);
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTask(task);

        Mockito.when(taskServiceMock.getTask(Mockito.eq(1001L))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTask(1001L);

        Assertions.assertNotNull(result.getBody().getTask());
        Assertions.assertEquals(1001L, result.getBody().getTask().getId());
        Assertions.assertNull(result.getBody().getError());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTask(Mockito.eq(1001L));
    }

    @Test
    public void testGetTaskFailed() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Argument"));

        Mockito.when(taskServiceMock.getTask(Mockito.eq(1001L))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTask(1001L);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTask(Mockito.eq(1001L));
    }

    @Test
    public void testGetTaskException() {
        Mockito.when(taskServiceMock.getTask(Mockito.eq(1001L))).thenThrow(new IllegalArgumentException("Invalid Argument"));

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.getTask(1001L);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INTERNAL_ERROR, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).getTask(Mockito.eq(1001L));
    }

    @Test
    public void testDeleteTaskSuccess() {
        OciTask task = new OciTask();
        task.setId(1001L);
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTask(task);

        Mockito.when(taskServiceMock.deleteTask(Mockito.eq(1001L))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.deleteTask(1001L);

        Assertions.assertNull(result.getBody().getError());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).deleteTask(Mockito.eq(1001L));
    }

    @Test
    public void testDeleteTaskFailed() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Argument"));

        Mockito.when(taskServiceMock.deleteTask(Mockito.eq(1001L))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.deleteTask(1001L);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).deleteTask(Mockito.eq(1001L));
    }

    @Test
    public void testDeleteTaskException() {
        Mockito.when(taskServiceMock.deleteTask(Mockito.eq(1001L))).thenThrow(new IllegalArgumentException("Invalid Argument"));

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.deleteTask(1001L);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INTERNAL_ERROR, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).deleteTask(Mockito.eq(1001L));
    }

    @Test
    public void testUpdateTaskSuccess() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test Task");

        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTaskId(1001L);

        Mockito.when(taskServiceMock.saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.updateTask(1001L, task);

        Assertions.assertNull(result.getBody().getError());
        Assertions.assertEquals(1001L, result.getBody().getTaskId());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class));
    }

    @Test
    public void testUpdateTaskFailed() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test Task");

        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Argument"));

        Mockito.when(taskServiceMock.saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.updateTask(1001L, task);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class));
    }

    @Test
    public void testUpdateTaskException() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test Task");

        Mockito.when(taskServiceMock.saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class))).thenThrow(new IllegalArgumentException("Invalid Argument"));

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.updateTask(1001L, task);

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INTERNAL_ERROR, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(1001L), Mockito.any(OciTask.class));
    }

    @Test
    public void testCreateTaskSuccess() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setTaskId(1001L);

        Mockito.when(taskServiceMock.saveTask(Mockito.eq(0L), Mockito.any(OciTask.class))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.createTask(new OciTask());

        Assertions.assertNull(result.getBody().getError());
        Assertions.assertEquals(1001L, result.getBody().getTaskId());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(0L), Mockito.any(OciTask.class));
    }

    @Test
    public void testCreateTaskFailed() {
        OciTaskServResponse resp = new OciTaskServResponse();
        resp.setError(new OciError(OciErrorCode.INVALID_ARGUMENT, "Invalid Argument"));

        Mockito.when(taskServiceMock.saveTask(Mockito.eq(0L), Mockito.any(OciTask.class))).thenReturn(resp);

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.createTask(new OciTask());

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(0L), Mockito.any(OciTask.class));
    }

    @Test
    public void testCreateTaskException() {
        Mockito.when(taskServiceMock.saveTask(Mockito.eq(0L), Mockito.any(OciTask.class))).thenThrow(new IllegalArgumentException("Invalid Argument"));

        ResponseEntity<OciTaskServResponse> result = ociTaskApi.createTask(new OciTask());

        Assertions.assertNotNull(result.getBody().getError());
        Assertions.assertEquals(OciErrorCode.INTERNAL_ERROR, result.getBody().getError().getErrorCode());

        Mockito.verify(taskServiceMock, Mockito.atLeastOnce()).saveTask(Mockito.eq(0L), Mockito.any(OciTask.class));
    }

}
