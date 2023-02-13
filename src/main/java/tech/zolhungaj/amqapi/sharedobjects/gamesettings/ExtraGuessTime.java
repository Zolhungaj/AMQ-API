package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

public final class ExtraGuessTime extends StaticValueOrRandomRange{
	public static final int DEFAULT_STANDARD_VALUE = 0;
	public static final int MIN = 0;
	public static final int MAX = 15;
	public static final List<Integer> DEFAULT_RANDOM_VALUE = List.of(MIN,MAX);
	public static final ExtraGuessTime DEFAULT = new ExtraGuessTime(DEFAULT_STANDARD_VALUE, DEFAULT_RANDOM_VALUE, false);

	public ExtraGuessTime(int value, List<Integer> randomRange, boolean randomOn) {
		super(value, randomRange, randomOn);
	}

	public ExtraGuessTime(Range range) {
		super(range);
	}

	public ExtraGuessTime(int start, int end) {
		super(start, end);
	}

	public ExtraGuessTime(int value) {
		super(value);
	}

	@Override
	protected int defaultStandardValue() {
		return DEFAULT_STANDARD_VALUE;
	}

	@Override
	protected List<Integer> defaultRandomRange() {
		return DEFAULT_RANDOM_VALUE;
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