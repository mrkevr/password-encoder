package dev.mrkevr.passwordencoder.exception;

public class NoSuchEncoderException extends RuntimeException {

	private static final long serialVersionUID = -9063778769259240324L;

	public NoSuchEncoderException(String qualifier) {
		super("No matching Encoder found for " + qualifier);
	}
}
