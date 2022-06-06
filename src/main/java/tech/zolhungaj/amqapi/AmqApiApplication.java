package tech.zolhungaj.amqapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.zolhungaj.amqapi.commands.GameChatUpdate;

@SpringBootApplication
public class AmqApiApplication implements ApplicationRunner {
	private static final Logger LOG = LoggerFactory.getLogger(AmqApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AmqApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws InterruptedException{
		String username = args.getOptionValues("username").get(0);
		String password = args.getOptionValues("password").get(0);
		boolean force = args.getOptionValues("force") != null;
		AmqApi api = new AmqApi(username, password, force);
		api.on((EventHandler<GameChatUpdate>) event -> {
			LOG.info("GameChatUpdate handled: {}", event);
			return true;
		});
		Thread apiThread = new Thread(api);
		apiThread.start();
		try{
			Thread.sleep(60_000);
		}catch (InterruptedException e){
			apiThread.interrupt();
			throw e;
		}
		apiThread.join();
		System.exit(0);
	}
}
