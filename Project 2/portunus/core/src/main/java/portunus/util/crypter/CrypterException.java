package portunus.util.crypter;

public abstract class CrypterException extends Exception {
	private static final long serialVersionUID = 1L;

	public CrypterException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
