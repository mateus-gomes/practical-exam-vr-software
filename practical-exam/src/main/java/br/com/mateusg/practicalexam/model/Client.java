package br.com.mateusg.practicalexam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "client_id", nullable = false, length = 30)
    private Long clientId;

    @Column(name = "client_name", nullable = false, length = 80)
    private String clientName;

    @Column(name = "client_limit", nullable = false)
    private Double clientLimit;

    @Column(name = "invoice_due_date", nullable = false)
    private Byte invoiceDueDate;
}
