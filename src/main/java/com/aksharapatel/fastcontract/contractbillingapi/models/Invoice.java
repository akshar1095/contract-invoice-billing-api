package com.aksharapatel.fastcontract.contractbillingapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="INVOICES")
@EntityListeners(AuditingEntityListener.class)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id", updatable = false, nullable = false)
    private Long invoiceId;

    @Column(name="invoice_value")
    private Double invoiceValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    private Date updateDate;

    @Column(name="invoice_Void")
    private Boolean invoiceVoid;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable = false)
    @JsonIgnore
    private Contract contract;

    public Invoice() {}

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(Double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() { return updateDate; }

    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }

    public Boolean getInvoiceVoid() {
        return invoiceVoid;
    }

    public void setInvoiceVoid(Boolean aVoid) {
        invoiceVoid = aVoid;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
