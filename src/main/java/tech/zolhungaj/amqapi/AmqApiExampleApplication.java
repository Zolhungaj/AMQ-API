package tech.zolhungaj.amqapi;

import lombok.extern.slf4j.Slf4j;
import tech.zolhungaj.amqapi.clientcommands.expandlibrary.LoadExpandLibraryAndStartListeningForChanges;
import tech.zolhungaj.amqapi.clientcommands.lobby.Kick;
import tech.zolhungaj.amqapi.clientcommands.lobby.SendPublicChatMessage;
import tech.zolhungaj.amqapi.clientcommands.lobby.StartGame;
import tech.zolhungaj.amqapi.clientcommands.roombrowser.HostMultiplayerRoom;
import tech.zolhungaj.amqapi.clientcommands.social.GetProfile;
import tech.zolhungaj.amqapi.clientcommands.social.RemoveFriend;
import tech.zolhungaj.amqapi.clientcommands.social.SendFriendRequest;
import tech.zolhungaj.amqapi.servercommands.ErrorParsingCommand;
import tech.zolhungaj.amqapi.servercommands.UnregisteredCommand;
import tech.zolhungaj.amqapi.servercommands.expandlibrary.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.game.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.*;
import tech.zolhungaj.amqapi.servercommands.globalstate.*;
import tech.zolhungaj.amqapi.servercommands.social.*;
import tech.zolhungaj.amqapi.servercommands.store.TicketRollResult;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
public class AmqApiExampleApplication {
	private static final String OTHER_ACCOUNT = "Zolhungaj";

	private static final List<Class<?>> registerList = List.of(
			ExpandLibraryEntryList.class,
			ExpandLibraryEntryUpdated.class,
			AnswerResults.class,
			AnswerReveal.class,
			GameStarting.class,
			GuessPhaseOver.class,
			NextVideoInfo.class,
			PlayerRejoin.class,
			PlayersAnswered.class,
			PlayNextSong.class,
			QuizEndResult.class,
			QuizFatalError.class,
			QuizNoSongs.class,
			QuizOver.class,
			QuizReady.class,
			QuizSkipMessage.class,
			SongFeedbackRequest.class,
			WaitingForBuffering.class,
			GameHosted.class,
			NewPlayer.class,
			PlayerChangedAvatar.class,
			PlayerChangedToSpectator.class,
			PlayerReadyChange.class,
			SpectatorChangedToPlayer.class,
			GameChatMessage.class,
			GameChatSystemMessage.class,
			GameChatUpdate.class,
			PlayerLeft.class,
			SpectatorJoined.class,
			SpectatorLeft.class,
			Alert.class,
			AllOnlineUsers.class,
			AvatarDriveUpdate.class,
			FileServerStatus.class,
			ForcedLogoff.class,
			FriendAdded.class,
			FriendNameChange.class,
			FriendOnlineChange.class,
			FriendProfileImageChange.class,
			HtmlAlert.class,
			LoginComplete.class,
			NewDonation.class,
			NewQuestEvents.class,
			OnlinePlayerCountChange.class,
			OnlineUserChange.class,
			PopoutMessage.class,
			RankedChampionsUpdate.class,
			RankedGameStateChanged.class,
			RankedLeaderboardUpdate.class,
			RankedScoreUpdate.class,
			SelfNameChange.class,
			ServerRestartWarning.class,
			ServerUnknownError.class,
			DirectMessage.class,
			DirectMessageResponse.class,
			FriendRemoved.class,
			FriendRequestReceived.class,
			FriendRequestResponse.class,
			FriendSocialStatusUpdate.class,
			GameInvite.class,
			PlayerProfile.class,
			TicketRollResult.class,
			PlayerJoinedQueue.class,
			PlayerLeftQueue.class,
			DirectMessageAlert.class,
			SkipVotePassed.class,
			QuizSkippingToNextPhase.class,
			QuizPaused.class,
			QuizResumed.class
	);


