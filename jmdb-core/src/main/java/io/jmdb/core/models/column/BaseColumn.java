package io.jmdb.core.models.column;

import io.jmdb.core.models.data.DataType;

/**
 * @author Dan Wiechert
 */
public abstract class BaseColumn implements Column {
	private final String name;
	private final DataType dataType;

	protected BaseColumn(final String name, final DataType dataType) {
		this.name = name;
		this.dataType = dataType;
	}

	@Override
	public DataType getDataType() {
		return dataType;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final BaseColumn that = (BaseColumn) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return dataType != null ? dataType.equals(that.dataType) : that.dataType == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "BaseColumn{" +
				"name='" + name + '\'' +
				", dataType=" + dataType +
				'}';
	}
}
