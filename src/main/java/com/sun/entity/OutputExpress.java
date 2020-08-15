package com.sun.entity;

import java.util.*;

public class OutputExpress {
    public static void main(String[] args) {
        testOutputExpress();
    }

    public static String outputExpress(int[] coefficientArray, int[] exponentArray) {
        Map<Integer, Integer> map = new TreeMap();
        for (int i = 0; i < exponentArray.length; i++) {
            map.put(exponentArray[i], coefficientArray[i]);
        }
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            Integer exponent = entry.getKey();
            Integer coefficient = entry.getValue();

            if (coefficient == 0) {
                continue;
            } else if (coefficient == 1) {
                if (exponent == 0) {
                    builder.append(1).append("+");
                } else if (exponent == 1) {
                    builder.append("x").append("+");
                } else {
                    builder.append("x").append(exponent).append("+");
                }
            } else if (coefficient == -1) {
                if (exponent == 0) {
                    builder.append("-1").append("+");
                } else if (exponent == 1) {
                    builder.append("-").append("x").append("+");
                } else {
                    builder.append("-").append("x").append(exponent).append("+");
                }
            } else {
                if (exponent == 0) {
                    builder.append(coefficient).append("+");
                } else if (exponent == 1) {
                    builder.append(coefficient).append("x").append("+");
                } else {
                    builder.append(coefficient).append("x").append(exponent).append("+");
                }
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        String str = builder.toString();
        return str.replaceAll("\\+-", "-");
    }

    public static void testOutputExpress() {
        List<Data> list = new ArrayList<>();

        int[] coefficientArray = new int[]{1, 2, 3, 4, 5};
        int[] exponentArray = new int[]{6, 7, 8, 9, 10};
        String expectResult = "x6+2x7+3x8+4x9+5x10";
        list.add(new Data(coefficientArray, exponentArray, expectResult));

        coefficientArray = new int[]{-2, -1, 0, 1, 2};
        exponentArray = new int[]{6, 7, 8, 9, 10};
        expectResult = "-2x6-x7+x9+2x10";
        list.add(new Data(coefficientArray, exponentArray, expectResult));

        coefficientArray = new int[]{-2, -1, 0, 1, 2};
        exponentArray = new int[]{0, 1, 2, 3, 4};
        expectResult = "-2-x+x3+2x4";
        list.add(new Data(coefficientArray, exponentArray, expectResult));

        for (Data data : list) {
            String express = outputExpress(data.coefficientArray, data.exponentArray);
            System.out.println("预期结果：" + data.expectResult + " , 实际结果：" + express + " , 是否符合预期：" + (data.expectResult.equals(express) ? "是" : "否"));
        }
    }

    static class Data {
        private int[] coefficientArray;
        private int[] exponentArray;
        private String expectResult;

        public Data() {
        }

        public Data(int[] coefficientArray, int[] exponentArray, String expectResult) {
            this.coefficientArray = coefficientArray;
            this.exponentArray = exponentArray;
            this.expectResult = expectResult;
        }

        public int[] getCoefficientArray() {
            return coefficientArray;
        }

        public void setCoefficientArray(int[] coefficientArray) {
            this.coefficientArray = coefficientArray;
        }

        public int[] getExponentArray() {
            return exponentArray;
        }

        public void setExponentArray(int[] exponentArray) {
            this.exponentArray = exponentArray;
        }

        public String getExpectResult() {
            return expectResult;
        }

        public void setExpectResult(String expectResult) {
            this.expectResult = expectResult;
        }
    }
}
