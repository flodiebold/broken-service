package broken1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicLong
import java.util.Random

@RestController
class AuthController {

    val default_password  = "Foo"+(Random().nextInt() )

    @GetMapping("/login")
    fun login(@RequestParam(value = "password") password: String): StatusResponse {
        return if (default_password==password)  StatusResponse("SUCCESS") else  StatusResponse("WRONG PASSWORD")
    }


}
