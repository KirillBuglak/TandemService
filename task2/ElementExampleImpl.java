package ru.tandemservice.test.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * одна из возможных реализаций {@link IElement}
 */
public final class ElementExampleImpl implements IElement {

    private final Context context;

    private final long id;
    private Integer number = null;

    /**
     * @param number номер элемента
     */
    public ElementExampleImpl(final Context context, final int number) {
        this.context = context;
        this.id = System.identityHashCode(this);
        this.setNumber(number);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getNumber() {
        return number;
    }

    private void setNumber(final int number) {
        if (this.number != null) {
            context.uElements.remove(this.number);
        }
        this.number = number;
        final ElementExampleImpl old = context.uElements.put(this.number, this);
        if (old == null) {
            return;
        }
        if (this == old) {
            return;
        }
        throw new IllegalStateException("Этот номер уже был");
    }

    @Override
    public void setupNumber(final int number) {
        setNumber(number);
        context.updateCount();// обновляем счетчик
    }

    @Override
    protected Object clone() {
        throw new UnsupportedOperationException(); // клонировать элементы нельзя
    }

    public static class Context {

        private final AtomicInteger count = new AtomicInteger(0);//Счетчик операций

        private Map<Integer, ElementExampleImpl> uElements = new HashMap<>();

        public synchronized int getCount() {
            return count.get();
        }

        public synchronized void updateCount() {
            count.incrementAndGet();
        }
    }
}
