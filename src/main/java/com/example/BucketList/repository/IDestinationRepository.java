package com.example.BucketList.repository;

import com.example.BucketList.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Long> {
}
