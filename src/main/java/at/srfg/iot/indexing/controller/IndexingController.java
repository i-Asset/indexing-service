package at.srfg.iot.indexing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexingController {
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
}
