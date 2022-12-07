import java.util.*;

class Player {
	//instance variables
	private PlayerKind kind;
	private float money;
	private ArrayList<Float> moneyOverTime;
	private HashSet<Integer> lottoNums;
	Random random = new Random();
	private int red, green, blue;

	//constructor
	public Player(PlayerKind pK, float startFunds) {
		kind = pK;
		money = startFunds;
		moneyOverTime = new ArrayList<Float>();
		moneyOverTime.add(startFunds);
		red = random.nextInt(100);
		green = random.nextInt(100);
		blue = random.nextInt(100);
		lottoNums = new HashSet<Integer>();
		lottoNums.add(0);

		//overall blue tint to POORLY_PAID
		if (kind == PlayerKind.WELL_PAID) {
			red += 100;
		} else {
			blue += 100;
		}
	}

	public int getR() { return red; }
	public int getG() { return green; }
	public int getB() { return blue; }
	public float getMoney() { return money; }
	public PlayerKind getKind() { return kind; }
	public ArrayList<Float> getFunds() { return moneyOverTime; }
	public float getPastMoney(int year) {
		if (year >= 0 && year < moneyOverTime.size()) {
			return moneyOverTime.get(year);
		}
		return 0.0f;
	}
	public void addMoney(float money) {
		this.money += money;
	}
	public HashSet<Integer> playRandom() {
		lottoNums.clear();
		while(lottoNums.size() < 5) {
			lottoNums.add(random.nextInt(1, 42));
		}
		return lottoNums;
	}
	public HashSet<Integer> getPlayerSet() {
		return lottoNums;
	}
	public void updateMoneyEachYear() {
		this.moneyOverTime.add(this.money);
	}
}
