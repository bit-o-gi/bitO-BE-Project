package bit.config;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FailoverIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.introspector.JacksonObjectArbitraryIntrospector;
import java.util.Arrays;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FixtureMonkeyConfig {

    @Bean
    public FixtureMonkey defaultFixtureMonkey() {
        return FixtureMonkey.builder()
                .objectIntrospector(
                        new FailoverIntrospector(Arrays.asList(
                                JacksonObjectArbitraryIntrospector.INSTANCE,
                                FieldReflectionArbitraryIntrospector.INSTANCE
                        ))
                )
                .build();
    }
}
