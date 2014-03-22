
package com.rpsgame.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class that provides a console reader, from which other classes can read.
 *
 * @author Frank van Heeswijk
 */
public class ConsoleReader implements Runnable {
    /** Synchronized mapping from objects to a list of string consumers to consume input lines. */
    private final static Map<Object, List<Consumer<String>>> CONSUMER_MAP = Collections.synchronizedMap(new HashMap<>());

    /** Synchronized mapping from objects to a blocking queue of strings to read input lines while maintaining blocking behaviour. */
    private final static Map<Object, BlockingQueue<String>> BLOCKING_QUEUE_MAP = Collections.synchronizedMap(new HashMap<>());

    /**
     * Adds a consumer on the object you pass. When console input happens, the consumer will be triggered.
     * 
     * @param object    The object to which the consumers belong
     * @param consumer  The consumer to handle new input
     */
    public static void addConsumer(final Object object, final Consumer<String> consumer) {
        ensureKey(object, CONSUMER_MAP, ArrayList::new);
        CONSUMER_MAP.get(object).add(consumer);
    }

    /**
     * Removes all consumers related to the object you pass.
     * 
     * @param object    The object to which consumers might be attached
     */
    public static void removeConsumers(final Object object) {
        CONSUMER_MAP.remove(object);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            try {
                for (Map.Entry<Object, BlockingQueue<String>> entry : BLOCKING_QUEUE_MAP.entrySet()) {
                    entry.getValue().put(nextLine);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            CONSUMER_MAP.forEach((obj, list) -> list.forEach(consumer -> consumer.accept(nextLine)));
        }
    }

    /**
     * Waits for a new line of input and then returns it.
     * 
     * Per object a new queue gets created, such that taking away the line from one queue does not mean that other objects cannot read it anymore.
     * 
     * @param object    The object that asks to read the next line
     * @return  The next line from System.in
     * @throws InterruptedException     If the thread has been interrupted
     */
    public static String nextLine(final Object object) throws InterruptedException {
        ensureKey(object, BLOCKING_QUEUE_MAP, LinkedBlockingQueue::new);
        BlockingQueue<String> queue = BLOCKING_QUEUE_MAP.get(object);
        String nextLine = queue.take();
        if (queue.isEmpty()) {
            BLOCKING_QUEUE_MAP.remove(object);
        }
        return nextLine;
    }

    /**
     * Ensures that the key has an empty structure attached to it.
     * 
     * @param <V>   The type of structure of Map<Object, V>
     * @param object    The key of the map
     * @param map   The map on which to ensure a value exists
     * @param supplier  A supplier to supply an empty structure
     */
    private static <V> void ensureKey(final Object object, final Map<Object, V> map, final Supplier<V> supplier) {
        if (!map.containsKey(object)) {
            map.put(object, supplier.get());
        }
    }
}
