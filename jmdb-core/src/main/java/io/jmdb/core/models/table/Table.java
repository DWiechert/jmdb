package io.jmdb.core.models.table;

import io.jmdb.core.models.column.Column;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class Table {
	private static final int INDEX_NOT_FOUND = -1;

	private final String name;
	private final List<Column> columns = new CopyOnWriteArrayList<>();

	public Table(final String name, final List<Column> columns) {
		this.name = name;
		this.columns.addAll(columns);
	}

	public String getName() {
		return name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getColumn(final String columnName) {
		final int index = columnIndex(columnName);
		if (index == INDEX_NOT_FOUND) {
			// TODO: Maybe change to Optional
			return null;
		}
		return columns.get(index);
	}

	private int columnIndex(final String columnName) {
		synchronized (columns) {
			int index = 0;
			for (Column column : columns) {
				if (columnName.equals(column.name())) {
					return index;
				}
				index++;
			}
		}
		return INDEX_NOT_FOUND;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Table table = (Table) o;

		if (name != null ? !name.equals(table.name) : table.name != null) return false;
		return columns != null ? columns.equals(table.columns) : table.columns == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (columns != null ? columns.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Table{" +
				"name='" + name + '\'' +
				", columns=" + columns +
				'}';
	}
}
