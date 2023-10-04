package dev.mrkevr.passwordencoder.service;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import dev.mrkevr.passwordencoder.encoder.Encoder;
import dev.mrkevr.passwordencoder.encoder.NoSuchEncoderException;

@Service
public class EncoderService {

	public String encode(String encoderQualifier, String rawString) {
		Encoder encoder = this.getEncoderByQualifier(encoderQualifier);
		return encoder.encode(rawString);
	}

	public Boolean match(String encoderQualifier, String candidateString, String encodedString) {
		Encoder encoder = this.getEncoderByQualifier(encoderQualifier);
		return encoder.match(candidateString, encodedString);
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
			e.printStackTrace();
		}
		
		return null;
	}
}
