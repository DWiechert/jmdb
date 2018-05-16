package io.jmdb.core.models.column;

import io.jmdb.core.models.data.StringDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class StringCol extends BaseColumn {
	// FIXME: Have a Map<String, Integer> for compression
	// FIXME: values should be List<Integer> which maps to compression map
	private final List<byte[]> values = new CopyOnWriteArrayList<>();

	public StringCol(final String name) {
		super(name, new StringDataType());
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

		final StringCol stringCol = (StringCol) o;

		return values != null ? values.equals(stringCol.values) : stringCol.values == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (values != null ? values.hashCode() : 0);
		return result;
	}
}
