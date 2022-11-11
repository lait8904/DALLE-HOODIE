package org.dalleHoodie;

import org.dalleHoodie.repository.CategoriesRepository;
import org.dalleHoodie.repository.ItemsRepository;
import org.dalleHoodie.repository.UserRepository;
import org.dalleHoodie.services.*;

import java.util.Scanner;
import java.util.Arrays;

public class DalleHoodieApplication {
    public static void main(String[] args) {
        //new CommandList();
        ApplicationContext applicationContext = new ApplicationContext();
        HelpService helpService = new HelpService(applicationContext);
        CategoriesRepository categoriesRepository = new CategoriesRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        UserRepository userRepository = new UserRepository();
        CategoriesService categoriesService = new CategoriesService(applicationContext, categoriesRepository);
        CategoryService categoryService = new CategoryService(applicationContext, itemsRepository);
        ItemService itemService = new ItemService(applicationContext, itemsRepository);
        UserService userService = new UserService(applicationContext, userRepository);
        RegisterService registerService = new RegisterService(applicationContext, userRepository);
        LoginService loginService = new LoginService(applicationContext, userRepository);

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
                case CommandList.USER:
                    System.out.print(userService.perform(cmd_args));
                    break;
                case CommandList.REGISTER:
                    System.out.print(registerService.perform(cmd_args));
                    break;
                case CommandList.LOGIN:
                    System.out.print(loginService.perform((cmd_args)));
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
