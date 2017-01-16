package test2;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class HelloWorldCommandFactory implements CommandFactory {

	@Override
	public Command createCommand(Message message, RequestHandler requestHandler) {
		System.out.println("MESSAGGIO: " + message.getText());
		return new HelloWorldCommand(message, requestHandler);
	}
}
