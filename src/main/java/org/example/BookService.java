package org.example;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Random;

public class BookService {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = factory.createEntityManager();

        String[] names = {
                "Иван Иванов",
                "Петр Петров",
                "Анна Сидорова",
                "Мария Павлова",
                "Александр Козлов",
                "Елена Николаева",
                "Дмитрий Смирнов",
                "Светлана Иванова",
                "Андрей Петров",
                "Ольга Морозова"
        };

//        addAuthors(entityManager, names);

        String[] bookTitles = {
                "Война и мир",
                "Преступление и наказание",
                "Мастер и Маргарита",
                "1984",
                "Унесенные ветром",
                "Три товарища",
                "Анна Каренина",
                "Тень горы",
                "Маленький принц",
                "Гарри Поттер и философский камень",
                "Лолита",
                "Над пропастью во ржи",
                "Алиса в стране чудес",
                "Дневник Анны Франк",
                "Тонкая красная линия",
                "Белый клык",
                "Властелин колец",
                "Дон Кихот",
                "Гордость и предубеждение",
                "Цветы для Элджернона",
                "Капитанская дочка",
                "О дивный новый мир",
                "Марсианин",
                "Туманность Андромеды",
                "Сто лет одиночества",
                "Магическая гора",
                "Зелёная миля",
                "Сумерки",
                "Алхимик",
                "Дракула"
        };

        String[] additionalBookTitles = {
                "Магнитные бури",
                "Метро 2033",
                "Айвенго",
                "Сила подсознания",
                "Американский психопат",
                "Мастер Гарри",
                "Алхимик",
                "Ночной дозор",
                "Триумфальная арка",
                "Цвет волшебства",
                "Космическая одиссея 2001 года",
                "Божественная комедия",
                "Лавка забытых исцелений",
                "Терри Пратчетт",
                "Джек Лондон"
        };

//        addBooks(entityManager, additionalBookTitles);

//        Author author1 = entityManager.find(Author.class, 8);
//        transaction.begin();
//        entityManager.remove(author1);
//        transaction.commit();



//        List<Author> authors = getAllAuthors(entityManager);
//        for (Author author : authors) {
//            System.out.println(author);
//            List<Book> books = author.getBooks();
//            int count = 1;
//            for (Book book : books) {
//                System.out.println(count++ + ") " + book);
//            }
//        }




        Book book = new Book();


        book.setName1("dbzkjkcxbckjvbczxkvdxv");

        Book book1 = new Book();

        book1.setName1("zdbfjkdbfjkdbfjk");

        Book book2 = new Book();

        book2.setName1("HOW do you do it???");
//
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
//        Book book = entityManager.find(Book.class, 30);
        entityManager.persist(book);
        entityManager.persist(book1);
        entityManager.persist(book2);
//        entityManager.remove(book);
        transaction.commit();
//
//        List<Book> books1 = getBooksCriteria(entityManager);
//            for (Book book : books1) {
//                System.out.println(book);
//                System.out.println("book.getAuthor() = " + book.getAuthor());
//            }


//        List<Book> books = book_processWithSQL(entityManager);
//        for (Book book3 : books) {
//            System.out.println("Book = " + book3);
//            System.out.println(book3.getAuthor());
//        }
//
//        System.out.println();
//
        List<Author> authors = getAuthors(entityManager);
        for (Author author : authors) {
            System.out.println(author);
            List<Book> books = author.getBooks();
            for (Book book3 : books) {
                System.out.println(book3);
            }
        }
    }

    static List<Book> book_processWithSQL(EntityManager entityManager){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    static  List<Author> getAllAuthors(EntityManager entityManager){
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    static List<Author> getAuthors(EntityManager entityManager){
        TypedQuery <Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    static void addAuthors(EntityManager em, String ... names){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (String name : names) {
            Author author = new Author();
            author.setName(name);
            em.persist(author);
        }
        transaction.commit();
    }

    static void addBooks(EntityManager em, Author [] authors, String ... names){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (String name : names) {
            Random random = new Random();
            int author_id = random.nextInt(10) + 1;
            Book book = new Book();
            book.setName1(name);
//            book.setAuthor(author_id);
            em.persist(book);
        }
        transaction.commit();
    }

    static List<Book> getBooksCriteria(EntityManager entityManager){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), 4)
        );
        TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
