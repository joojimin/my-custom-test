package com.example.mycustomtest.embeddeddb;

import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_17;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import java.time.ZoneId;
import java.util.TimeZone;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public abstract class EmbeddedDbSupport {

    private static final EmbeddedMysql server;
    private static final String USER = "test";
    private static final String KEY = "test1234";
    private static final int PORT = 13306;

    static {
        MysqldConfig config = aMysqldConfig(v8_0_17)
            .withCharset(Charset.aCharset("utf8mb4", "utf8mb4_bin"))
            .withPort(PORT)
            .withUser(USER, KEY)
            .withTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
            .withServerVariable("sql_mode", "ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION")
            .withServerVariable("max_connect_errors", 666)
            .build();

        server = EmbeddedMysql.anEmbeddedMysql(config)
                              .addSchema("test")
                              .start();
    }
}
