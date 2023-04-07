package com.example.backend.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the rep_rapports_x database table.
 * 
 */
@Entity
@Table(name = "rep_rapports_x",schema = "etl")
@NamedQuery(name = "RepRapportsX.findAll", query = "SELECT r FROM RepRapportsX r")
public class RepRapportsX implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "field_name")
	private String fieldName;

	@Column(name = "field_reporting")
	private String fieldReporting;

	private String table_rep;

	@Column(name = "tableref_field_query")
	private String tableref_field_query;

	@Column(name = "id_field")
	private Long idField;

	@Column(name = "tableref_field_appears")
	private String tableref_field_appears;

	private String operation;

	private String Filtre;

	private boolean is_join;

	private String col1;

	private String col2;

	private String table_join;

	private boolean isYcustfield;


	public boolean isIs_join() {
		return is_join;
	}

	public void setIs_join(boolean is_join) {
		this.is_join = is_join;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getTable_join() {
		return table_join;
	}

	public void setTable_join(String table_join) {
		this.table_join = table_join;
	}

	// bi-directional many-tno-one association to RepRapport
	@ManyToOne
	@JoinColumn(name = "id_rapport")
	private RepRapport repRapport;

	// bi-directional many-to-one association to RepRapportsY
	@OneToMany(mappedBy = "repRapportsX", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<RepRapportsY> repRapportsies;


	public String getTableref_field_query() {
		return tableref_field_query;
	}

	public void setTableref_field_query(String tableref_field_query) {
		this.tableref_field_query = tableref_field_query;
	}

	public String getTableref_field_appears() {
		return tableref_field_appears;
	}

	public void setTableref_field_appears(String tableref_field_appears) {
		this.tableref_field_appears = tableref_field_appears;
	}

	public RepRapportsX() {
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

	public RepRapport getRepRapport() {
		return this.repRapport;
	}

	public void setRepRapport(RepRapport repRapport) {
		this.repRapport = repRapport;
	}

	public List<RepRapportsY> getRepRapportsies() {
		return this.repRapportsies;
	}

	public void setRepRapportsies(List<RepRapportsY> repRapportsies) {
		this.repRapportsies = repRapportsies;
	}

	public RepRapportsY addRepRapportsy(RepRapportsY repRapportsy) {
		getRepRapportsies().add(repRapportsy);
		repRapportsy.setRepRapportsX(this);

		return repRapportsy;
	}

	public RepRapportsY removeRepRapportsy(RepRapportsY repRapportsy) {
		getRepRapportsies().remove(repRapportsy);
		repRapportsy.setRepRapportsX(null);

		return repRapportsy;
	}


	public String getTable_rep() {
		return table_rep;
	}

	public void setTable_rep(String table_rep) {
		this.table_rep = table_rep;
	}

	public boolean isYcustfield() {
		return isYcustfield;
	}

	public void setYcustfield(boolean isYcustfield) {
		this.isYcustfield = isYcustfield;
	}

}