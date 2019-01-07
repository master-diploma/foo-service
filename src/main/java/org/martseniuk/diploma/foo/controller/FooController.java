package org.martseniuk.diploma.foo.controller;

import org.martseniuk.diploma.foo.service.AsyncService;
import org.martseniuk.diploma.foo.service.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Controller for foo service.
 *
 * @author Roman_Martseniuk
 */
@RestController
public class FooController {

    private static final Logger LOG = LoggerFactory.getLogger(FooController.class);

    private RpcService rpcService;
    private AsyncService asyncService;

    @Autowired
    public FooController(RpcService rpcService, AsyncService asyncService) {
        this.rpcService = rpcService;
        this.asyncService = asyncService;
    }

    @GetMapping(value = "/api/rpc/dumb-client")
    public String callDumbClient() {
        return rpcService.callDumbClient() + ". Host: " + getLocalHost();
    }

    @GetMapping(value = "/api/rpc/feign-client")
    public String callFeignClient() {
        return rpcService.callFeignClient() + ". Host: " + getLocalHost();
    }

    @GetMapping(value = "/api/async/message")
    public String postMessage(@RequestParam String setting) {
        asyncService.postMessage(setting);
        return "message was send, check the bar service. Host: " + getLocalHost();
    }

    private String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOG.error("Unknown host", e);
            return "unknown host";
        }
    }
}
