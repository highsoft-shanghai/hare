package ltd.highsoft.frameworks.test.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/web-test")
public class WebTestController {

    @GetMapping("simple-get")
    public Object simpleGet() {
        return Map.of("name", "John");
    }

    @GetMapping("get-with-parameters")
    public Object getWithParameters(@RequestParam String q) {
        return Map.of("q", q);
    }

    @PostMapping("post")
    public Object post(@RequestBody Map<String, Object> body) {
        return body;
    }

    @PostMapping("post/{id}")
    public Object postWithPathVariables(@PathVariable String id, @RequestBody Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>(body);
        result.put("id", id);
        return result;
    }

    @PostMapping("post-with-file")
    public Object postWithFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        return new String(file.getBytes());
    }

    @PutMapping("put")
    public Object put(@RequestBody Map<String, Object> body) {
        return body;
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable String id) {
        return Map.of("id", id);
    }

    @GetMapping("api-header/{id}")
    public Object documentApiHeader(@PathVariable String id) {
        return Map.of("id", id);
    }

    @GetMapping("api-header")
    public Object documentApiHeader() {
        return Map.of("id", "1");
    }

    @PostMapping("document-constrained-fields/{id}")
    public Object documentConstrainedFields(@PathVariable String id) {
        return Map.of("id", id);
    }

    @GetMapping("document-constrained-parameters")
    public Object documentConstrainedParameters() {
        return Map.of("content", "test");
    }

    @GetMapping("document-paged-response")
    public Object documentPagedResponse() {
        return Map.of(
            "first", true, "last", true, "numberOfTotalPages", 1, "numberOfTotalElements", 1, "numberOfElements", 1, "size", 10, "number", 0,
            "sort", List.of(Map.of("property", "name", "direction", "asc")), "content", List.of(Map.of("name", "John"))
        );
    }

}
