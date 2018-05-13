package io.jmdb.core.models.table;

import io.jmdb.core.models.column.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Wiechert
 */
public class SearchResults {
	// This needs to be something which handles:
	// - List of column names
	// - List of column types
	// - List of rows
	// -- Each row has values for each column

	private final List<String> columnNames = new ArrayList<>();
	private final List<ResultRow> values = new ArrayList<>();


	public class ResultRow {
		private final Column.ColumnType columnType;
		private final byte[] value;

		public ResultRow(final Column.ColumnType columnType, final byte[] value) {
			this.columnType = columnType;
			this.value = value;
		}
	}
}
