package broken1

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    println("CONTAINER HAS JMX ADDRESS: 127.0.0.1:9010 \n try: visualvm --openjmx 127.0.0.1:9010 \n cause breakage: \n curl '127.0.0.1:8080/breakme?type=1'")
    SpringApplication.run(Application::class.java, *args)
}
