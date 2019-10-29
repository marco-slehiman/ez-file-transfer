package com.neoris.websockets.filetransfer;

// TODO: Auto-generated Javadoc
/**
 * The Class Operation.
 */
public class Operation {

	/** The op. */
	private String op;
	
	/** The fname. */
	private String fname;
	
	/** The fsize. */
	private long fsize;
	
	/** The emailfrom. */
	private String emailfrom;
	
	/** The emailto. */
	private String emailto;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder = builder.append("operation: " + this.op).append("\n");
		builder = builder.append("file name: " + this.fname).append("\n");
		builder = builder.append("file size: " + this.fsize).append("\n");
		return builder.toString();
	}

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public String getOperation() {
		return op;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fname;
	}

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public long getFileSize() {
		return fsize;
	}

	/**
	 * Gets the email from.
	 *
	 * @return the email from
	 */
	public String getEmailFrom() {
		return emailfrom;
	}

	/**
	 * Gets the email TO.
	 *
	 * @return the email TO
	 */
	public String getEmailTO() {
		return this.emailto;
	}

}
