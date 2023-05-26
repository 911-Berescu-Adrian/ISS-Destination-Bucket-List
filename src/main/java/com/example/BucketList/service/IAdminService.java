package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;

public interface IAdminService {
    Destination saveDestination(Destination destination);
    //    List<Car> fetchCarListForDealershipID(Long dealershipID);
//    Car one(Long dealershipID, Long carID);
//
//    List<CarDealershipIDDTO> getAllCars();
//    CarDealershipDTO getOneCarWithDealershipObject(Long carID);
//    //update
//    Car updateCar(Car car, Long carID, Long dealershipID);
//    //delete
//    void deleteCar(Long carID, Long dealershipID);
}
