package com.bicjo.sample.bar.controller;

import java.security.Principal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/bar")
@ConditionalOnProperty(prefix = "example.bar.authentication", name = "enabled", havingValue = "true", matchIfMissing = false)
public class BarController {

	@GetMapping(value = "/login")
	public String login() {
		return "bar/login";
	}

	@GetMapping(value = "/me", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Principal me(Principal principal) {
		return principal;
	}

}
