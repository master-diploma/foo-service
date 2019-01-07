package org.martseniuk.diploma.foo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign client for bar service.
 *
 * @author Roman_Martseniuk
 */
@FeignClient("bar-service")
public interface BarFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/setting")
    String getSetting();
}
