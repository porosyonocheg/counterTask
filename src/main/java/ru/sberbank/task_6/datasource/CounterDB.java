package ru.sberbank.task_6.datasource;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Хранилище счётчиков*/

@Repository
public class CounterDB {
    private final Map<String, Integer> counters;

    public CounterDB() {
        counters = new HashMap<>();
    }

    /**Проверка наличия в базе сущности с заданным именем*/
    public boolean isUniqueName(String name) {
        return !counters.containsKey(name);
    }

    /**Сохранение новой сущности в базу*/
    public void save (String name) {
        counters.put(name, 0);
    }

    /**Инкрементирование значения существующей сущности*/
    public int update(String name) {
        int newValue = counters.get(name) + 1;
        counters.put(name, newValue);
        return newValue;
    }

    /**Получение значения существующей сущности по имени*/
    public int getValueByName(String name) {
        return counters.get(name);
    }

    /**Удаление существующей сущности по имени*/
    public void delete(String name) {
        counters.remove(name);
    }

    /**Получение общей суммы значений всех счётчиков*/
    public int getTotalValue() {
        int sum = 0;
        for (int i : counters.values()) {
            sum += i;
        }
        return sum;
    }

    /**Получение списка всех имен сущностей*/
    public List<String> getNames() {
        return new ArrayList<>(counters.keySet());
    }
}
