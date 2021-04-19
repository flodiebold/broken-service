package broken1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicLong

@RestController
class BreakMeController {

    val allRequests: MutableList<DataContainer> = mutableListOf()

    val counter = AtomicLong()


    final tailrec fun havefun() {
        "I can do lots of strings ${counter.get()}+1=${counter.incrementAndGet()}"
        havefun()
    }

    @GetMapping("/breakme")
    fun breakme(@RequestParam(value = "type", defaultValue = "1") type: String): StatusResponse {
        return when (type) {
            "1" -> {
                for (i in 1..50) {
                    allRequests.add(DataContainer("Some ${counter.incrementAndGet()}: $i", Thread()))
                }
                StatusResponse("Causing in small leak, find out what")
            }
            "2" -> {
                havefun()
                StatusResponse("Finished. That took a while.")
            }
            else -> StatusResponse("dont know $type")
        }

    }


}
