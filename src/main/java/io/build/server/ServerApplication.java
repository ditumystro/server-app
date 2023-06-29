package io.build.server;

import io.build.server.enumeration.Status;
import io.build.server.model.Server;
import io.build.server.repo.serverRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}

	CommandLineRunner run(serverRepo serverRepo){

		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160",
					"Ubuntu Linux", "16 GB", "My PC",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP ));

			serverRepo.save(new Server(null, "192.168.1.10",
					"CentOs Linux", "6 GB", "docs",
					"http://localhost:8080/server/image/server2.png", Status.SERVER_UP ));

			serverRepo.save(new Server(null, "192.168.1.60",
					"Fedora Linux", "1 GB", "My docs",
					"http://localhost:8080/server/image/server3.png", Status.SERVER_UP ));

			serverRepo.save(new Server(null, "192.168.1.1",
					"Windows", "16 GB", "personnal",
					"http://localhost:8080/server/image/server4.png", Status.SERVER_UP ));


		};
	}
}
