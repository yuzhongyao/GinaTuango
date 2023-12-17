package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "DELETE FROM images WHERE item_id = :itemParam ;",
    nativeQuery = true)
    void deleteImagesByItemId(@Param("itemParam") int id);

    @Query(value = "SELECT * FROM images WHERE item_id = :itemParam ;",nativeQuery = true)
    List<Image> getImagesByItemId(@Param("itemParam")int id);

}
