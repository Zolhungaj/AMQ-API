package tech.zolhungaj.amqapi;

import lombok.extern.slf4j.Slf4j;
import tech.zolhungaj.amqapi.servercommands.ErrorParsingCommand;
import tech.zolhungaj.amqapi.servercommands.NotImplementedCommand;
import tech.zolhungaj.amqapi.servercommands.NotStartedCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Slf4j
public class AmqApiExampleApplication {

	public static void main(String[] args) throws InterruptedException{
		if(args.length < 2){
			return;
		}
		String username = args[0];
		String password = args[1];
		boolean force = true;
		var api = new AmqApi(username, password, force);

		api.on(command -> {
			var fileExtension = ".json";
			if(command instanceof NotImplementedCommand notImplementedCommand) {
				var path = Path.of("UNIMPLEMENTED-" + notImplementedCommand.commandName().replace(" ", "-") + fileExtension);
				try{
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(path, notImplementedCommand.data().toString(4), StandardOpenOption.APPEND);
				}catch (IOException e){
					return false;
				}
			}else if (command instanceof ErrorParsingCommand errorParsingCommand){
				var path = Path.of("ERROR-" + errorParsingCommand.commandName().replace(" ", "-") + fileExtension);
				try{
					log.error("Something is wrong with the input data, writing to {} for inspection", path, errorParsingCommand.error());
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(path, errorParsingCommand.data().toString(4), StandardOpenOption.APPEND);
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND);
					Files.writeString(path, errorParsingCommand.error().toString(), StandardOpenOption.APPEND);
				}catch (IOException e){
					return false;
				}
			} else if (command instanceof NotStartedCommand notStartedCommand){
				log.info("""
                    Unknown command:
                        command: {}
                        data: {}
                    """, notStartedCommand.commandName(), notStartedCommand.data());
				var path = Path.of("UNINITIATED-" +notStartedCommand.commandName().replace(" ", "-") + fileExtension);
				try{
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(path, notStartedCommand.data().toString(4), StandardOpenOption.APPEND);
				}catch (IOException e){
					return false;
				}
			}
			return true;
		});

		Thread apiThread = new Thread(api);
		apiThread.start();
		apiThread.join();
		System.exit(0);
	}
}
