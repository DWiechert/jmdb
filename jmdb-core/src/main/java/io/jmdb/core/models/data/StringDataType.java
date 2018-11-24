package io.jmdb.core.models.data;

import java.nio.charset.StandardCharsets;

/**
 * @author Dan Wiechert
 */
public class StringDataType implements DataType<String> {
	private final int length;

	public StringDataType(final int length) {
		this.length = length;
	}

	@Override
	public Class<String> getClazz() {
		return String.class;
	}

	@Override
	public String fromBytes(final byte[] bytes) {
		return new String(bytes, StandardCharsets.UTF_8);
	}

	@Override
	public byte[] toBytes(final String value) {
		return value.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public int length() {
		return length;
	}
}
