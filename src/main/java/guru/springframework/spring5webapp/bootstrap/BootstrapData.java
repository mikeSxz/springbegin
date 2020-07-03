package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData  implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","393654653651");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher pub1 = new Publisher();
        pub1.setName("SFG Publishing");
        pub1.setCity("St Petersburg");
        pub1.setState("FL");
        publisherRepository.save(pub1);
        pub1.getBooks().add(ddd);
        pub1.getBooks().add(noEJB);
        publisherRepository.save(pub1);

        noEJB.setPublisher(pub1);
        bookRepository.save(noEJB);
        ddd.setPublisher(pub1);
        bookRepository.save(ddd);


        System.out.println("Started in Bootstrap");

        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Number of Authors " + authorRepository.count());
        System.out.println("Number of publishers " + publisherRepository.count());
        System.out.println("Publisher Number of Books: " + pub1.getBooks().size());
    }
}
