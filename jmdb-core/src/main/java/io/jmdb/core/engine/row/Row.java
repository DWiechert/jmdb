package io.jmdb.core.engine.row;

import io.jmdb.core.models.data.DataType;

import java.util.List;

/**
 * @author Dan Wiechert
 */
public interface Row {
	// TODO: Just have this be byte[]
	// Column information stays on the RowTable class
	List<DataType<?>> getData();

	List<DataType<?>> getData(String column);
}
