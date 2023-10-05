package dev.mrkevr.passwordencoder.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.passwordencoder.dto.HttpResponse;
import dev.mrkevr.passwordencoder.service.EncoderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/encoder")
public class EncoderController {

	private final EncoderService encoderService;
	
	@GetMapping("/list")
	public ResponseEntity<?> encode() {
		return ResponseEntity.ok(encoderService.getEncoders());
	}
	
	@GetMapping("/encode")
	public ResponseEntity<?> encode(
			@RequestParam(name = "raw", required = true) String raw,
			@RequestParam(name = "encoder", required = false, defaultValue = "bcrypt") String encoder) {
		
		String encoded = encoderService.encode(encoder, raw);
		
		HttpResponse response = HttpResponse.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.encoder(encoder.toUpperCase())
				.body(encoded)
				.build();
				
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/match")
	public ResponseEntity<?> match(
			@RequestParam(name = "candidate", required = true) String candidate,
			@RequestParam(name = "encoded", required = true) String encoded,
			@RequestParam(name = "encoder", required = false, defaultValue = "bcrypt") String encoder) {
		
		Boolean isMatch = encoderService.match(encoder, candidate, encoded);
		Map<String, Object> body = new HashMap<>();
		body.put("candidate", candidate);
		body.put("encoded", encoded);
		body.put("result", isMatch ? "Match" : "Not Match");
		
		HttpResponse response = HttpResponse.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.encoder(encoder.toUpperCase())
				.body(body)
				.build();
		
		return ResponseEntity.ok(response);
	}
}
