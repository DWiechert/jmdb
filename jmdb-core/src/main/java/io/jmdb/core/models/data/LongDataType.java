package io.jmdb.core.models.data;

import com.google.common.primitives.Longs;

/**
 * @author Dan Wiechert
 */
public class LongDataType implements DataType<Long> {
	@Override
	public Class<Long> getClazz() {
		return Long.class;
	}

	@Override
	public Long fromBytes(final byte[] bytes) {
		return Longs.fromByteArray(bytes);
	}

	@Override
	public byte[] toBytes(final Long value) {
		return Longs.toByteArray(value);
	}
}
