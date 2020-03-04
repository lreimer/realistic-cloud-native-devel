package cloud.nativ.devel

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.ClassRule
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given

@Testcontainers
class DockerComposeSpec extends Specification {

    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withExposedService("microservice_1", 8080, Wait.forListeningPort())
                    .waitingFor("microservice_1", Wait.forHttp("/openapi/").forPort(8080).forStatusCode(200))
                    .withBuild(true)
                    .withLocalCompose(true)

    def setupSpec() {
        environment.start()
    }

    def setup() {
        RestAssured.baseURI = "http://" + environment.getServiceHost("microservice_1", 8080)
        RestAssured.port = environment.getServicePort("microservice_1", 8080)
    }

    @Unroll
    def "GET #requestPath"() {
        given:
        def request = given().accept(ContentType.JSON).log().all(true)

        when:
        def response = request.get(requestPath).then()

        then:
        response.log().all(true).statusCode(expectedStatusCode)

        where:
        requestPath    | expectedStatusCode
        "/api/weather" | 200
        "/metrics"     | 200
        "/health"      | 200
    }
}
