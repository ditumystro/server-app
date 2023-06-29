package io.build.server.service;

import io.build.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * author : ditu
 * Time : 9:09 PM
 */
public interface serverService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    Collection<Server> list(int Limit);
    Server get (Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
