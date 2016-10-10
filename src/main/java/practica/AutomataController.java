package practica;

import com.fasterxml.jackson.databind.ObjectMapper;
import practica.pojo.Pojo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import practica.Automata.AutomataFinito;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AutomataController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ObjectMapper mapper = new ObjectMapper();
   

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public Pojo doSomething(@RequestBody Pojo input) throws IOException {

        return Utils.AutomataToPojo(Utils.PojoToAutomata(input));
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String[][] afndtoafd(@RequestBody Pojo input) throws IOException {
        AutomataFinito a = new AutomataFinito(Utils.PojoToAutomata(input));
        if (a.isAFND()) {
            a.AFNDTOAFD();
            return a.automata;
        } else {
              return a. automata;
        }

    }
}
