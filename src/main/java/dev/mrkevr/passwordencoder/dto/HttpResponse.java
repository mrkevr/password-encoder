package dev.mrkevr.passwordencoder.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {

	private LocalDateTime timeStamp;

	private int status;

	private String encoder;

	private Object body;
}
