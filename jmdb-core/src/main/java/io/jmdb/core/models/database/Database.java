package io.jmdb.core.models.database;

import io.jmdb.core.models.table.Table;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dan Wiechert
 */
public class Database {
	private static final int INDEX_NOT_FOUND = -1;
	public static final String DEFAULT_DATABASE_NAME = "default";

	private final String name;
	private final List<Table> tables = new CopyOnWriteArrayList<>();

	public Database(final String name) {
		this.name = name;
	}

	public boolean addTable(final Table table) {
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

	public Table getTable(final String tableName) {
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
			for (Table table : tables) {
				if (tableName.equals(table.getName())) {
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

		final Database database = (Database) o;

		if (name != null ? !name.equals(database.name) : database.name != null) return false;
		return tables != null ? tables.equals(database.tables) : database.tables == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (tables != null ? tables.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Database{" +
				"name='" + name + '\'' +
				", tables=" + tables +
				'}';
	}
}
