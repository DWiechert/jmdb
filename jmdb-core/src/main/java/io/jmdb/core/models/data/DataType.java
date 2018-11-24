package io.jmdb.core.models.data;

/**
 * @author Dan Wiechert
 */
public interface DataType<T> {
	Class<T> getClazz();

	T fromBytes(byte[] bytes);

	byte[] toBytes(T value);

	int length();
}