	public static void main(String[] args) throws InterruptedException{
		if(args.length < 2){
			return;
		}
		String username = args[0];
		String password = args[1];
		boolean force = true;
		var api = new AmqApi(username, password, force);
		registerList.forEach(api::registerCommand);
		api.onAllCommands(command -> {
			var fileExtension = ".json";
			if (command instanceof ErrorParsingCommand errorParsingCommand){
				var path = Path.of("ERROR-" + errorParsingCommand.commandName().replace(" ", "-") + fileExtension);
				try{
					log.error("Something is wrong with the input data, writing to {} for inspection", path, errorParsingCommand.error());
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(path, errorParsingCommand.data().toString(4), StandardOpenOption.APPEND);
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND);
					Files.writeString(path, errorParsingCommand.error().toString(), StandardOpenOption.APPEND);
				}catch (IOException e){
					log.error("ERROR file write error", e);
				}
			} else if (command instanceof UnregisteredCommand unregisteredCommand){
				log.info("""
                    Unregistered command:
                        command: {}
                        data: {}
                    """, unregisteredCommand.commandName(), unregisteredCommand.data());
				var path = Path.of("UNREGISTERED-" + unregisteredCommand.commandName().replace(" ", "-") + fileExtension);
				try{
					Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					Files.writeString(path, unregisteredCommand.data().toString(4), StandardOpenOption.APPEND);
				}catch (IOException e){
					log.error("UNREGISTERED file write error", e);
				}
			}
		});
		api.on(ExpandLibraryEntryList.class, expandLibraryEntryList -> log.info("{}", expandLibraryEntryList));
		Consumer<GameChatMessage> gcmConsumer = message -> {
			if(!message.sender().equals(username)){
				api.sendCommand(new SendPublicChatMessage("Yo"));
			}
			log.info("{}", message);
		};
		Consumer<GameChatUpdate> gcuConsumer = message -> message.messages().forEach(gcmConsumer);
		api.on(GameChatMessage.class, gcmConsumer);
		api.on(GameChatUpdate.class, gcuConsumer);
		Predicate<GameChatMessage> gcmPredicate = message -> {
			if(message.message().equals("Predicate")){
				api.sendCommand(new SendPublicChatMessage("Predicates work"));
				return true;
			}
			return false;
		};
		Predicate<GameChatUpdate> gcuPredicate = message -> message.messages().stream().anyMatch(gcmPredicate);
		api.once(GameChatMessage.class, gcmPredicate);
		api.once(GameChatUpdate.class, gcuPredicate);
		SendPublicChatMessage message = new SendPublicChatMessage("Hello World");
		Kick kick = new Kick(OTHER_ACCOUNT);
		log.info("{}", message);
		log.info("{}", kick);
		Thread apiThread = new Thread(api);
		apiThread.start();
		Thread.sleep(5000);
		api.sendCommand(new LoadExpandLibraryAndStartListeningForChanges());
		Thread.sleep(5000);
		api.sendCommand(new RemoveFriend(OTHER_ACCOUNT));
		Thread.sleep(2000);
		api.sendCommand(new SendFriendRequest(OTHER_ACCOUNT));
		Thread.sleep(2000);
		api.sendCommand(new HostMultiplayerRoom(
				GameSettings.DEFAULT.toBuilder()
						.songSelection(SongSelection.of(2, 1, 2, 5))
						.songTypeSelection(SongTypeSelection.of(5, SongTypeSelection.SongType.ALL))
						.numberOfSongs(5)
						.build()
		));
		Thread.sleep(5000);
		api.sendCommand(new StartGame());
		Thread.sleep(10000);
		api.sendCommand(message);
		Thread.sleep(5000);
		api.sendCommand(kick);
		api.sendCommand(new GetProfile(OTHER_ACCOUNT));
		api.sendCommand(new GetProfile("HermesBOT"));
		apiThread.join();
		System.exit(0);
	}
}
