package flume;

import org.apache.flume.node.Application;
import org.apache.flume.node.PropertiesFileConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class FlumeCtrlTest {
    private static Logger log = LoggerFactory.getLogger(FlumeCtrlTest.class);

    private static final String agentName = "a1";

    public static void main(final String[] args) {
        URL fileURL = Thread.currentThread().getContextClassLoader().getResource("http_test.conf");
        assert fileURL != null;
        File confFile = new File(fileURL.getFile());
        final PropertiesFileConfigurationProvider configurationProvider =
                new PropertiesFileConfigurationProvider(agentName, confFile);

        final Application application = new Application();
        application.handleConfigurationEvent(configurationProvider.getConfiguration());
        application.start();
        log.info("start.....");

        Runtime.getRuntime().addShutdownHook(new Thread("agent-shutdown-hook") {
            @Override
            public void run() {
                application.stop();
            }
        });

    }
}
