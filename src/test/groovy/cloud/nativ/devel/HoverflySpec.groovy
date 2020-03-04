package cloud.nativ.devel

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.specto.hoverfly.junit.rule.HoverflyRule
import org.junit.Rule
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static io.specto.hoverfly.junit.core.SimulationSource.dsl
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.json
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success
import static java.util.Collections.singletonMap
import static org.hamcrest.Matchers.equalTo

class HoverflySpec extends Specification {

    @Rule
    public HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
            service("samples.openweathermap.org")
                    .get("/data/2.5/weather")
                    .willReturn(success(json(singletonMap("Rosenheim", "Sunshine"))))
    ))

    def setupSpec() {
        // when using @ClassRule @Shared
        // we need to manually call the before() method
        // this seems like a bug and is a bit hacky
        // hoverflyRule.before()
    }

    def setup() {
        RestAssured.proxy(hoverflyRule.getProxyPort())
        RestAssured.baseURI = "http://samples.openweathermap.org"
    }

    def "Hoverfly Weather Simulation"() {
        given:
        def request = given().accept(ContentType.JSON).log().all(true)

        when:
        def response = request.get("/data/2.5/weather").then()

        then:
        response.log().all(true)
                .statusCode(200)
                .contentType("application/json")
                .body("Rosenheim", equalTo("Sunshine"))
    }
}
