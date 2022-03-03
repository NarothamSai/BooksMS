package com.example.books.Publisher;

import com.example.books.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    Publisher save(Publisher publisher);

    Publisher findByName(String name);
}
