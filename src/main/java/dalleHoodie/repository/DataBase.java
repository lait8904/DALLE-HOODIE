package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class DataBase {
        public static final List<Category> categories = new ArrayList<Category>();
        public static final List<Item> items = new ArrayList<Item>();
        public static final List<OrderItem> orderItems = new ArrayList<OrderItem>();
        public static final List<Order> orders = new ArrayList<Order>();
        public static final List<User> users = new ArrayList<User>();

        public DataBase() {
                //Добавляем предметы
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("black hoodie");
        item1.setCategoryId(1);
        item1.setColorId(1);
        items.add(item1);

        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("red hoodie");
        item2.setCategoryId(1);
        item2.setColorId(2);
        items.add(item2);

        Item item3 = new Item();
        item3.setItemId(3);
        item3.setItemName("black t-short");
        item3.setCategoryId(2);
        item3.setColorId(1);
        items.add(item3);

        //Добавляем категории
        Category cat1 = new Category();
        cat1.setCategoryId(1);
        cat1.setCategoryName("hoodie");
        categories.add(cat1);

        Category cat2 = new Category();
        cat2.setCategoryId(2);
        cat2.setCategoryName("t-short");
        categories.add(cat2);

        Category cat3 = new Category();
        cat3.setCategoryId(3);
        cat3.setCategoryName("backpack");
        categories.add(cat3);
        }

}
