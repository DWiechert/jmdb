package io.jmdb.core.models.column;

import io.jmdb.core.models.data.BooleanDT;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Wiechert
 */
public class BooleanCol implements Column<BooleanDT> {
	private final String name;
	private final List<Boolean> values = new ArrayList<>();

	public BooleanCol(final String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public boolean add(final BooleanDT booleanDT) {
		return false;
	}

	public boolean delete(final int index) {
		return values.remove(index);
	}

	public int deleteAll(final BooleanDT booleanDT) {
		int count = 0;

		return count;
	}
}
