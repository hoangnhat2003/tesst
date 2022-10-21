package com.codingboot.Core.domain.entity;

import javax.persistence.*;
import com.codingboot.Core.domain.enums.ERole;
//import com.codingboot.Auth.domain.utils.PostgresEnumType;
import lombok.*;

@Entity
@Table(name = "tbl_roles")
@Data
//@TypeDef(
//        name = "e_roles",
//        typeClass = PostgresEnumType.class
//)
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
//    @Type(type = "e_roles")
    private ERole name;
}
