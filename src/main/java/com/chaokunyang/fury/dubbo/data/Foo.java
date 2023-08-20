package com.chaokunyang.fury.dubbo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Test data.
 *
 * @author chaokunyang
 */
public class Foo {
  private int f1;
  private List<Integer> list;
  private Map<String, Integer> map;

  public static Foo create() {
    Foo f = new Foo();
    f.f1 = 10;
    f.list = new ArrayList<>();
    f.map = new HashMap<>();
    f.list.add(1);
    f.list.add(2);
    f.map.put("k1", 100);
    f.map.put("k2", 200);
    return f;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Foo foo = (Foo) o;
    return f1 == foo.f1 && Objects.equals(list, foo.list) && Objects.equals(map, foo.map);
  }

  @Override
  public int hashCode() {
    return Objects.hash(f1, list, map);
  }

  @Override
  public String toString() {
    return "Foo{" + "f1=" + f1 + ", list=" + list + ", map=" + map + '}';
  }
}
