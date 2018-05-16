package io.jmdb.core.models.data;

/**
 * @author Dan Wiechert
 */
public class BooleanDataType implements DataType<Boolean> {
	private final byte[] TRUE = new byte[]{1};
	private final byte[] FALSE = new byte[]{0};

	@Override
	public Class<Boolean> getClazz() {
		return Boolean.class;
	}

	@Override
	public Boolean fromBytes(final byte[] bytes) {
		return bytes.length == 1 && bytes[0] == 1;
	}

	@Override
	public byte[] toBytes(final Boolean value) {
		return value ? TRUE : FALSE;
	}
}
