package io.build.server.repo;

import io.build.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author : ditu
 * Time : 9:01 PM
 */
public interface serverRepo extends JpaRepository<Server, Long> {

    Server findByIpAddress(String ipAddress);
}
