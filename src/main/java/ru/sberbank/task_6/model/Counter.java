package ru.sberbank.task_6.model;

import java.util.Objects;

/**Модель объекта "Счётчик"*/
public class Counter {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        Counter counter = (Counter) o;
        return getValue() == counter.getValue() && getName().equals(counter.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue());
    }
}
