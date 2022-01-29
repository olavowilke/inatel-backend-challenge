package com.inatel.quotationmanagement.data.dto;

import com.inatel.quotationmanagement.data.Quote;
import com.inatel.quotationmanagement.data.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.tomcat.jni.Local;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

//    public static void main(String[] args) {
//        Map<LocalDate, String> now = Map.of(
//                LocalDate.now(), "10",
//                LocalDate.now().minusDays(5), "20",
//                LocalDate.now().minusDays(3), "30"
//        );
////        System.out.println(now);
//
//        List<Bla> blas = List.of(new Bla(LocalDate.now(), "10"),
//                new Bla(LocalDate.now().minusDays(5), "20"),
//                new Bla(LocalDate.now().minusDays(3), "30"));
//
////        System.out.println(blas);
//
//        Map<LocalDate, String> collect = blas.stream()
//                .collect(Collectors.toMap(Bla::getDate, Bla::getPrice));
//
////        System.out.println(collect);
//
//        //----------------------------------------------------------------
//
//        Map<LocalDate, String> quotes = Map.of(
//                LocalDate.now(), "10",
//                LocalDate.now().minusDays(5), "20",
//                LocalDate.now().minusDays(3), "30"
//        );
//
//        CreateStock createStock = new CreateStock("123", quotes);
//
//        Map<LocalDate, String> quotes1 = createStock.getQuotes();
//
//
//
////        {
////            "id": "c01cede4-cd45-11eb-b8bc-0242ac130003",
////                "stockId": "petr3",
////                "quotes":
////            {
////                "2019-01-01": "10",
////                    "2019-01-02": "11",
////                    "2019-01-03": "14"
////            }
////        }
//
//    }
//
//    @AllArgsConstructor
//    @Getter
//    public static class Bla{
//
//        private LocalDate date;
//        private String price;
//
//        @Override
//        public String toString() {
//            return "Bla{" +
//                    "date:" + date +
//                    ", price:'" + price + '\'' +
//                    '}';
//        }
//    }


}
