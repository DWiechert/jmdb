package io.jmdb.core.models.column;

/**
 * @author Dan Wiechert
 */
public abstract class BaseColumn implements Column {
	private final String name;
	private final ColumnType columnType;

	protected BaseColumn(final String name, final ColumnType columnType) {
		this.name = name;
		this.columnType = columnType;
	}

	@Override
	public ColumnType getType() {
		return columnType;
	}

	@Override
	public String name() {
		return name;
	}
}
