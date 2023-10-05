package dev.mrkevr.passwordencoder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import dev.mrkevr.passwordencoder.encoder.Encoder;
import dev.mrkevr.passwordencoder.exception.NoSuchEncoderException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncoderService {
	
	private final List<Encoder> encoders;

	public String encode(String encoderQualifier, String rawString) {
		Encoder encoder = this.getEncoderByQualifier(encoderQualifier);
		return encoder.encode(rawString);
	}

	public Boolean match(String encoderQualifier, String candidateString, String encodedString) {
		Encoder encoder = this.getEncoderByQualifier(encoderQualifier);
		return encoder.match(candidateString, encodedString);
	}
	
	public List<String> getEncoders(){
		return encoders.stream().map(e -> e.getEncoderName()).collect(Collectors.toList());
	}
	
	private Encoder getEncoderByQualifier(String encoderQualifier) {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.scan("dev.mrkevr.passwordencoder.encoder");
			ctx.refresh();
			Encoder encoder = BeanFactoryAnnotationUtils.qualifiedBeanOfType(ctx.getBeanFactory(), Encoder.class, encoderQualifier.toLowerCase());
			ctx.close();
			return encoder;
		} catch (NoSuchBeanDefinitionException ex) {
			throw new NoSuchEncoderException(encoderQualifier);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
