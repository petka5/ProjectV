package org.petka.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<String> templates = new ArrayList<>();
        templates.add("Hello {one} , {two}");
        templates.add("sfg {one} , {two} {3}");

        Map<String, String> map = new HashMap<>();
        map.put("one", " this is one");
        map.put("two", "tova e 2");

        List<String> collect1 = templates.stream().map(
                t -> map.entrySet().stream()
                        .map(e -> (Function<String, String>) data -> data
                                .replaceAll("\\{" + e.getKey() + "\\}", e.getValue()))
                        .reduce(Function.identity(), Function::andThen).apply(t)).collect(Collectors.toList());

        System.out.println(collect1);
    }
}
