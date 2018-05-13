package io.jmdb.core.models.column;

import java.util.List;

/**
 * @author Dan Wiechert
 */
public interface Column {
	enum ColumnType {
		BOOLEAN,
		BIGINT,
		STRING;
	}

	ColumnType getType();

	String name();

	boolean add(byte[] data);

	List<Integer> indexOf(byte[] data);

	boolean delete(int index);

	int deleteAll(byte[] data);
}
