# 스프링부트 구글 애널리틱스, Sentriy.io

## 스프링부트 프로젝트 의존성

- Web
- DevTools
- Mustache
- Sentry 추가한것

```xml
		<dependency>
			<groupId>io.sentry</groupId>
			<artifactId>sentry</artifactId>
			<version>1.7.30</version>
		</dependency>
```

![image](https://user-images.githubusercontent.com/62128942/90353822-49fe6300-e082-11ea-8b7f-4b03c5ac9bf8.png)

![image](https://user-images.githubusercontent.com/62128942/90354015-e1fc4c80-e082-11ea-9ae6-7e35dbad384b.png)

## 구글 애널리틱스

- 파이어베이스에 프로젝트 생성 (웹)
- 모든 웹페이지에 footer로 자바스크립트 집어넣기

```js
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.18.0/firebase-app.js"></script>

<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/7.18.0/firebase-analytics.js"></script>

<script>
  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "API키",
    authDomain: "app-f8626.firebaseapp.com",
    databaseURL: "https://app-f8626.firebaseio.com",
    projectId: "app-f8626",
    storageBucket: "app-f8626.appspot.com",
    messagingSenderId: "805457834048",
    appId: "1:805457834048:web:6b0c59282bf8792edf2fa5",
    measurementId: "G-87NPXZDZ89"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();
</script>
</body>
</html>
```

## Sentry.io (로그 남기기)

![image](https://user-images.githubusercontent.com/62128942/90354206-84b4cb00-e083-11ea-8f92-79b7b617819d.png)

- sentry.io 회원가입
- Project 생성(Java)
- Settings - Project - 내프로젝트이름 - Client Keys(DSN) - DSN 확인하기
- Sentry 설정하는 법

```java
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

```

- Sentry 실행하는 법

```java
@Autowired
	private SentrySupport sentrySupport;

    //필요한곳에서
    sentrySupport.logSimpleMessage("프로덕트 쪽 오류");
```
