package jpabook.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column("MEMBER_ID")
	private Long memberId;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date orderDate;

	@Enumerated(value = EnumType.STRING)
	private OrderStatus status;

}

