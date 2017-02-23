package pkgPokerBLL;

import java.util.UUID;

public class Player {

	private UUID PlayerID;
	private String PlayerName;
	
	public Player(String playerName) {
		super();
		PlayerID = UUID.randomUUID();
		PlayerName = playerName;
	}

}
