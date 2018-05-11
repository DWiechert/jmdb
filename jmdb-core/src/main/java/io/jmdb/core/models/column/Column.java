package io.jmdb.core.models.column;

/**
 * @author Dan Wiechert
 */
public interface Column<DataType> {
	String name();

	boolean add(DataType dataType);

	boolean delete(int index);

	int deleteAll(DataType dataType);
}
