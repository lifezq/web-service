package com.yql.webservice.config;

import com.yql.webservice.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Package com.yql.webservice.config
 * @ClassName WebServiceConfig
 * @Description TODO
 * @Author Ryan
 * @Date 2022/8/5
 */
@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;
    @Autowired
    private UserService userService;

    /**
     * 如果有多个服务需要暴露，需定义多个EndpointImpl 的Bean，并关联到对应的 服务 ，绑定到指定路径
     *
     * @return
     */

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        // 设置入口拦截器，拦截器实现 org.apache.cxf.interceptor.Interceptor 接口即可
//        endpoint.getInInterceptors().add(new CustomInterceptor());
        endpoint.publish("/user");
        return endpoint;
    }
}