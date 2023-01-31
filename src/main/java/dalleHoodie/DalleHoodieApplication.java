package main.java.dalleHoodie;

import main.java.dalleHoodie.repository.*;
import main.java.dalleHoodie.services.*;
import java.sql.*;

import java.util.Scanner;
import java.util.Arrays;

public class DalleHoodieApplication {
    public static void main(String[] args) {

        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider();

        ApplicationContext applicationContext = new ApplicationContext();
        CategoriesRepository categoriesRepository = new CategoriesRepository(
                dbConnectionProvider.getConnection());
        ItemsRepository itemsRepository = new ItemsRepository(
                dbConnectionProvider.getConnection());
        UsersRepository usersRepository = new UsersRepository(
                dbConnectionProvider.getConnection());
        OrdersRepository ordersRepository = new OrdersRepository(
                dbConnectionProvider.getConnection());
        HelpService helpService = new HelpService(applicationContext);
        CategoriesService categoriesService = new CategoriesService(applicationContext, categoriesRepository);
        CategoryService categoryService = new CategoryService(applicationContext, itemsRepository);
        ItemService itemService = new ItemService(applicationContext, itemsRepository);
        ProfileService profileService = new ProfileService(applicationContext, usersRepository);
        RegisterService registerService = new RegisterService(applicationContext, usersRepository);
        LoginService loginService = new LoginService(applicationContext, usersRepository);
        OrderService orderService = new OrderService(applicationContext, ordersRepository, itemsRepository);
        OrdersService ordersService = new OrdersService(applicationContext, ordersRepository, itemsRepository);
        OrderItemService orderItemService = new OrderItemService(applicationContext, ordersRepository, itemsRepository);

        boolean loop = true;
        Scanner in = new Scanner(System.in);
        while (loop) {
            System.out.println("\nEnter command");
            String line = in.nextLine();
            String[] words = line.split(" ");
            String cmd = words[0];
            String[] cmd_args = Arrays.copyOfRange(words, 1, words.length);
            switch(cmd) {
                case CommandList.HELP:
                    System.out.print(helpService.perform(cmd_args));
                    break;
                case CommandList.CATEGORIES:
                    System.out.print(categoriesService.perform(cmd_args));
                    break;
                case CommandList.CATEGORY:
                    System.out.print(categoryService.perform(cmd_args));
                    break;
                case CommandList.ITEM:
                    System.out.print(itemService.perform(cmd_args));
                    break;
                case CommandList.PROFILE:
                    System.out.print(profileService.perform(cmd_args));
                    break;
                case CommandList.REGISTER:
                    System.out.print(registerService.perform(cmd_args));
                    break;
                case CommandList.LOGIN:
                    System.out.print(loginService.perform(cmd_args));
                    break;
                case CommandList.ORDER:
                    System.out.print(orderService.perform(cmd_args));
                    break;
                case CommandList.ORDERS:
                    System.out.print(ordersService.perform(cmd_args));
                    break;
                case CommandList.ORDER_ITEMS:
                    System.out.print(orderItemService.perform(cmd_args));
                    break;
                case CommandList.EXIT:
                    loop = false;
                    break;
                          default:
                    System.out.println("Unknown command");
            }
        }
        System.out.println("Goodbye!");
    }
}
