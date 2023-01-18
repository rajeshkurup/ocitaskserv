package org.oci.task.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oci.task.data.api.OciTaskServRequest;
import org.oci.task.data.api.OciTaskServResponse;
import org.oci.task.data.dao.OciTaskDao;
import org.oci.task.data.model.OciTask;
import org.oci.task.error.OciErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @brief Unit test helper for {@link OciTaskServiceImpl}
 * @author rajeshkurup@live.com
 */
@ExtendWith(MockitoExtension.class)
public class OciTaskServiceImplTest {

    @Mock
    OciTaskDao ociTaskDaoMock;

    @InjectMocks
    OciTaskServiceImpl ociTaskServiceImpl;

    @Test
    public void testGetTasksSuccess() {
        List<OciTask> tasks = new ArrayList<OciTask>();
        tasks.add(new OciTask());

        Mockito.when(ociTaskDaoMock.findAll()).thenReturn(tasks);

        OciTaskServResponse result = ociTaskServiceImpl.getTasks();

        Assertions.assertNotNull(result.getTasks());
        Assertions.assertEquals(1, result.getTasks().size());
        Assertions.assertNull(result.getError());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void testGetTasksFailedNoTaskFound() {
        List<OciTask> tasks = new ArrayList<OciTask>();

        Mockito.when(ociTaskDaoMock.findAll()).thenReturn(tasks);

        OciTaskServResponse result = ociTaskServiceImpl.getTasks();

        Assertions.assertNotNull(result.getTasks());
        Assertions.assertEquals(0, result.getTasks().size());
        Assertions.assertNull(result.getError());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void testGetTaskSuccess() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test task");

        Optional<OciTask> optTask = Optional.of(task);

        Mockito.when(ociTaskDaoMock.findById(Mockito.eq(1001L))).thenReturn(optTask);

        OciTaskServResponse result = ociTaskServiceImpl.getTask(1001L);

        Assertions.assertNotNull(result.getTask());
        Assertions.assertEquals(1001L, result.getTask().getId());
        Assertions.assertEquals("My test task", result.getTask().getTitle());
        Assertions.assertNull(result.getError());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).findById(Mockito.eq(1001L));
    }

    @Test
    public void testGetTaskFailedNoTaskFound() {
        Optional<OciTask> optTask = Optional.empty();

        Mockito.when(ociTaskDaoMock.findById(Mockito.eq(1001L))).thenReturn(optTask);

        OciTaskServResponse result = ociTaskServiceImpl.getTask(1001L);

        Assertions.assertNull(result.getTask());
        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals(OciErrorCode.NO_DATA_FOUND, result.getError().getErrorCode());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).findById(Mockito.eq(1001L));
    }

    @Test
    public void testGetTaskFailedInvalidArgument() {
        Mockito.when(ociTaskDaoMock.findById(Mockito.eq(1001L))).thenThrow(new IllegalArgumentException("Invalid Identifier"));

        OciTaskServResponse result = ociTaskServiceImpl.getTask(1001L);

        Assertions.assertNull(result.getTask());
        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getError().getErrorCode());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).findById(Mockito.eq(1001L));
    }

    @Test
    public void testDeleteTaskSuccess() {
        Mockito.doNothing().when(ociTaskDaoMock).deleteById(Mockito.eq(1001L));

        OciTaskServResponse result = ociTaskServiceImpl.deleteTask(1001L);

        Assertions.assertNull(result.getTask());
        Assertions.assertNull(result.getError());
        Assertions.assertEquals(0L, result.getTaskId());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).deleteById(Mockito.eq(1001L));
    }

    @Test
    public void testDeleteTaskFailedInvalidArgument() {
        Mockito.doThrow(new IllegalArgumentException("Invalid Identifier")).when(ociTaskDaoMock).deleteById(Mockito.eq(1001L));

        OciTaskServResponse result = ociTaskServiceImpl.deleteTask(1001L);

        Assertions.assertNull(result.getTask());
        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getError().getErrorCode());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).deleteById(Mockito.eq(1001L));
    }

    @Test
    public void testSaveTaskSuccessForCreate() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test task");

        Mockito.when(ociTaskDaoMock.save(Mockito.any(OciTask.class))).thenReturn(task);

        OciTaskServResponse result = ociTaskServiceImpl.saveTask(0L, new OciTaskServRequest());

        Assertions.assertNull(result.getTask());
        Assertions.assertEquals(1001L, result.getTaskId());
        Assertions.assertNull(result.getError());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).save(Mockito.any(OciTask.class));
    }

    @Test
    public void testSaveTaskSuccessForUpdate() {
        OciTask task = new OciTask();
        task.setId(1001L);
        task.setTitle("My test task");

        Mockito.when(ociTaskDaoMock.save(Mockito.any(OciTask.class))).thenReturn(task);

        OciTaskServResponse result = ociTaskServiceImpl.saveTask(1001L, new OciTaskServRequest());

        Assertions.assertNull(result.getTask());
        Assertions.assertEquals(1001L, result.getTaskId());
        Assertions.assertNull(result.getError());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).save(Mockito.any(OciTask.class));
    }

    @Test
    public void testSaveTaskFailedInvalidTaskObject() {
        OciTaskServResponse result = ociTaskServiceImpl.saveTask(1001L, null);

        Assertions.assertNull(result.getTask());
        Assertions.assertEquals(0L, result.getTaskId());
        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getError().getErrorCode());

        Mockito.verify(ociTaskDaoMock, Mockito.never()).save(Mockito.any(OciTask.class));
    }

    @Test
    public void testSaveTaskFailedInvalidArgument() {
        Mockito.when(ociTaskDaoMock.save(Mockito.any(OciTask.class))).thenThrow(new IllegalArgumentException("Invalid Argument"));

        OciTaskServResponse result = ociTaskServiceImpl.saveTask(1001L, new OciTaskServRequest());

        Assertions.assertNull(result.getTask());
        Assertions.assertEquals(0L, result.getTaskId());
        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals(OciErrorCode.INVALID_ARGUMENT, result.getError().getErrorCode());

        Mockito.verify(ociTaskDaoMock, Mockito.atLeastOnce()).save(Mockito.any(OciTask.class));
    }

}
