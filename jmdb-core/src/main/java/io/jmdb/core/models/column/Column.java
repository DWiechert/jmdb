package io.jmdb.core.models.column;

import io.jmdb.core.models.data.DataType;

import java.util.List;

/**
 * @author Dan Wiechert
 */
public interface Column<T> {
	DataType<T> getDataType();

	String name();

	byte[] get(int index);

	boolean add(byte[] data);

	List<Integer> indexOf(byte[] data);

	boolean delete(int index);

	int deleteAll(byte[] data);
}
