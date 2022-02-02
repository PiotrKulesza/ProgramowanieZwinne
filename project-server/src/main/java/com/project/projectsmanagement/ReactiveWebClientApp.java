package com.project.projectsmanagement;

import org.springframework.web.reactive.function.client.WebClient;

import com.project.projectsmanagement.model.Lecturer;

import reactor.core.publisher.Mono;

public class ReactiveWebClientApp {
	private WebClient webClient;
	
	public ReactiveWebClientApp() {
		webClient = WebClient.create("http://localhost:8080");
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReactiveWebClientApp app = new ReactiveWebClientApp();

		 app.webClient
			.put()
			.uri("/updateLecturer/1?imie=Tomasz")
			.body(Mono.just(new Lecturer()), Lecturer.class)
			.retrieve()
			.bodyToMono(Lecturer.class)
			.thenMany(app.webClient
				.get()
				.uri("/lecturers")
				.retrieve()
				.bodyToFlux(Lecturer.class)
				.doOnNext(l -> {
					System.out.printf("Id: %d, Imiê: %s, Nazwisko: %s%n", l.getLecturerId(),l.getImie(), l.getNazwisko());
				}))
			.blockLast();
	}

}
