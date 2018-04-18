package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;


public class Main {

    public static void main(String[] args) throws IOException {


        /* Лист с заявками*/
        List<String> list = new ArrayList<>();

        boolean go = true;


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (go) {
            System.out.println("Для ввода заявки через консоль нажмите 1, для ввода с файла нажмите 2, для расчета нажмите 3");
            Integer parameter = Integer.valueOf(br.readLine());
            switch (parameter) {
                case 1:{
                    list.add(fromConsole());
                }
                    break;
                case 2:{
                    getFromFile(list, br);
                }
                    break;
                case 3: {
                    go = false;
                    calculation(list);
                }
                    break;
            }
        }

    }

    private static void getFromFile(List<String> list, BufferedReader br) throws IOException {
        System.out.println("Введите путь к файлу (Я сделал фаил 1.txt)");
        String fileName = br.readLine();
        BufferedReader brFile = new BufferedReader(new FileReader(fileName));
        String buffer = null;
        while ((buffer = brFile.readLine())!=null){
            list.add(buffer);
            System.out.println(buffer);
        }
    }

    private static void calculation(List<String> list) {
        TreeSet<Double> mas = new TreeSet<>();

        /*лист объектов заявок*/
        List<Bid> bidList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i).split(" ");
            Bid bid  = new Bid(str[0],Integer.valueOf(str[1]),Double.valueOf(str[2]));
            /*Добавили заявку в лист*/
            bidList.add(bid);
            /*добавили в TreSet всех различных цен продаж*/
            if (bid.getOrientations().equals("S"))
            mas.add(Double.valueOf(bid.getPrice()));
        }

        List<Bid> masSuitable = new ArrayList<>();
        Integer number = 0;
        Double[][] finish = new Double[mas.size()][2];

        /*Нахождение максимального числа заявок, которых купят*/
        for (Double t: mas
             ) {
            Integer numberSell = 0;
            Integer numberBuy = 0;
            for (Bid b: bidList
                    ) {
                if ((b.getOrientations().equals("S"))&(b.getPrice() <= t))
                    numberSell+=b.getCount();
            }
            for (Bid b: bidList
                 ) {
                if ((b.getOrientations().equals("B"))&(b.getPrice() >= t))
                    numberBuy+=b.getCount();
            }
            if (Integer.min(numberSell,numberBuy)>=number){
                number = Integer.min(numberSell,numberBuy);
            }
            System.out.println(Integer.min(numberSell,numberBuy));
        }
        System.out.println("Максимальное число сделок = "+number);


        /*Ищу из всех варианты в которых число сделок равно number */
        if (number==0)
            System.out.println("0 n/a");
        else {
            Integer j = 0;
            Double sum = 0.0;

            for (Double t : mas
                    ) {
                Integer numberSell = 0;
                Integer numberBuy = 0;
                for (Bid b : bidList
                        ) {
                    if ((b.getOrientations().equals("S")) & (b.getPrice() <= t))
                        numberSell += b.getCount();
                }
                for (Bid b : bidList
                        ) {
                    if ((b.getOrientations().equals("B")) & (b.getPrice() >= t))
                        numberBuy += b.getCount();
                }
                if (Integer.min(numberSell, numberBuy) == number) {
                    j++;
                    sum += t;
                }
            }
            System.out.println("-----------------------------------------------");
            System.out.println(number + " " + new BigDecimal(sum / j).setScale(2, RoundingMode.UP).doubleValue());
        }
    }



    private static String fromConsole() throws IOException {
        System.out.println("Введите заявку: направление, количество, цена через пробелы!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bid = br.readLine();
        return bid;
    }

}
