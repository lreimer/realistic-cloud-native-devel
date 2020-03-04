package cloud.nativ.devel

import org.junit.Rule
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import java.sql.Connection
import java.sql.DriverManager

@Testcontainers
class PostgreContainerSpec extends Specification {

    @Rule
    public PostgreSQLContainer postgre = new PostgreSQLContainer("postgres:12.2-alpine")
            .withDatabaseName("spock")
            .withUsername("user")
            .withPassword("password")

    Connection connection

    def setup() {
        postgre.start()

        def props = new Properties()
        props.setProperty("user", "user")
        props.setProperty("password", "password")

        connection = DriverManager.getConnection(postgre.getJdbcUrl(), props)
    }

    def "Select 1 from PostgreSQL"() {
        given:


        def statement = connection.prepareStatement("SELECT 1")

        when:
        def resultSet = statement.executeQuery()

        then:
        resultSet.next()
        resultSet.getInt(1) == 1

        cleanup:
        resultSet.close()
        statement.close()
    }
}
