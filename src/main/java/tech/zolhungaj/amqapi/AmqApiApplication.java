package tech.zolhungaj.amqapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.zolhungaj.amqapi.servercommands.NotImplementedCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Slf4j
@SpringBootApplication
public class AmqApiApplication implements ApplicationRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AmqApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws InterruptedException{
		String username = args.getOptionValues("username").get(0);
		String password = args.getOptionValues("password").get(0);
		boolean force = args.getOptionValues("force") != null;
		AmqApi api = new AmqApi(username, password, force);

		api.on(command -> {
			if(command instanceof NotImplementedCommand) {
				try{
					Path file = Path.of(command.getCommandName().replace(" ", "-").concat(".txt"));
					Files.writeString(file, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(file, command.toString(), StandardOpenOption.APPEND);
				}catch (IOException e){
					return false;
				}
			}
			return true;
		});

		Thread apiThread = new Thread(api);
		apiThread.start();
		try{
			Thread.sleep(5_000);
			Thread.sleep(60_000);
		}catch (InterruptedException e){
			apiThread.interrupt();
			throw e;
		}
		apiThread.join();
		System.exit(0);
	}
}
