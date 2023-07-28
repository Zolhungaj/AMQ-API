package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

public final class GuessTime extends StaticValueOrRandomRange{
	public static final int DEFAULT_STANDARD_VALUE = 20;
	public static final int MIN = 1;
	public static final int MAX = 60;
	public static final List<Integer> DEFAULT_RANDOM_RANGE = List.of(MIN,MAX);
	public static final GuessTime DEFAULT = new GuessTime(DEFAULT_STANDARD_VALUE, DEFAULT_RANDOM_RANGE, false);

	public GuessTime(int value, List<Integer> randomRange, boolean randomOn) {
		super(value, randomRange, randomOn);
	}

	public GuessTime(Range range) {
		super(range);
	}

	public GuessTime(int start, int end) {
		super(start, end);
	}

	public GuessTime(int value) {
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