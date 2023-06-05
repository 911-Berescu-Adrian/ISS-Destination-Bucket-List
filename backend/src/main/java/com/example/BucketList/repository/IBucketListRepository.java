package com.example.BucketList.repository;

import com.example.BucketList.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBucketListRepository extends JpaRepository<Destination, Long> {
}
