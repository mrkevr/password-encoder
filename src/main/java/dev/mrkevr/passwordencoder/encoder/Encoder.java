package dev.mrkevr.passwordencoder.encoder;

public interface Encoder {

	String encode(String rawString);

	Boolean match(String candidateString, String encodedString);

	String getEncoderName();
}
