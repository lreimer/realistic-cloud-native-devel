package cloud.nativ.devel

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.Rule
import org.testcontainers.containers.NginxContainer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.containsString
import static spock.util.matcher.HamcrestSupport.expect

@Testcontainers
class NginxContainerTest extends Specification {

    @Rule
    public NginxContainer nginx = new NginxContainer<>().waitingFor(new HttpWaitStrategy())

    def setup() {
        RestAssured.baseURI = "http://" + nginx.getContainerIpAddress()
        RestAssured.port = nginx.getMappedPort(80)
    }

    def "Get / from Nginx with Spock and Hamcrest"() {
        given:
        def url = nginx.getBaseUrl("http", 80)

        when:
        def text = url.openStream().text

        then:
        expect text, containsString("Welcome to nginx!")
    }

    @Unroll
    def "Get #requestPath from Nginx with REST-assured"() {
        given:
        def request = given().accept(ContentType.ANY).log().all(true)

        when:
        def response = request.get(requestPath).then()

        then:
        response.log().all(true).statusCode(expectedStatusCode)

        where:
        requestPath   | expectedStatusCode
        "/"           | 200
        "/index.html" | 200
        "/404.html"   | 404
    }
}
