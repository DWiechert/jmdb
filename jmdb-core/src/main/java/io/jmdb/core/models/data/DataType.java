package io.jmdb.core.models.data;

/**
 * @author Dan Wiechert
 */
public interface DataType<T> {
	T value();

	boolean equals(DataType other);
}
