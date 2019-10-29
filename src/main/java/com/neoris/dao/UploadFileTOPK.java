package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the UPLOAD_FILES database table.
 * 
 * @author marco.slehiman
 */
@Embeddable
public class UploadFileTOPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int id;

	@Column(name="FILE_NAME")
	private String fileName;

	public UploadFileTOPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UploadFileTOPK)) {
			return false;
		}
		UploadFileTOPK castOther = (UploadFileTOPK)other;
		return 
			(this.id == castOther.id)
			&& this.fileName.equals(castOther.fileName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.fileName.hashCode();
		
		return hash;
	}
}