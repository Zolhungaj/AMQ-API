package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

public final class LootingTime extends StaticValueOrRandomRange{

	public static final int DEFAULT_STANDARD_VALUE = 90;
	public static final int MIN = 10;
	public static final int MAX = 150;
	public static final List<Integer> DEFAULT_RANDOM_RANGE = List.of(MIN,MAX);
	public static final LootingTime DEFAULT = new LootingTime(DEFAULT_STANDARD_VALUE, DEFAULT_RANDOM_RANGE, false);
	public LootingTime(int value, List<Integer> randomRange, boolean randomOn) {
		super(value, randomRange, randomOn);
	}

	public LootingTime(Range range) {
		super(range);
	}

	public LootingTime(int start, int end) {
		super(start, end);
	}

	public LootingTime(int value) {
		super(value);
	}

	@Override
	protected int defaultStandardValue() {
		return DEFAULT_STANDARD_VALUE;
	}

	@Override
	protected List<Integer> defaultRandomRange() {
		return DEFAULT_RANDOM_RANGE;
	}

	@Override
	protected int min() {
		return MIN;
	}

	@Override
	protected int max() {
		return MAX;
	}
}