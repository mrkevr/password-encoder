package dev.mrkevr.passwordencoder.encoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component("md5")
public class MD5Encoder implements Encoder {

	@Override
	public String encode(String rawString) {
		return DigestUtils.md5Hex(rawString);
	}

	@Override
	public Boolean match(String candidateString, String encodedString) {
		candidateString = DigestUtils.md5Hex(candidateString).toUpperCase();
		encodedString = encodedString.toUpperCase();
		return candidateString.equals(encodedString);
	}

}
