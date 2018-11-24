package io.jmdb.core.models.column;

import io.jmdb.core.models.data.DataType;

/**
 * @author Dan Wiechert
 */
public interface Column<T> {
	DataType<T> getDataType();

	String name();
}
