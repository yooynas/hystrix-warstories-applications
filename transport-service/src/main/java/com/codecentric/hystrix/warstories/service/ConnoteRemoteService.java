package com.codecentric.hystrix.warstories.service;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Service
public class ConnoteRemoteService {

    private DynamicStringProperty connoteServiceAddress = DynamicPropertyFactory.getInstance()
        .getStringProperty("service.address.connote", "http://localhost:8080/connote/create");

    @HystrixCommand(commandKey = "ConnoteClientCmdKey", threadPoolKey = "ConnoteClientThreadPool")
    public ConnoteDTO createConnote() {
        Assert.hasText(connoteServiceAddress.get());

        RestTemplate client = new RestTemplate();
        URI uri = URI.create(connoteServiceAddress.get());
        ResponseEntity<ConnoteDTO> dtoResponseEntity = client.getForEntity(uri, ConnoteDTO.class);

        return dtoResponseEntity.getBody();
    }
}
