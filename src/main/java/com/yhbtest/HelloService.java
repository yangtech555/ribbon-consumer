package com.yhbtest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by yanghongbo on 2018/3/21.
 */
@Service
public class HelloService
{
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey")
    public String helloService(){
        String result="";
        long start = System.currentTimeMillis();
        result = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
        long end =System.currentTimeMillis();
        logger.info("SpendTime:"+(end-start));
        return  result;
    }

    public String helloFallback(){
        return "error";
    }
}
