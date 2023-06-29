package example.micronaut;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.rxjava3.core.Flowable;
import reactor.core.publisher.Flux;

import java.io.Flushable;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {


    @Post(value = "flowable", produces = {MediaType.TEXT_PLAIN}, consumes = {MediaType.APPLICATION_JSON})
    public Flowable<String> rxJava(@Body Flowable<Integer> flow) {
        return flow.delay(1, TimeUnit.MILLISECONDS).doOnNext(System.out::println).map(Object::toString);
    }

    @Post(value = "flux", produces = {MediaType.TEXT_PLAIN}, consumes = {MediaType.APPLICATION_JSON})
    public Flux<String> reactor(@Body Flux<Integer> flow) {
        return flow.delayElements(Duration.ofMillis(1)).doOnNext(System.out::println).map(Object::toString);
    }
}
