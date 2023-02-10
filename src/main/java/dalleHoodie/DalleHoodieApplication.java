package dalleHoodie;

import dalleHoodie.repository.*;
import dalleHoodie.services.*;

import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class DalleHoodieApplication {
    public static void main(String[] args) {

        DBClient dbConnectionProvider = new DBClient();

        ApplicationContext applicationContext = new ApplicationContext();
        CategoriesRepository categoriesRepository = new CategoriesRepository(
                dbConnectionProvider.getConnection());
        ItemsRepository itemsRepository = new ItemsRepository(
                dbConnectionProvider.getConnection());
        UsersRepository usersRepository = new UsersRepository(
                dbConnectionProvider.getConnection());
        OrdersRepository ordersRepository = new OrdersRepository(
                dbConnectionProvider.getConnection());



//        IService helpService = new HelpService(applicationContext);
//        IService categoriesService = new CategoriesService(applicationContext, categoriesRepository);
//        IService categoryService = new CategoryService(applicationContext, itemsRepository);
//        IService itemService = new ItemService(applicationContext, itemsRepository);
//        IService profileService = new ProfileService(applicationContext, usersRepository);
//        IService registerService = new RegisterService(applicationContext, usersRepository);
//        IService loginService = new LoginService(applicationContext, usersRepository);
//        IService orderService = new OrderService(applicationContext, ordersRepository, itemsRepository);
//        IService ordersService = new OrdersService(applicationContext, ordersRepository, itemsRepository);
//        IService orderItemService = new OrderItemService(applicationContext, ordersRepository, itemsRepository);
        Map<String, IService> services = Map.of(CommandList.HELP, new HelpService(applicationContext), );

        boolean loop = true;
        Scanner in = new Scanner(System.in);
        while (loop) {
            System.out.println("\nEnter command");
            String line = in.nextLine();
            String[] words = line.split(" ");
            String cmd = words[0];
            String[] cmd_args = Arrays.copyOfRange(words, 1, words.length);
            IService service = services.get(cmd);
            service.perform(cmd_args)
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
