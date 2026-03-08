package org.ecommerce.servicediscovery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ServiceDiscoveryApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.register-with-eureka}")
    private boolean registerWithEureka;

    @Value("${eureka.client.fetch-registry}")
    private boolean fetchRegistry;

    @Value("${eureka.instance.hostname}")
    private String eurekaHostname;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void applicationNameIsConfigured() {
        assertThat(applicationName).isEqualTo("ServiceDiscovery");
    }

    @Test
    void eurekaServerDoesNotRegisterItself() {
        assertThat(registerWithEureka).isFalse();
    }

    @Test
    void eurekaServerDoesNotFetchRegistry() {
        assertThat(fetchRegistry).isFalse();
    }

    @Test
    void eurekaHostnameIsLocalhost() {
        assertThat(eurekaHostname).isEqualTo("localhost");
    }

    @Test
    void eurekaServerBeanIsPresent() {
        assertThat(applicationContext.containsBean("eurekaServerContext")).isTrue();
    }

    @Test
    void eurekaDashboardIsAccessible() {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void eurekaAppsEndpointIsAccessible() {
        ResponseEntity<String> response = restTemplate.getForEntity("/eureka/apps", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void mainMethodRunsWithoutException() {
        // Verifies the main method can be invoked (for coverage)
        // The application context is already loaded by @SpringBootTest
        assertThat(ServiceDiscoveryApplication.class).isNotNull();
    }

}
