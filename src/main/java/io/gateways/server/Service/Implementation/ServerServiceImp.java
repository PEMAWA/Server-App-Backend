package io.gateways.server.Service.Implementation;

import io.gateways.server.Model.Server;
import io.gateways.server.Service.ServerService;
import io.gateways.server.enumeration.Status;
import io.gateways.server.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server :{} " ,server.getName());
        server.setImageURL(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP :{} " ,ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? Status.SERVER_UP: Status.SERVER_DOWN);
        // Pinging a server
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching All Servers  " );

        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching Server by ID : {}  " ,id);

        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server :{} " ,server.getName());
        return serverRepo.save(server);

    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting Server By ID :{} " ,id);
        serverRepo.deleteById(id);
        return true;
    }
    private String setServerImageUrl() {
        String[] Imagenames={"server.png","server1.png","server2.png","server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/Images/" +Imagenames[new Random().nextInt(4)]).toUriString();
    }
}
