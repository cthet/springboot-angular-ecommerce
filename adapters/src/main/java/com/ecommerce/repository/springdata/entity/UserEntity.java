package com.ecommerce.repository.springdata.entity;

import com.ecommerce.util.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "civility_id", referencedColumnName = "id")
    private CivilityEntity civilityEntity;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private CartEntity cartEntity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresseEntityList = new ArrayList<>();


    public List<OrderEntity> getOrders() {
        return Collections.unmodifiableList(orderEntityList);
    }

    public List<AddressEntity> getAddresseEntityList() {
        return Collections.unmodifiableList(addresseEntityList);
    }

    public void addOrder(OrderEntity orderEntity){
        if(orderEntity != null) {
            this.orderEntityList.add(orderEntity);
            orderEntity.setUserEntity(this);
        }
    }

    public void addAddress(AddressEntity address){
        if(address != null) {
            this.addresseEntityList.add(address);
            address.setUserEntity(this);
        }
    }

}
