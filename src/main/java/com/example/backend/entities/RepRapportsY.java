package com.example.backend.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the rep_rapports_y database table.
 * 
 */
@Entity
@Table(name="rep_rapports_y",schema = "etl")
@NamedQuery(name="RepRapportsY.findAll", query="SELECT r FROM RepRapportsY r")
public class RepRapportsY implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="field_name")
	private String fieldName;

	@Column(name="field_reporting")
	private String fieldReporting;

	@Column(name="id_field")
	private Long idField;

	private String operation;

	 

	//bi-directional many-to-one association to RepRapportsX
	@ManyToOne
	@JoinColumn(name="id_x")
	private RepRapportsX repRapportsX;

	public RepRapportsY() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldReporting() {
		return this.fieldReporting;
	}

	public void setFieldReporting(String fieldReporting) {
		this.fieldReporting = fieldReporting;
	}

	public Long getIdField() {
		return this.idField;
	}

	public void setIdField(Long idField) {
		this.idField = idField;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}


	public RepRapportsX getRepRapportsX() {
		return this.repRapportsX;
	}

	public void setRepRapportsX(RepRapportsX repRapportsX) {
		this.repRapportsX = repRapportsX;
	}

}