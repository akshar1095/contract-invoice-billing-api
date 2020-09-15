package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id", updatable = false, nullable = false)
    private Long invoiceId;

    @Column(name="invoice_value")
    private Double invoiceValue;

    @Column(name="create_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name="is_void", columnDefinition = "boolean default false")
    private Boolean isVoid;

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

    public Boolean getVoid() {
        return isVoid;
    }

    public void setVoid(Boolean aVoid) {
        isVoid = aVoid;
    }
}
