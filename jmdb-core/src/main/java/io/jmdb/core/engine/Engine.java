package io.jmdb.core.engine;

import io.jmdb.core.models.column.BooleanCol;
import io.jmdb.core.models.column.Column;
import io.jmdb.core.models.column.LongCol;
import io.jmdb.core.models.column.StringCol;
import io.jmdb.core.models.database.Database;
import io.jmdb.core.models.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dan Wiechert
 */
public class Engine {
	private final Database database;

	public Engine() {
		this(new Database(Database.DEFAULT_DATABASE_NAME));
	}

	public Engine(final Database database) {
		this.database = database;
	}

	public boolean createTable(final String tableName, final Map<String, String> columnMap) {
		final List<Column> columns = new ArrayList<>();

		for (final Map.Entry<String, String> column : columnMap.entrySet()) {
			switch (column.getValue()) {
				case "STRING":
					columns.add(new StringCol(column.getKey()));
					break;
				case "LONG":
					columns.add(new LongCol(column.getKey()));
					break;
				case "BOOLEAN":
					columns.add(new BooleanCol(column.getKey()));
					break;
				default:
					throw new IllegalArgumentException("Unknown column type: " + column.getValue());
			}
		}

		final Table table = new Table(tableName, columns);
		return database.addTable(table);
	}

	public boolean deleteTable(final String tableName) {
		return database.deleteTable(tableName);
	}

	public boolean insertValues(final String tableName, final byte[]... values) {
		// TODO: Null checks
		boolean success = true;

		final Table table = database.getTable(tableName);
		synchronized (table) {
			final List<Column> columns = table.getColumns();
			int index = 0;
			// TODO: Size checks
			for (final byte[] value : values) {
				// TODO: Validate data for each column
				success &= columns.get(index).add(value);
				index++;
			}
		}

		// TODO: Rollbacks
		return success;
	}
}
