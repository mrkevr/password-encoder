package dev.mrkevr.passwordencoder.encoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component("sha256")
public class SHA256Encoder implements Encoder {

	@Override
	public String encode(String rawString) {
		return DigestUtils.sha256Hex(rawString);
	}

	@Override
	public Boolean match(String candidateString, String encodedString) {
		candidateString = DigestUtils.sha256Hex(candidateString);
		return candidateString.equalsIgnoreCase(encodedString);
	}

	@Override
	public String getEncoderName() {
		return "sha256";
	}
}
