package io.jmdb.core.models.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class BigintCol extends BaseColumn {
	private final List<byte[]> values = new CopyOnWriteArrayList<>();

	public BigintCol(final String name) {
		super(name, ColumnType.BIGINT);
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
}
