package io.jmdb.core.models.database;

import io.jmdb.core.models.table.RowTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Wiechert
 */
public class RowDatabase {
	private static final int INDEX_NOT_FOUND = -1;
	private final String name;
	private final List<RowTable> tables = new ArrayList<>();

	public RowDatabase(final String name) {
		this.name = name;
	}

	public boolean addTable(final RowTable table) {
		final int index = tableIndex(table.getName());
		if (index != INDEX_NOT_FOUND) {
			return false;
		}
		return tables.add(table);
	}

	public boolean deleteTable(final String tableName) {
		final int index = tableIndex(tableName);
		if (index == INDEX_NOT_FOUND) {
			return false;
		}
		tables.remove(index);
		return true;
	}


	public RowTable getTable(final String tableName) {
		final int index = tableIndex(tableName);
		if (index == INDEX_NOT_FOUND) {
			// TODO: Maybe change to Optional
			return null;
		}
		return tables.get(index);
	}

	private int tableIndex(final String tableName) {
		synchronized (tables) {
			int index = 0;
			for (RowTable table : tables) {
				if (tableName.equals(table.getName())) {
					return index;
				}
				index++;
			}
		}
		return INDEX_NOT_FOUND;
	}
}
