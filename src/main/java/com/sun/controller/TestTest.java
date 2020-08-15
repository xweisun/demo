package com.sun.controller;

import java.util.*;
import java.util.stream.Collectors;

public class TestTest {

    public static void main(String[] args) {
        List<Pair<String, Double>> stringDoubleMap = getStringDoubleMap(0);

        DoubleSummaryStatistics doubleSummaryStatistics =
                stringDoubleMap.stream().mapToDouble(Pair::getR).limit(1).summaryStatistics();

        Pair<Object, Object> pair = new Pair<>();
        Optional<Object> roleOpt = Optional.ofNullable(pair).map(Pair::getR);

    }

    private static List<Pair<String, Double>> getStringDoubleMap(int s) {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 6.19));
        pairArrayList.add(new Pair<>("version", 10.24));
        pairArrayList.add(new Pair<>("version", 13.14));
        return pairArrayList;
    }

    /*public ListNode reverseKGroupPlus(ListNode head, int k) {
        if (head == null || k <= 1)
            return head;
        // 计算原始链表长度
        int length = linkedLength(head);
        if (length < k)
            return head;
        // 计算 offset
        int offsetIndex = length % k;
        // 原始链表正好可以由 K 分位 N 组，可直接处理
         if (offsetIndex == 0) {
             return reverseKGroup(head, k);
         }
        // 定义并找到 prev 和 offset
        ListNode prev = head, offset = head;
        while (offsetIndex > 0) {
            prev = offset;
            offset = offset.next;
            offsetIndex--;
        }
        // 将 offset 结点子链表进行翻转，再拼接回主链表
        prev.next = reverseKGroup(offset, k);
        return head;
    }*/
    private static void ddd() {
        ArrayList<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 6.19));
        pairArrayList.add(new Pair<>("version", 10.24));
        pairArrayList.add(new Pair<>("version", 13.14));
        ArrayList<Pair<String, Double>> objects = null;
        // pairArrayList.add(new Pair<>("3333", 1333333.14));
        //pairArrayList.addAll(objects);
        //objects.addAll(pairArrayList);
        pairArrayList.sort((p1,p2)->(p1.getR().compareTo(p2.getR())));
        System.out.println(pairArrayList);
//        Pair[] pairs = pairArrayList.toArray(new Pair[0]);
//        Arrays.stream(pairs).forEach(pair -> System.out.println(pair.getR()+""+pair.getT()));

       /* BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
        //apply方法用于接收参数，并返回BinaryOperator中的Integer类型
        System.out.println(add.apply(3, 4));*/
    }

    private static void testddd() {
        String[] departments = new String[] {"iERP", "iERP", "EIBU"};
// 抛出 IllegalStateException 异常
        Map<Integer, String> map = Arrays.stream(departments)
                .collect(Collectors.toMap((S)-> S.hashCode(), str -> str));
        System.out.println(map);
    }

    private static Map<String, Double> getStringDoubleMap() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 6.19));
        pairArrayList.add(new Pair<>("version", 10.24));
        pairArrayList.add(new Pair<>("version", 13.14));
        return pairArrayList.stream().collect(
            // 生成的 map 集合中只有一个键值对：{version=13.14}
                Collectors.toMap((p)->p.getT(), (r)->r.getR(), (v1, v2) -> v2));
    }

    private static boolean checkValueByOptional(Object object) {
        Optional<Object> object1 = Optional.ofNullable(object);
        boolean equals = object1.equals("");
        return (Boolean) Optional.ofNullable(object)
                .filter((e) -> e instanceof String && e.equals("") ? false : true)
                .orElse(false);
    }

    public static void testStreamMap(){
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("a","dd");
        map.put("b",null);
        map.put("c","");
        System.out.println(parseMapForFilter(map));


    }
    public static Map<String, Object> parseMapForFilter(Map<String, Object> map) {
        /*if (map == null) {
            return null;
        } else {
            map = map.entrySet().stream()
                    .filter((e) -> checkValue(e.getValue()))
                    .collect(Collectors.toMap(
                            (e) -> (String) e.getKey(),
                            (e) -> e.getValue()
                    ));
        }*/
        Optional<Map<String, Object>> map2 = Optional.ofNullable(map);
        Map map1 = Optional.ofNullable(map).map(
                (v) -> {
                    Map params = v.entrySet().stream()
                            .filter((e) -> checkValue(e.getValue()))
                            .collect(Collectors.toMap(
                                    (e) -> (String) e.getKey(),
                                    (e) -> e.getValue()
                            ));

                    return params;
                }
        ).orElse(null);


        return map;
    }

    private static  boolean checkValue(Object object) {

        if (object instanceof String && "".equals(object)) {
            return false;
        }

        if (null == object) {
            return false;
        }

        return true;


    }
}
