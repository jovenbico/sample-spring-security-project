package com.bicjo.sample.foo.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController {

	@GetMapping(value = "/login")
	public String login() {
		return "foo/login";
	}

	@GetMapping(value = "/me", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Principal me(Principal principal) {
		return principal;
	}

}
