package org.example;

import entities.evenement;
import entities.tickets;
import service.event_service;
import utils.data_source;
import service.tickets_service;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        event_service es = new event_service();
      tickets_service ts=new tickets_service();

        Connection cnx = data_source.getInstance().getCnx();

       /* evenement e=new evenement("haloowen","scary", Date.valueOf("2023-09-11"));
        es.add(e);*/
        //System.out.println(es.readAll());
       // es.delete(1);
      /*  evenement e=new evenement(2,"allah","islem",Date.valueOf("2023-09-12"));
es.update(e);*/
/*tickets t =new tickets("VIP",12,70,2);
ts.add(t);*/
        ts.delete(3);
    }
    }
