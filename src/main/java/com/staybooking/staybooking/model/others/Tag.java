package com.staybooking.staybooking.model.others;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Image image;
    private String name;
    private boolean deleted;
}
