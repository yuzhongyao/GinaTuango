package com.example.XingCheng.repositories;

import com.example.XingCheng.data.entities.Address;
import com.example.XingCheng.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
