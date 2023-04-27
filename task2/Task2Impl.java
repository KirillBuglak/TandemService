package ru.tandemservice.test.task2;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 *
 *
 * <h1>Задание №2</h1>
 *
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по
 * времени работы с учетом скорости выполнения операции присвоения номера: большим плюсом (хотя это
 * и не обязательно) будет оценка числа операций, доказательство оптимальности или указание области,
 * в которой алгоритм будет оптимальным.
 */

public class Task2Impl implements IElementNumberAssigner {
//Task2Impl task - реализуем как Singleton
  private static volatile Task2Impl task;

  public static Task2Impl getTask() {
    if (task == null) {
      synchronized (Task2Impl.class) {
        if (task == null) {
          task = new Task2Impl();
        }
      }
    }
    return task;
  }

  @Override
  public void assignNumbers(final List<IElement> elements) {
    ForkJoinPool pool = new ForkJoinPool();
    MyAction action = new MyAction(elements);
    pool.invoke(action);
  }
}
