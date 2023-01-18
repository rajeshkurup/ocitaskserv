package org.oci.task.data.dao;

import org.oci.task.data.model.OciTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @brief Data Access Object for persisting Tasks in OCI Task System.
 * @author rajeskurup@live.com
 */
@Repository
public interface OciTaskDao extends JpaRepository<OciTask, Long> {
    // Empty
}
