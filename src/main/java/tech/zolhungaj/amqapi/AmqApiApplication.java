package tech.zolhungaj.amqapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.zolhungaj.amqapi.clientcommands.expandlibrary.ExpandLibraryGetQuestions;
import tech.zolhungaj.amqapi.clientcommands.friend.FriendRequestResponse;
import tech.zolhungaj.amqapi.clientcommands.onlineusers.GetAllOnlineUsersAndStartTrackingOnlineUsers;
import tech.zolhungaj.amqapi.clientcommands.onlineusers.StopTrackingOnlineUsers;
import tech.zolhungaj.amqapi.servercommands.social.FriendRequestReceived;
import tech.zolhungaj.amqapi.servercommands.gameroom.GameChatUpdate;
import tech.zolhungaj.amqapi.servercommands.globalstate.OnlinePlayerCountChange;

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
			if(command instanceof GameChatUpdate g){
				log.info("GameChatUpdate handled: {}", g);
				return true;
			}
			return false;
		});
		api.on(command -> {
			if(command instanceof OnlinePlayerCountChange o){
				log.info("OnlinePlayerCountChange handled: {}", o.count());
				return true;
			}
			return false;
		});
		api.on(command -> {
			if(command instanceof FriendRequestReceived frr){
				var response = FriendRequestResponse.builder()
						.target(frr.playerName())
						.accept(true)
						.build();
				api.sendCommand(response);
				return true;
			}
			return false;
		});
		api.once(command -> {
			if(command instanceof OnlinePlayerCountChange o){
				log.info("THIS ONLY HAPPENS ONCE {}", o.count());
				return true;
			}
			return false;
		});

		api.on(command -> {
			try{
				Path file = Path.of(command.getCommandName().replace(" ", "-").concat(".txt"));
				Files.writeString(file, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
				Files.writeString(file, command.toString(), StandardOpenOption.APPEND);
			}catch (IOException e){
				return false;
			}
			return true;
		});

		Thread apiThread = new Thread(api);
		apiThread.start();
		try{
			Thread.sleep(5_000);
//			api.sendCommand(new ExpandLibraryGetQuestions());
//			api.sendCommand(new GetAllOnlineUsersAndStartTrackingOnlineUsers());
			Thread.sleep(60_000);
//			api.sendCommand(new StopTrackingOnlineUsers());
		}catch (InterruptedException e){
			apiThread.interrupt();
			throw e;
		}
		apiThread.join();
		System.exit(0);
	}
}
