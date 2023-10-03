package dev.mrkevr.passwordencoder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.passwordencoder.service.EncoderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/encoder")
public class EncoderController {

	private final EncoderService encoderService;
	
	@GetMapping("/encode")
	public String encode(
			@RequestParam(name = "raw", required = true) String raw,
			@RequestParam(name = "encoder", required = false, defaultValue = "bcrypt") String encoder) {
		
		String encoded = encoderService.encode(encoder, raw);
		return encoded;
	}
	
	@GetMapping("/match")
	public boolean match(
			@RequestParam(name = "candidate", required = true) String candidate,
			@RequestParam(name = "encoded", required = true) String encoded,
			@RequestParam(name = "encoder", required = false, defaultValue = "bcrypt") String encoder) {

		return encoderService.match(encoder, candidate, encoded);
	}
}
