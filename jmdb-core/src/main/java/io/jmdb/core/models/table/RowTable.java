package io.jmdb.core.models.table;

import io.jmdb.core.engine.row.Row;
import io.jmdb.core.models.column.Column;
import io.jmdb.core.models.data.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Wiechert
 */
public class RowTable {
	private static final int INDEX_NOT_FOUND = -1;

	private final String name;
	private final List<Column<?>> columns;
	private final List<Row> rows = new ArrayList<>();

	public RowTable(final String name, final List<Column<?>> columns) {
		this.name = name;
		this.columns = columns;
	}

	public String getName() {
		return name;
	}

	public List<List<DataType<?>>> getAllData() {
		final List<List<DataType<?>>> data = new ArrayList<>(rows.size());
		for (final Row row : rows) {
			data.add(row.getData());
		}
		return data;
	}

	// TODO: Add row, delete row
	private
}
