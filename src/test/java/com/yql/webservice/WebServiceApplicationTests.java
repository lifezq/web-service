package com.yql.webservice;

import com.alibaba.fastjson.JSON;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.kalpana.springboot.User;
import top.kalpana.springboot.UserServiceImpl;
import top.kalpana.springboot.UserServiceImplService;

import javax.xml.namespace.QName;

@SpringBootTest
class WebServiceApplicationTests {

    @Test
    void testWebServiceDynamic() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/student-service/user?wsdl");
        // 设置出口拦截器，拦截器实现 org.apache.cxf.interceptor.Interceptor 接口即可
//        client.getOutInterceptors().add(new CustomInterceptor());
        Object[] objects;
        try {
            // p1:服务所在命名空间，p2:方法名
            QName opName = new QName("http://springboot.kalpana.top", "getById");
            User user = new User();
            user.setId(3L);
            objects = client.invoke(opName, user);
            System.out.println("测试动态响应 : " + JSON.toJSONString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testWebServiceStatic() {
        UserServiceImplService userServiceImplService = new UserServiceImplService();

        UserServiceImpl userServiceImplPort = userServiceImplService.getUserServiceImplPort();
        User user = new User();
        user.setId(3L);
        user = userServiceImplPort.getById(user);
        System.out.println("静态测试返回的数据: " + JSON.toJSONString(user));
    }
}
