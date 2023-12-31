package io.build.server.impl;

import io.build.server.model.Server;
import io.build.server.repo.serverRepo;
import io.build.server.service.serverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static io.build.server.enumeration.Status.SERVER_DOWN;
import static io.build.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

/**
 * Author : ditu
 * Time : 7:25 AM
 */

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerImpl implements serverService {

    private final serverRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());

        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }


    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {

        log.info("Updating server : {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by Id : {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl(){

        String[] imageNames = {"server1.png", "server2.png", "server3.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(3)]).toUriString();
    }

}
