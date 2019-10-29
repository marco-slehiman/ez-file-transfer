package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UPLOAD_FILES database table.
 *
 * @author marco.slehiman
 */
@Entity
@Table(name="UPLOAD_FILES", schema = "APP")
@NamedQuery(name="UploadFileTO.findAll", query="SELECT u FROM UploadFileTO u")
public class UploadFileTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private UploadFileTOPK id;

	/** The size. */
	private int size;

	/** The upload. */
	//bi-directional many-to-one association to UploadTO
	@ManyToOne
	@JoinColumn(name="ID")
	private UploadTO upload;

	/**
	 * Instantiates a new upload file TO.
	 */
	public UploadFileTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public UploadFileTOPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(UploadFileTOPK id) {
		this.id = id;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the upload.
	 *
	 * @return the upload
	 */
	public UploadTO getUpload() {
		return this.upload;
	}

	/**
	 * Sets the upload.
	 *
	 * @param upload the new upload
	 */
	public void setUpload(UploadTO upload) {
		this.upload = upload;
	}

}