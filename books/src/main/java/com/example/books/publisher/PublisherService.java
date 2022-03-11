package com.example.books.publisher;

import com.example.books.models.Publisher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher create(String name){
        Publisher publisher = publisherRepository.findByName(name);
        if(publisher == null){
            Publisher newPublisher = new Publisher();
            newPublisher.setName(name);

            publisher = this.publisherRepository.save(newPublisher);
        }
        return  publisher;
    }
}
