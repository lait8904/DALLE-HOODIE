package dalleHoodie;

import dalleHoodie.repository.*;
import dalleHoodie.services.*;

import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class DalleHoodieApplication {
    public static void main(String[] args) {

        DBClient dbClient = new DBClient();

        ApplicationContext applicationContext = new ApplicationContext();
        CategoriesRepository categoriesRepository = new CategoriesRepository(
                dbClient);
        ItemsRepository itemsRepository = new ItemsRepository(
                dbClient);
        UsersRepository usersRepository = new UsersRepository(
                dbClient);
        OrdersRepository ordersRepository = new OrdersRepository(
                dbClient);

        Map<String, IService> services = Map.of(
                CommandList.HELP, new HelpService(applicationContext),
                CommandList.CATEGORIES, new CategoriesService(applicationContext, categoriesRepository),
                CommandList.CATEGORY, new CategoryService(applicationContext, itemsRepository),
                CommandList.ITEM, new ItemService(applicationContext, itemsRepository),
                CommandList.PROFILE, new ProfileService(applicationContext, usersRepository),
                CommandList.REGISTER, new RegisterService(applicationContext, usersRepository),
                CommandList.LOGIN, new LoginService(applicationContext, usersRepository),
                CommandList.ORDER, new OrderService(applicationContext, ordersRepository, itemsRepository),
                CommandList.ORDERS, new OrdersService(applicationContext, ordersRepository, itemsRepository),
                CommandList.ORDER_ITEMS, new OrderItemService(applicationContext, ordersRepository, itemsRepository)
                );
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter command");
            String line = in.nextLine();
            String[] words = line.split(" ");
            String cmd = words[0];
            String[] cmd_args = Arrays.copyOfRange(words, 1, words.length);

            if (cmd.equals(CommandList.EXIT))
                break;
            IService service = services.get(cmd);
            if (service == null)
                System.out.println("Unknown command");
            else
                System.out.println(service.perform(cmd_args));
        }
        System.out.println("Goodbye!");
    }
}
