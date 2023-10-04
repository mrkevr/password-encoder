package dev.mrkevr.passwordencoder.encoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component("sha1")
public class SHA1Encoder implements Encoder {

	@Override
	public String encode(String rawString) {
		return DigestUtils.sha1Hex(rawString);
	}

	@Override
	public Boolean match(String candidateString, String encodedString) {
		candidateString = DigestUtils.sha1Hex(candidateString).toUpperCase();
		encodedString = encodedString.toUpperCase();
		return candidateString.equals(encodedString);
	}
}
