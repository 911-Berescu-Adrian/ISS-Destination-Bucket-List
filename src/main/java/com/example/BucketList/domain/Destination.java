package com.example.BucketList.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long destinationId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false, unique = true)
    private String image;

    @Column(nullable = false, unique = true)
    private String geolocation;

    @Column(unique = true)
    private String startDate;

    @Column(unique = true)
    private String endDate;
}