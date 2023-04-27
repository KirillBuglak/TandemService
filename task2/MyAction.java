package ru.tandemservice.test.task2;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MyAction extends RecursiveAction {
    private int start;
    private volatile List<IElement> elements;
    private int end;
    /**
     * время работы Алгоритма зависит от данного параметра, необходимо знать размер List<IElement> elements
     * чтобы правильно настроить параметр limit.
     */
    private int limit;

    public MyAction(List<IElement> elements) {
        this.elements = elements;
    }

    private MyAction(List<IElement> elements, int start, int end) {
        this.elements = elements;
        this.limit = (elements.size() % Runtime.getRuntime().availableProcessors());
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) <= limit) {
            for (int i = start; i <= Math.max(end - 1, elements.size() - 1); i++) {
                IElement element = elements.get(i);
                setNumber(element, i);
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new MyAction(elements, start, middle), new MyAction(elements, middle + 1, end));
        }
    }

    private void setNumber(IElement element, int i) {
        if (element.getNumber() != i) {
            try {
                element.setupNumber(i);
            } catch (IllegalStateException e) {
                setNumber(element, i);
            }
        }
    }
}