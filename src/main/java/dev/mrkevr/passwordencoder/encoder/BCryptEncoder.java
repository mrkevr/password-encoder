package dev.mrkevr.passwordencoder.encoder;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component("bcrypt")
public class BCryptEncoder implements Encoder {

	@Override
	public String encode(String rawString) {
		return BCrypt.hashpw(rawString, BCrypt.gensalt());
	}

	@Override
	public Boolean match(String candidateString, String encodedString) {
		return BCrypt.checkpw(candidateString, encodedString);
	}
}
