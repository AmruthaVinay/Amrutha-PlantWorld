package com.perscholas.app;

import com.perscholas.app.model.*;
import com.perscholas.app.repository.*;
import com.perscholas.app.service.UserImageService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoadDataRunner implements CommandLineRunner {

ProductRepository productRepository;
CategoryRepository categoryRepository;
UserRepository userRepository;
OrderRepository orderRepository;
    UserImageRepository userImageRepository;
    UserImageService userImageService;
AuthGroupRepoI authGroupRepoI;

    public LoadDataRunner(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, OrderRepository orderRepository, UserImageRepository userImageRepository, UserImageService userImageService, AuthGroupRepoI authGroupRepoI) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.userImageRepository = userImageRepository;
        this.userImageService = userImageService;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Autowired


    @PostConstruct
    void created(){
        log.warn("=============== My CommandLineRunner Got Created ===============");
    }

    @Async
    @Override
    public void run(String... args) throws Exception {

        userImageService.init();

        try {

            UserRegistration user1 = new UserRegistration("a@a.com","Amrutha","Amrutha@89","Naperville Orion59");
            user1 =  userRepository.saveAndFlush(user1);
            UserRegistration user2 = new UserRegistration("v@v.com","Vinay","Vinay@89","Naperville Orion59");
            user2  =  userRepository.saveAndFlush(user2);
            UserRegistration user3 = new UserRegistration("av@av.com","Avyukth","Avyu@89","Naperville Orion59");
            user3 =  userRepository.saveAndFlush(user3);
            UserRegistration user4 = new UserRegistration("ac@ac.com","Achinthya","Achin@89","Naperville Orion59");
            user4 =  userRepository.saveAndFlush(user4);
            UserRegistration user5 = new UserRegistration("naga@naga.com","Naga","Naga@89","Bangalore Gangamma Block");
            user5 =  userRepository.saveAndFlush(user5);


            authGroupRepoI.save(new AuthGroup("a@a.com", "ROLE_ADMIN"));
            authGroupRepoI.save(new AuthGroup("v@v.com", "ROLE_USER"));
            authGroupRepoI.save(new AuthGroup("ac@ac.com", "ROLE_USER"));
            authGroupRepoI.save(new AuthGroup("av@av.com", "ROLE_USER"));


   /*         UserImage userImage1 = new UserImage("Amrutha_image", "./uploads/a-2023.webp");
            UserImage userImage2 = new UserImage("Vinay_image", "./uploads/amvi-2023.png");
            UserImage userImage3 = new UserImage("Av_image", "./uploads/new-2023.webp");
            UserImage userImage4 = new UserImage("Ac_image", "./uploads/newa-2023.webp");
            UserImage userImage5 = new UserImage("Naga_image", "./uploads/a-2023.webp");*/



       /*    userImageRepository.saveAndFlush(userImage1);
            userImageRepository.saveAndFlush(userImage2);
            userImageRepository.saveAndFlush(userImage3);
            userImageRepository.saveAndFlush(userImage4);
           userImageRepository.saveAndFlush(userImage5);*/



           // log.debug(user5.toString());
            Category indoorPlants = new Category(Integer.valueOf(1),"Indoor PLants","Indoor Plants");
            indoorPlants = categoryRepository.saveAndFlush(indoorPlants);
            Category OutdoorPlants = new Category(Integer.valueOf(2),"Outdoor PLants","Outdoor Plants");
            OutdoorPlants = categoryRepository.saveAndFlush(OutdoorPlants);
            Category herbs = new Category(Integer.valueOf(3),"Herbs","Herbs");
            herbs = categoryRepository.saveAndFlush(herbs);
            Category potsAndTools = new Category(Integer.valueOf(4),"Pots and Tools","Pots and Tools");
            potsAndTools = categoryRepository.saveAndFlush(potsAndTools);
            Category sale = new Category(Integer.valueOf(5),"Sale","Sale");
            sale = categoryRepository.saveAndFlush(sale);


            Product product = new Product(Integer.valueOf(1),"Aloe Plant",sale,
                    16.00,
                    "This is a colourful and rare aloe plant. Make it yours",
                    "images/sale/fancy1.jpg",20,20.0,1,user1);
            product = productRepository.saveAndFlush(product);
            Product product2 = new Product(Integer.valueOf(2),"Fancy Pot",sale,
                    27.20,
                    "This is a beautiful , cute pot for succulents or small plants.",
                    "images/sale/fancy6.jpg",15,32.00,1,user1);
            product2 = productRepository.saveAndFlush(product2);
            Product product3 = new Product(Integer.valueOf(3),"Fancy Pots",sale,
                    29.25,
                    "This is a beautiful pot that can hold a pair of plants",
                    "images/sale/fancy7.jpg",35,45.00,1,user1);
            product3 = productRepository.saveAndFlush(product3);
            Product product4 = new Product(Integer.valueOf(4),"Cactus Plant",sale,
                    33.00,
                    "This is a rare star fish cactus plant. Make it yours",
                    "images/sale/fancy2.jpg",40,55.00,1,user1);
            product4 = productRepository.saveAndFlush(product4);
            Product product5 = new Product(Integer.valueOf(5),"Buddha Pot",sale,
                    13.20,
                    "Beautiful Buddha pot, that suits any living space",
                    "images/sale/fancy3.jpg",67,40.00,1,user1);
            product5 = productRepository.saveAndFlush(product5);
          Product product6 = new Product(Integer.valueOf(6),"Fancy Pots",sale,
                    10.80   ,
                    "This is a beautiful emoji pots. Best suited for succulents",
                    "images/sale/fancy4.jpg",40,18.00,1,user1);
            product6 = productRepository.saveAndFlush(product6);
            Product product7 = new Product(Integer.valueOf(7),"Emoji Pots",sale,
                    40.00,
                    "White and beautiful emoji pots. Best for small plants",
                    "images/sale/fancy5.jpg",20,50.00,1,user1);
            product7 = productRepository.saveAndFlush(product7);
            Product product8 = new Product(Integer.valueOf(8),"Buddha Pot",sale,
                    24.00,
                    "Beautiful buddha pot for succulents or small plants",
                    "images/sale/fancy8.jpg",20,30.00,1,user1);
            product8 = productRepository.saveAndFlush(product8);
            Product product9 = new Product(Integer.valueOf(9),"Bonsai trees",indoorPlants,
                    20.00,
                    "These are beautiful dwarf tress that suit any living space",
                    "images/indoor/Bonsai.webp",0,1,user1);
            product9 = productRepository.saveAndFlush(product9);
            Product product10 = new Product(Integer.valueOf(10),"Ferns",indoorPlants,
                    20.00,
                    "Ferns are best hanging plants, that stays bushy and green",
                    "images/indoor/fern.webp",0,1,user1);
            product10 = productRepository.saveAndFlush(product10);
            Product product11 = new Product(Integer.valueOf(11),"Orchids",indoorPlants,
                    10.00,
                    "Beautiful Orchids which adds a pop of colour to any space",
                    "images/indoor/orchid.webp",0,1,user1);
            product11 = productRepository.saveAndFlush(product11);
            Product product12 = new Product(Integer.valueOf(12),"Peace Lillies",indoorPlants,
                    30.00,
                    "Best house plants which are beautiful as well as air purifier",
                    "images/indoor/peace_lillies.webp",0,1,user1);
            product12 = productRepository.saveAndFlush(product12);
            Product product25 = new Product(Integer.valueOf(25),"Photos",indoorPlants,
                    18.00,
                    "These are low maintenance,beautiful, air purifying hanging plant",
                    "images/indoor/photos.webp",0,1,user1);
            product25 = productRepository.saveAndFlush(product25);
            Product product13 = new Product(Integer.valueOf(13),"Snake Plant",indoorPlants,
                    23.00,
                    "Snake plants are low maintenance, hard to kill, air purifying plants",
                    "images/indoor/snake.webp",0,1,user1);
            product13 = productRepository.saveAndFlush(product13);
            Product product14 = new Product(Integer.valueOf(14),"Swiss cheese Plant",indoorPlants,
                    25.00,
                    "These hanging plants have beautiful leaves that very green and cute",
                    "images/indoor/swissCheese.webp",0,1,user1);
            product14 = productRepository.saveAndFlush(product14);
            Product product15 = new Product(Integer.valueOf(15),"ZZ Plant",indoorPlants,
                    20.00,
                    "Best house plants with low maintenance, low light, easy growing plant",
                    "images/indoor/zz.webp",0,1,user1);
            product15 = productRepository.saveAndFlush(product15);
            Product product16 = new Product(Integer.valueOf(16),"Hibiscus Plant",OutdoorPlants,
                    25.00,
                    "This is a colourful hibiscus plant",
                    "images/outdoor/hibiscus pink.webp",0,1,user1);
            product16 = productRepository.saveAndFlush(product16);
            Product product17 = new Product(Integer.valueOf(17),"Hydrangea Plant",OutdoorPlants,
                    15.00,
                    "This is a colourful hydrangea. Beautify your garden with this plant",
                    "images/outdoor/hydrangea_colourful.webp",0,1,user1);
            product17 = productRepository.saveAndFlush(product17);
            Product product18 = new Product(Integer.valueOf(18),"Tool",potsAndTools,
                    10.00,
                    "This is a useful tool in your garden",
                    "images/tools/tool2.webp",0,1,user1);
            product18 = productRepository.saveAndFlush(product18);
            Product product19 = new Product(Integer.valueOf(19),"Seed starter",potsAndTools,
                    32.00,
                    "Useful as a seed starter or for growing plant from seeds",
                    "images/tools/seed starter.webp",0,1,user1);
            product19 = productRepository.saveAndFlush(product19);
            Product product20 = new Product(Integer.valueOf(20),"Planting Pots",potsAndTools,
                    12.00,
                    "This is a useful for planting pots or as seed starter",
                    "images/tools/plantable_pots.webp",0,1,user1);
            product20 = productRepository.saveAndFlush(product20);
            Product product21 = new Product(Integer.valueOf(21),"Tomato Cage",potsAndTools,
                    32.00,
                    "This is a tomato cage, useful for big and juicy tomatoes",
                    "images/tools/tomato _cage.webp",0,1,user1);
            product21 = productRepository.saveAndFlush(product21);
            Product product22 = new Product(Integer.valueOf(22),"Cilantro Plant",herbs,
                    22.00,
                    "Cilantro is great for garnishing or as blood purifier",
                    "images/herb/cilantro.webp",0,1,user1);
            product22 = productRepository.saveAndFlush(product22);
            Product product23 = new Product(Integer.valueOf(23),"Dill pLant",herbs,
                    12.00,
                    "This plant is both edible, delicious and very flavourful",
                    "images/herb/dill.webp",0,1,user1);
            product23 = productRepository.saveAndFlush(product23);
            Product product24 = new Product(Integer.valueOf(24),"Lemon Grass",herbs,
                    32.00,
                    "lemon grass plant is good for tea or as mosquito repellent",
                    "images/herb/lemongrass.webp",0,1,user1);
            product24 = productRepository.saveAndFlush(product24);


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
