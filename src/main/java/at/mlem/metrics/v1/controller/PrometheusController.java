package at.mlem.metrics.v1.controller;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> getMetrics(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/openmetrics-text;charset=utf-8");

        return new ResponseEntity<>(
                new String(
                        registry.scrape().getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8
                ),
                responseHeaders,
                HttpStatus.OK);
    }


}
