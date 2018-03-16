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

  val passwords = ArrayList<String>()
  val indices = java.util.HashMap<String, Int>()
  init {
    val r = Random()
    for (i in 1..300) {
      passwords.add("Foo"+(r.nextInt()))
    }
    val correctIndex = r.nextInt(300)
    for (i in 1..20) {
      indices.put(java.util.UUID.randomUUID().toString(), r.nextInt(300))
    }
    indices.put("correct", correctIndex)
  }

    @GetMapping("/login")
    fun login(@RequestParam(value = "password") password: String): StatusResponse {
        return if (passwords.get(indices.get("correct") ?: 0)==password)  StatusResponse("SUCCESS") else  StatusResponse("WRONG PASSWORD")
    }


}
