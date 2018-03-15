package broken1

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

@SpringBootApplication
class Application



fun getLocalIpAddress(): String {
    try {
        val en = NetworkInterface.getNetworkInterfaces()
        while (en.hasMoreElements()) {
            val intf = en.nextElement()
            val enumIpAddr = intf.inetAddresses
            while (enumIpAddr.hasMoreElements()) {
                val inetAddress = enumIpAddr.nextElement()
                if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                    return inetAddress.getHostAddress()
                }
            }
        }
    } catch (ex: SocketException) {
        ex.printStackTrace()
    }
    throw IllegalArgumentException()
}


fun main(args: Array<String>) {


    println("CONTAINER HAS JMX ADDRESS: ${getLocalIpAddress()}:9010 \n try:  visualvm --openjmx ${getLocalIpAddress()}:9010 \n cause breakage: \n curl  ${getLocalIpAddress()}:8080/breakme?type=1  ")
    SpringApplication.run(Application::class.java, *args)
}
