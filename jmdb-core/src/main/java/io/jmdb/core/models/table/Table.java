package io.jmdb.core.models.table;

import io.jmdb.core.models.column.Column;

import java.util.List;

/**
 * @author Dan Wiechert
 */
public class Table {
	private final String name;
	private final List<Column> columns;

	public Table(final String name, final List<Column> columns) {
		this.name = name;
		this.columns = columns;
	}


}
