package ru.tandemservice.test.task1;


import java.util.List;

/**
 * <h1>Задание №1</h1>
 * <p>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными
 * средствами java.
 */
public class Task1Impl implements IStringRowsListSorter {
    //Task1Impl task - реализуем как Singleton
    public static volatile Task1Impl task;

    public static Task1Impl getTask() {
        if (task == null) {
            synchronized (Task1Impl.class) {
                if (task == null) {
                    task = new Task1Impl();
                }
            }
        }
        return task;
    }

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        rows.sort(new Sorter(columnIndex));
    }
}
