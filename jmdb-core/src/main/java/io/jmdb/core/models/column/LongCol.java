package io.jmdb.core.models.column;

import io.jmdb.core.models.data.LongDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class LongCol extends BaseColumn {
	private final List<byte[]> values = new CopyOnWriteArrayList<>();

	public LongCol(final String name) {
		super(name, new LongDataType());
	}

	@Override
	public byte[] get(final int index) {
		return values.get(index);
	}

	@Override
	public boolean add(final byte[] data) {
		values.add(data);
		return true;
	}

	@Override
	public List<Integer> indexOf(final byte[] data) {
		final List<Integer> indexes = new ArrayList<>();
		int index = 0;
		for (final byte[] currentValue : values) {
			if (Arrays.equals(currentValue, data)) {
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

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		final LongCol longCol = (LongCol) o;

		return values != null ? values.equals(longCol.values) : longCol.values == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (values != null ? values.hashCode() : 0);
		return result;
	}
}
