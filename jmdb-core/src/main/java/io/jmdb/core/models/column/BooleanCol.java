package io.jmdb.core.models.column;

import io.jmdb.core.models.data.BooleanDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class BooleanCol extends BaseColumn {
	private final List<byte[]> values = new CopyOnWriteArrayList<>();

	@Override
	public byte[] get(final int index) {
		return values.get(index);
	}

	public BooleanCol(final String name) {
		super(name, new BooleanDataType());
	}

	@Override
	public boolean add(final byte[] data) {
		final byte[] value = validate(data);
		values.add(value);
		return true;
	}

	@Override
	public List<Integer> indexOf(final byte[] data) {
		final List<Integer> indexes = new ArrayList<>();
		final byte[] value = validate(data);
		int index = 0;
		for (final byte[] currentValue : values) {
			if (Arrays.equals(currentValue, value)) {
				indexes.add(index);
			}
			index++;
		}
		return indexes;
	}

	@Override
	public boolean delete(final int index) {
		values.remove(index);
		return true;
	}

	@Override
	public int deleteAll(final byte[] data) {
		final List<Integer> indexes = indexOf(data);
		synchronized (values) {
			// FIXME: This is wrong
			for (Integer index : indexes) {
				values.remove(index);
			}
		}
		return indexes.size();
	}

	private byte[] validate(final byte[] data) {
		// FIXME: Move validate to BaseColumn
		if (data.length != 1) {
			throw new IllegalArgumentException("Boolean data must be 1 byte in length.");
		}
		return data;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		final BooleanCol that = (BooleanCol) o;

		return values != null ? values.equals(that.values) : that.values == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (values != null ? values.hashCode() : 0);
		return result;
	}
}
