package io.jmdb.core.models.data;

/**
 * @author Dan Wiechert
 */
public class BooleanDT implements DataType<Boolean> {
	private final boolean value;

	public BooleanDT(final boolean value) {
		this.value = value;
	}

	@Override
	public Boolean value() {
		return value;
	}

	@Override
	public boolean equals(final DataType other) {
		return false;
	}
}
