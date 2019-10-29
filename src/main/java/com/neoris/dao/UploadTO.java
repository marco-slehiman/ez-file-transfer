package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UPLOADS database table.
 * 
 */
@Entity
@Table(name="UPLOADS", schema = "APP")
@NamedQuery(name="UploadTO.findAll", query="SELECT u FROM UploadTO u")
public class UploadTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/** The email from. */
	@Column(name="EMAIL_FROM")
	private String emailFrom;

	/** The email to. */
	@Column(name="EMAIL_TO")
	private String emailTo;

	/** The uploaded date. */
	@Temporal(TemporalType.DATE)
	@Column(name="UPLOADED_DATE")
	private Date uploadedDate;

	/** The upload files. */
	//bi-directional many-to-one association to UploadFileTO
	@OneToMany(mappedBy="upload")
	private List<UploadFileTO> uploadFiles;

	/**
	 * Instantiates a new upload TO.
	 */
	public UploadTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the email from.
	 *
	 * @return the email from
	 */
	public String getEmailFrom() {
		return this.emailFrom;
	}

	/**
	 * Sets the email from.
	 *
	 * @param emailFrom the new email from
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * Gets the email to.
	 *
	 * @return the email to
	 */
	public String getEmailTo() {
		return this.emailTo;
	}

	/**
	 * Sets the email to.
	 *
	 * @param emailTo the new email to
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * Gets the uploaded date.
	 *
	 * @return the uploaded date
	 */
	public Date getUploadedDate() {
		return this.uploadedDate;
	}

	/**
	 * Sets the uploaded date.
	 *
	 * @param uploadedDate the new uploaded date
	 */
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	/**
	 * Gets the upload files.
	 *
	 * @return the upload files
	 */
	public List<UploadFileTO> getUploadFiles() {
		return this.uploadFiles;
	}

	/**
	 * Sets the upload files.
	 *
	 * @param uploadFiles the new upload files
	 */
	public void setUploadFiles(List<UploadFileTO> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	/**
	 * Adds the upload file.
	 *
	 * @param uploadFile the upload file
	 * @return the upload file TO
	 */
	public UploadFileTO addUploadFile(UploadFileTO uploadFile) {
		getUploadFiles().add(uploadFile);
		uploadFile.setUpload(this);

		return uploadFile;
	}

	/**
	 * Removes the upload file.
	 *
	 * @param uploadFile the upload file
	 * @return the upload file TO
	 */
	public UploadFileTO removeUploadFile(UploadFileTO uploadFile) {
		getUploadFiles().remove(uploadFile);
		uploadFile.setUpload(null);

		return uploadFile;
	}

}