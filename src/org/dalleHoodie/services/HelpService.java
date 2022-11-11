package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;

public class HelpService implements IService {
    private final String referenceInformation =
            "help // вывести справку и список комманд" + '\n' +
            "register LOGIN PASSWORD // создать нового пользователя" + '\n' +
            "login LOGIN PASSWORD // авторизоваться в системе. " + '\n' +
            "categories    // выводит список категорий" + '\n' +
            "category CATEGORY_ID // выводит список позиций в категории" + '\n' +
            "item ITEM_ID // выводит более подробную инфу по позиции" + '\n' +
            "profile // выводит инфу о текущем залогиненом пользователе" + '\n' +
            "order  // вывести текущий ордер (корзину), который со статусом DRAFT, если его еще нет, то создаст автоматов пустой" + '\n' +
            "orders  // вывести список проведенных ордеров" + '\n' +
            "order-item ID_item // добавить в ордер позицию" + '\n' +
            "exit  // выйти" + '\n';

    private ApplicationContext applicationContext;

    public HelpService(ApplicationContext applicationContext) {
        this.setApplicationContext(applicationContext);
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 0)
            return "Error\n";

        return referenceInformation;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
