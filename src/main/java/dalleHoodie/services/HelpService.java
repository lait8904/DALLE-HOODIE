package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.CommandList;
import dalleHoodie.IService;

public class HelpService implements IService {
    private final String referenceInformation =
            CommandList.HELP + " // вывести справку и список комманд" + '\n' +
            CommandList.REGISTER + " LOGIN PASSWORD // создать нового пользователя" + '\n' +
            CommandList.LOGIN + " LOGIN PASSWORD // авторизоваться в системе. " + '\n' +
            CommandList.CATEGORIES + " // выводит список категорий" + '\n' +
            CommandList.CATEGORY + " CATEGORY_ID // выводит список позиций в категории" + '\n' +
            CommandList.ITEM + " ITEM_ID // выводит более подробную инфу по позиции" + '\n' +
            CommandList.PROFILE + " // выводит инфу о текущем залогиненом пользователе" + '\n' +
            CommandList.ORDER + " // вывести текущий ордер (корзину), который со статусом DRAFT, если его еще нет, то создаст автоматически пустой" + '\n' +
            CommandList.ORDERS + " // вывести список проведенных ордеров" + '\n' +
            CommandList.ORDER_ITEMS + " ITEM_ID // добавить в ордер позицию" + '\n' +
            CommandList.EXIT + " // выйти" + '\n';

    private ApplicationContext applicationContext;

    public HelpService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 0)
            return "Error\n";

        return referenceInformation;
    }

    public void someMethod(){

    }
}
