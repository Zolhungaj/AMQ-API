package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

public final class InventorySize extends StaticValueOrRandomRange{
	public static final int DEFAULT_STANDARD_VALUE = 20;
	public static final int MIN = 1;
	public static final int MAX = 99;
	public static final List<Integer> DEFAULT_RANDOM_RANGE = List.of(MIN,MAX);
	public static final InventorySize DEFAULT = new InventorySize(DEFAULT_STANDARD_VALUE, DEFAULT_RANDOM_RANGE, false);
	public InventorySize(int value, List<Integer> randomRange, boolean randomOn) {
		super(value, randomRange, randomOn);
	}

	public InventorySize(Range range) {
		super(range);
	}

	public InventorySize(int start, int end) {
		super(start, end);
	}

	public InventorySize(int value) {
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