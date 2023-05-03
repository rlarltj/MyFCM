package com.example.myfcm.config;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.myfcm.exception.FcmException;
import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FCMInitializer {

	@Value("${fcm.certification}")
	private String credential;

	@PostConstruct
	public void initialize(){
		ClassPathResource resource = new ClassPathResource(credential);

		try (InputStream stream = resource.getInputStream()) {
			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(stream))
				.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
				log.info("FirebaseApp 초기화 완료");
			}
		}catch (Exception e){
			e.printStackTrace();
			log.warn("FirebaseApp 초기화 실패");
			throw new FcmException();

		}

	}
}