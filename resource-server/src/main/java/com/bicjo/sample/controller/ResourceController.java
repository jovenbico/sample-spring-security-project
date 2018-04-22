package com.bicjo.sample.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

	@GetMapping(value = "/me", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Principal me(Principal principal) {
		return principal;
	}

}
