package com.my.steinfield.Steinfield.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.my.steinfield.Steinfield.models.Category;
import  com.my.steinfield.Steinfield.models.Order;
import  com.my.steinfield.Steinfield.models.OrderItem;
import  com.my.steinfield.Steinfield.models.Payment;
import  com.my.steinfield.Steinfield.models.Product;
import  com.my.steinfield.Steinfield.models.User;
import  com.my.steinfield.Steinfield.models.enuns.OrderStatus;
import  com.my.steinfield.Steinfield.models.enuns.Profiles;
import  com.my.steinfield.Steinfield.repositories.CategoryRepository;
import  com.my.steinfield.Steinfield.repositories.OrderItemRepository;
import  com.my.steinfield.Steinfield.repositories.OrderRepository;
import  com.my.steinfield.Steinfield.repositories.ProductRepository;
import  com.my.steinfield.Steinfield.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1= new Category(null, "Fantasia");
        Category cat2= new Category(null, "Comédia");
        Category cat3= new Category(null, "Autoajuda");
        Category cat4= new Category(null, "Sátira");
        Category cat5 = new Category(null, "Ficção");
        Category cat6 = new Category(null, "Romance");
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));

        Product p1= new Product(null, "Senhor dos Anéis", "J.R.R. Tolkien", 90.5, "https://m.media-amazon.com/images/I/51waks01PAL.jpg");
        Product p2= new Product(null, "Diário de um banana - Quebra Tudo", "Jeff Kinney", 100.0, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/_fd96be0f80e5e7dfbc3a659decca77ec13f86343.jpg");
        Product p3= new Product(null, "O Poder do Hábito", "Charles Duhigg", 110.0, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/a18a90acfd6dbeb64fdbe0ff229eb04e3fca4c97.jpg");
        Product p4= new Product(null, "Como Fazer Amigos e Influenciar Pessoas", "Dale Carnegie", 80.70, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/f6a3fd65539c811c901b1ecac731435fcdf3155e.jpg");
        Product p5= new Product(null, "A Revolução dos Bichos", "George Orwell", 100.99, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/33c58f12b61a13fda2f2cec1aca488076f18930d.jpg");
        Product p6= new Product(null, "O Alienista", "Machado de Assis", 95.5, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/_1a80c3b8bb9badf0ac78f1b9e3d832affee2e7a4.jpg");
        Product p7= new Product(null, "A Hora da Estrela", "Clarice Lispector", 110.0, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/81826808cb9e1be4b8944a3aad6e05f1c480630a.jpg");
        Product p8= new Product(null, "Os Miseráveis", "Victor Hugo", 80.0, "https://d1pkzhm5uq4mnt.cloudfront.net/imagens/capas/92de880eb7dd2672210b6afbbb1b65dd5883a3fd.jpg");
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
        p1.getCategories().add(cat1);
        p2.getCategories().add(cat2);
        p2.getCategories().add(cat5);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat4);
        p6.getCategories().add(cat4);
        p7.getCategories().add(cat6);
        p8.getCategories().add(cat6);
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

        User u1= new User("Maria Brown", "maria@gmail.com", "988888888", pe.encode("123456"));
        User u2= new User("Alex Green", "alex@gmail.com", "977777777", pe.encode("123456"));
        u2.setProfile(Profiles.ADMIN);
        Order o1 = new Order(null, Instant.parse("2020-06-30T09:39:30Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2020-07-01T19:39:30Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2020-07-15T14:30:30Z"), OrderStatus.WAITING_PAYMENT, u1);
        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        OrderItem oi1= new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2= new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3= new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4= new OrderItem(o3, p5, 2, p5.getPrice());
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2020-06-30T11:39:30Z"), o1);
        o1.setPayment(pay1);
        orderRepository.save(o1);
    }
}