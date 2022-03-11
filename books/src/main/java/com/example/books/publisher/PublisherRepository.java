package com.example.books.publisher;

import com.example.books.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    Publisher save(Publisher publisher);

    Publisher findByName(String name);
}
