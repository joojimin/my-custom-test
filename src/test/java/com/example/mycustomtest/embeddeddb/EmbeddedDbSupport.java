package com.example.mycustomtest.embeddeddb;

import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_17;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import java.time.ZoneId;
import java.util.TimeZone;
import org.flywaydb.core.Flyway;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public abstract class EmbeddedDbSupport {

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:13306/test?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&useSSL=true&verifyServerCertificate=false";
    private static EmbeddedMysql server;
    private static String USER = "test";
    private static String KEY = "test1234";

    static {
        MysqldConfig config = aMysqldConfig(v8_0_17)
            .withCharset(Charset.aCharset("utf8mb4", "utf8mb4_bin"))
            .withPort(13306)
            .withUser(USER, KEY)
            .withTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
            .withServerVariable("sql_mode", "ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION")
            .withServerVariable("max_connect_errors", 666)
            .build();

        server = EmbeddedMysql.anEmbeddedMysql(config)
                              .addSchema("test")
                              .start();
//        initSchema();
    }

    private static void initSchema() {

        var config = Flyway.configure().dataSource(DB_URL, USER, KEY);
        Flyway flyway = new Flyway(config);
        flyway.migrate();
    }
}
