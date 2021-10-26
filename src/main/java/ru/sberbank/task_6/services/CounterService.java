package ru.sberbank.task_6.services;

import org.springframework.stereotype.Service;
import ru.sberbank.task_6.datasource.CounterDB;
import ru.sberbank.task_6.model.Counter;
import ru.sberbank.task_6.model.dto.CounterDTO;

import java.util.List;

/**Сервисные методы для работы с хранилищем*/
@Service
public class CounterService {
    private final CounterDB counterDB;

    public CounterService(CounterDB counterDB) {
        this.counterDB = counterDB;
    }

    /**Добавление нового счётчика
     * @param counterDTO данные для создания нового счётчика
     * @return null если счётчик с таким именем уже существует или данные нового счётчика*/
    public CounterDTO addCounter(CounterDTO counterDTO) {
        String name = counterDTO.getName();
        CounterDTO result;
        if (counterDB.isUniqueName(name)) {
            result = new CounterDTO();
            counterDB.save(name);
            result.setName(name);
            return result;
        }
        return null;
    }

    /**Инкрементирование счётчика по имени
     * @param counterDTO данные инкрементируемого счётчика
     * @return null если счётчика с переданным именем не существует или обновлённые данные искомого счётчика*/
    public Counter incrementCounter(CounterDTO counterDTO) {
        String name = counterDTO.getName();
        if (counterDB.isUniqueName(name)) return null;
        Counter counter = new Counter();
        counter.setName(name);
        counter.setValue(counterDB.update(name));
        return counter;
    }

    /**Получение значение счётчика по имени
     * @param name имя счётчика
     * @return null если счётчика с таким именем не существует или данные для счётчика с заданным именем*/
    public Counter getValueByName(String name) {
        if (counterDB.isUniqueName(name)) return null;
        Counter counter = new Counter();
        counter.setName(name);
        counter.setValue(counterDB.getValueByName(name));
        return counter;
    }

    /**Удаление счётчика по имени
     * @param counterDTO данные удаляемого счётчика
     * @return  null если счётчика с переданным именем не существует или данные удалённого счётчика*/
    public CounterDTO deleteByName(CounterDTO counterDTO) {
        String name = counterDTO.getName();
        if (counterDB.isUniqueName(name)) return null;
        CounterDTO result = new CounterDTO();
        result.setName(name);
        counterDB.delete(name);
        return result;
    }

    /**Получение общей суммы всех счётчиков*/
    public int getTotalValue() {
        return counterDB.getTotalValue();
    }

    /**Получение списка всех имён счётчиков*/
    public List<String> getNames() {
        return counterDB.getNames();
    }
}
