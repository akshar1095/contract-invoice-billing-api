package com.aksharapatel.fastcontract.contractbillingapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="INVOICES")
@EntityListeners(AuditingEntityListener.class)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id", nullable = false, updatable = false)
    private Long invoiceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false, updatable = false)
    @LastModifiedDate
    private Date updateDate;

    @NotNull
    @Column(name="invoice_value", nullable = false)
    private Double invoiceValue;

    @NotNull
    @Column(name="invoice_Void", nullable = false)
    private Boolean invoiceVoid;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable = false)
    @JsonIgnore
    private Contract contract;

    public Invoice() {}

    public Long getInvoiceId() { return invoiceId; }

    public Double getInvoiceValue() { return invoiceValue; }

    public Date getCreateDate() { return createDate; }

    public Date getUpdateDate() { return updateDate; }

    public Boolean getInvoiceVoid() { return invoiceVoid; }

    public Contract getContract() { return contract; }

    public void setInvoiceValue(Double invoiceValue) { this.invoiceValue = invoiceValue; }

    public void setInvoiceVoid(Boolean invoiceVoid) { this.invoiceVoid = invoiceVoid; }

    public void setContract(Contract contract) { this.contract = contract; }
}
