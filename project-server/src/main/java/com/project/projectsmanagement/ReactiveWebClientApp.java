package com.project.projectsmanagement;

import java.util.concurrent.TimeUnit;

import org.springframework.web.reactive.function.client.WebClient;

import com.project.projectsmanagement.model.Student;

import reactor.core.publisher.Mono;

public class ReactiveWebClientApp {
	private WebClient webClient;
	
	public ReactiveWebClientApp() {
		webClient = WebClient.create("http://localhost:8080");
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReactiveWebClientApp app = new ReactiveWebClientApp();
		
		Student student = new Student(null, "Jan", "Nazwisko", "100000", true, null);
//		
//		 app.webClient
//			.post()
//			.uri("/students")
//			.body(Mono.just(student), Student.class)
//			.retrieve()
//			.bodyToMono(Student.class)
//			.subscribe();
//		
		 app.webClient
			.get()
			.uri("/students")
			.retrieve()
			.bodyToFlux(Student.class)
			.doOnNext(s -> {
				System.out.printf("Id: %d, Imiê: %s, Nazwisko: %s%n", s.getStudentId(), s.getImie(), s.getNazwisko());
			})
			.blockLast();
		
		TimeUnit.SECONDS.sleep(5);
	}

}
