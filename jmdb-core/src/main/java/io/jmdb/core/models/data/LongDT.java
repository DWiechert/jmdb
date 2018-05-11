package io.jmdb.core.models.data;

/**
 * @author Dan Wiechert
 */
public class LongDT implements DataType<Long> {
	private final long value;

	public LongDT(final long value) {
		this.value = value;
	}

	@Override
	public Long value() {
		return value;
	}

	@Override
	public boolean equals(final DataType other) {
		return false;
	}
}
