package openshift.cop;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class GreetingController {

    private static final String template = "Yellow, %s!";
    private final Counter counter;

    public GreetingController() {
       counter = new Counter("greeting_counter");
    }

    @RequestMapping(value = "/greeting" ,  method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        counter.increment();
        return new Greeting((int)counter.count(),
                            String.format(template, name));
    }

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/greeting");
    }


    @RequestMapping(value = "/hostinfo",  method = RequestMethod.GET)
    public HostInfo hostinfo() throws IOException {
      return new HostInfo();
    }
    
    @RequestMapping(value = "/envinfo" ,  method = RequestMethod.GET)
    public EnvInfo envinfo(@RequestParam(value="filter", defaultValue="*") String filter) throws IOException {
      return new EnvInfo(filter);
    }
    
}
