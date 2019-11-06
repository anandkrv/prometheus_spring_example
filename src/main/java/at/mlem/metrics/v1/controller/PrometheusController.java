package at.mlem.metrics.v1.controller;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping
public class PrometheusController {

    @Autowired
    public PrometheusController(PrometheusMeterRegistry registry) {
        this.registry = registry;
    }

    private final PrometheusMeterRegistry registry;

    @GetMapping(value = "/prometheus", produces = {"application/openmetrics-text;charset=UTF-8"})
    @ResponseBody
    @SuppressWarnings("unused")
    public String getMetrics(){
        return new String(registry.scrape().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

}
