import java.util.*;

public final class CommunityLSim {
	//instance variables
	ArrayList<Player> players;
	ArrayList<Integer> indices;
	Random random = new Random();
	//TODO: you will need to add more instance variables

	//Constructor
	public CommunityLSim(int numP) {
		//create the players
		this.players = new ArrayList<>();
		this.indices = new ArrayList<>();

		//generate a community of 30
		for (int i = 0; i < numP; i++) {
			if (i < numP / 2.0)
				this.players.add(new Player(PlayerKind.POORLY_PAID, (float) (99 + Math.random())));
			else
				this.players.add(new Player(PlayerKind.WELL_PAID, (float) (100.1 + Math.random())));
		}
	}

	public int getSize() {
		return this.players.size();
	}
	public Player getPlayer(int i) {
		return this.players.get(i);
	}

	public void addPocketChange(ArrayList<Player> lst) {
		for (Player person : lst) {
			if (person.getKind() == PlayerKind.WELL_PAID) {
				person.addMoney(0.1f);
			} else if (person.getKind() == PlayerKind.POORLY_PAID) {
				person.addMoney(0.03f);
			}
		}
	}

	private void reDoWhoPlays() {
		indices.clear();
		Object[] pp = randomUniqIndx((int) (players.size() * 0.3), 0, (int)(players.size() * 0.5));
		Object[] rp = randomUniqIndx((int) (players.size() * 0.2), (int)(players.size() * 0.5), players.size());

		for (Object o : pp) {
			indices.add((Integer) o);
		}
		for (Object o : rp) {
			indices.add((Integer) o);
		}
	}

	/*TODO generate some random indices for who might play the lottery
		in a given range of indices */
	public Object[] randomUniqIndx(int numI, int startRange, int endRange) {
		HashSet<Integer> set = new HashSet<>();
		int count = 0;
		while (count < numI) {
			if (set.add((int)(Math.random() * (endRange - startRange)) + startRange)) {
				count++;
			}
		}
		return set.toArray();
	}
	public void scholarship(float amount) {
		double rand = Math.random();
		int index;
		if (rand < 0.3) {
			index = (Integer)(randomUniqIndx(1, 0, (int)(players.size() * 0.5 + 0.5))[0]);
		} else {
			index = (Integer)(randomUniqIndx(1, (int)(players.size() * 0.5 + 0.5), players.size())[0]);
		}
		players.get(index).addMoney(amount);
	}

	public void simulateYears(int numYears) {
		/*now simulate lottery play for some years */
		float winnings;
		Player pl;
		int match;
		for (int year = 0; year < numYears; year++) {
			addPocketChange(players);
			reDoWhoPlays();
			// TODO: add code so that each member of the community who plays, plays
			//right now just everyone updates their list of funds each year
			Game lotto = new Game();
			for (int i : indices) {
				pl = players.get(i);
				pl.playRandom();
				match = lotto.numMatch(pl.getPlayerSet());
				winnings = lotto.winnings(match);
				pl.addMoney(winnings);
				if (Math.abs(1.0f + winnings) <= 0.0f) {
					scholarship(1.0f);
				}
			}
			for (Player p : players) {
				p.updateMoneyEachYear();
			}
		} //years
		moneyPrintout(numYears);
	}

	public float mostMoney(int year) {
		float money = players.get(0).getPastMoney(year);
		for (Player player : players) {
			if (player.getPastMoney(year) > money) {
				money = player.getPastMoney(year);
			}
		}
		return money;
	}

	public float leastMoney(int year) {
		float money = players.get(0).getPastMoney(year);
		for (Player player : players) {
			if (player.getPastMoney(year) < money) {
				money = player.getPastMoney(year);
			}
		}
		return money;
	}

	public void moneyPrintout(int numYears) {
		for (int i = 0; i < numYears; i++) {
			System.out.println("After year " + i + ":");
			System.out.println("The person with the most money has: " + mostMoney(i));
			System.out.println("The person with the least money has: " + leastMoney(i));
		}
	}
}
