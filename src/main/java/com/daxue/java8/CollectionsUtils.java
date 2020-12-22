package com.daxue.java8;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class CollectionsUtils {
    public static <E> Set<E> setOf() {
        return setOf(Collections.emptySet());
    }

    public static <E> Set<E> setOf(E e1) {
        return setOf((Collection)ofList(e1));
    }

    public static <E> Set<E> setOf(E e1, E e2) {
        return setOf((Collection)ofList(e1, e2));
    }

    public static <E> Set<E> setOf(E e1, E e2, E e3) {
        return setOf((Collection)ofList(e1, e2, e3));
    }

    public static <E> Set<E> setOf(E e1, E e2, E e3, E e4) {
        return setOf((Collection)ofList(e1, e2, e3, e4));
    }

    public static <E> Set<E> setOf(Collection<E> elementCollection) {
        return setOf((Set)(new HashSet(elementCollection)));
    }

    public static <E> Set<E> setOf(Set<E> elements) {
        return Collections.unmodifiableSet(elements);
    }

    public static <E> List<E> listOf() {
        return listOf(Collections.emptyList());
    }

    public static <E> List<E> listOf(E e1) {
        return listOf(ofList(e1));
    }

    public static <E> List<E> listOf(E e1, E e2) {
        return listOf(ofList(e1, e2));
    }

    public static <E> List<E> listOf(E e1, E e2, E e3) {
        return listOf(ofList(e1, e2, e3));
    }

    public static <E> List<E> listOf(E e1, E e2, E e3, E e4) {
        return listOf(ofList(e1, e2, e3, e4));
    }

    public static <E> List<E> listOf(Collection<E> elementCollection) {
        return listOf((List)(new ArrayList(elementCollection)));
    }

    public static <E> List<E> listOf(List<E> elements) {
        return Collections.unmodifiableList(elements);
    }

    public static <K, V> Map<K, V> mapOf() {
        return mapOf(Collections.emptyMap());
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1)));
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1), CollectionsUtils.MapEntry.of(k2, v2)));
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1), CollectionsUtils.MapEntry.of(k2, v2), CollectionsUtils.MapEntry.of(k3, v3)));
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1), CollectionsUtils.MapEntry.of(k2, v2), CollectionsUtils.MapEntry.of(k3, v3), CollectionsUtils.MapEntry.of(k4, v4)));
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1), CollectionsUtils.MapEntry.of(k2, v2), CollectionsUtils.MapEntry.of(k3, v3), CollectionsUtils.MapEntry.of(k4, v4), CollectionsUtils.MapEntry.of(k5, v5)));
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return mapOf((Iterable)ofList(CollectionsUtils.MapEntry.of(k1, v1), CollectionsUtils.MapEntry.of(k2, v2), CollectionsUtils.MapEntry.of(k3, v3), CollectionsUtils.MapEntry.of(k4, v4), CollectionsUtils.MapEntry.of(k5, v5), CollectionsUtils.MapEntry.of(k6, v6)));
    }

    public static <K, V> Map<K, V> mapOf(Map<K, V> map) {
        return Collections.unmodifiableMap(map);
    }

    private static <K, V> Map<K, V> mapOf(Iterable<Entry<K, V>> entries) {
        Map<K, V> map = new LinkedHashMap();
        Iterator var2 = entries.iterator();

        while(var2.hasNext()) {
            Entry<K, V> entry = (Entry)var2.next();
            map.put(entry.getKey(), entry.getValue());
        }

        return mapOf((Map)map);
    }

    @SafeVarargs
    private static <T> List<T> ofList(T... elements) {
        return Arrays.asList(elements);
    }

//    public static <T> Collection<List<T>> partition(Collection<T> collection, int partitionSize) {
//        AlertUtils.notNull(collection);
//        AlertUtils.isTrue(partitionSize > 0);
//        AtomicInteger index = new AtomicInteger();
//        return ((Map)collection.stream().collect(Collectors.groupingBy((e) -> {
//            return index.getAndIncrement() / partitionSize;
//        }))).values();
//    }

    private CollectionsUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static class MapEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(final V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @ConstructorProperties({"key", "value"})
        private MapEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public static <K, V> CollectionsUtils.MapEntry<K, V> of(final K key, final V value) {
            return new CollectionsUtils.MapEntry(key, value);
        }
    }
}

