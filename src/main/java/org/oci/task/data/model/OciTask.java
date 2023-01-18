package org.oci.task.data.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @brief Schema for Task entity in OCI Task Service.
 * @author rajeshkurup@live.com
 */
@Entity
@Table(name = OciTask.TABLE_NAME)
public class OciTask {

    public static final String TABLE_NAME= "OCI_TASK";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PRIORITY", columnDefinition = "TINYINT")
    private int priority;

    @Column(name = "TITLE", columnDefinition = "VARCHAR(1024) NOT NULL")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "COMPLETED", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean completed;

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
    private Date startDate;

    @Column(name = "DUE_DATE", columnDefinition = "TIMESTAMP")
    private Date dueDate;

    @UpdateTimestamp
    @Column(name = "TIME_UPDATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date timeUpdated;

    @CreationTimestamp
    @Column(name = "TIME_CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date timeCreated;

    public OciTask() {
        // Empty
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

}
