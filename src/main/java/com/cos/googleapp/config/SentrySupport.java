package com.cos.googleapp.config;

import org.springframework.context.annotation.Configuration;

import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;

@Configuration
public class SentrySupport {

	// init은 생성자에서 해주는게 좋음
	public SentrySupport() {
		System.out.println("====================== SentrySupport init()");
		//중요한 내용이라서 yml에서 당겨와서 쓰는게 맞음
		Sentry.init("DSN 주소");
	}

	public void logSimpleMessage(String msg) {
		// This sends an event to Sentry.
		EventBuilder eventBuilder = new EventBuilder()
				.withMessage(msg)//msg를 동적으로 받음
				.withLevel(Event.Level.ERROR)//INFO->ERROR
				.withLogger(SentrySupport.class.getName());
		
		// Note that the *unbuilt* EventBuilder instance is passed in so that
		// EventBuilderHelpers are run to add extra information to your event.
		Sentry.capture(eventBuilder);//소켓 통신을 해서 오류를 날리는것
	}
}
